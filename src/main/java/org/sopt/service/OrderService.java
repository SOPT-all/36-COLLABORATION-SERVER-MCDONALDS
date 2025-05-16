package org.sopt.service;

import lombok.RequiredArgsConstructor;
import org.sopt.domain.CartItem;
import org.sopt.domain.Order;
import org.sopt.domain.OrderDetail;
import org.sopt.domain.User;
import org.sopt.dto.request.CreateOrderRequest;
import org.sopt.dto.type.ErrorMessage;
import org.sopt.exception.CustomException;
import org.sopt.repository.CartItemRepository;
import org.sopt.repository.OrderDetailJpaRepository;
import org.sopt.repository.OrderJpaRepository;
import org.sopt.repository.UserJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderDetailJpaRepository orderDetailJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final CartItemRepository cartItemRepository;

    private final OrderJpaRepository orderJpaRepository;

    @Transactional
    public void createOrder(Long userId) {
        User user = getUser(userId);

        Order order = Order.create(user);
        orderJpaRepository.save(order);

        List<CartItem> cartItems = cartItemRepository.findAllByUser(user);
        if (cartItems.isEmpty()) {
            throw new CustomException(ErrorMessage.NOT_FOUND_ERROR);
        }

        List<OrderDetail> orderDetails = cartItems.stream()
                .map(cartItem -> OrderDetail.create(
                        order,
                        cartItem.getMenu(),
                        cartItem.getIsSet(),
                        cartItem.getAmount()
                ))
                .toList();
        orderDetailJpaRepository.saveAll(orderDetails);

        cartItemRepository.deleteAll(cartItems);
    }

    private User getUser(Long userId) {
        return userJpaRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorMessage.INVALID_HEADER_ERROR));
    }
}
