package org.sopt.service;

import lombok.RequiredArgsConstructor;
import org.sopt.domain.CartItem;
import org.sopt.domain.Order;
import org.sopt.domain.OrderDetail;
import org.sopt.domain.User;
import org.sopt.dto.OrderDto;
import org.sopt.dto.type.ErrorMessage;
import org.sopt.exception.CustomException;
import org.sopt.repository.CartItemJpaRepository;
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
    private final CartItemJpaRepository cartItemJpaRepository;

    private final OrderJpaRepository orderJpaRepository;

    @Transactional
    public void createOrder(Long userId) {
        User user = getUser(userId);

        Order order = Order.create(user);
        orderJpaRepository.save(order);

        List<CartItem> cartItems = cartItemJpaRepository.findAllByUserWithMenu(user);
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

        cartItemJpaRepository.deleteAll(cartItems);
    }

    public List<OrderDto> getRecentItems(Long userId){
        User user = getUser(userId);

        Order recentOrder = orderJpaRepository.findTopByUserOrderByCreatedAtDesc(user)
                .orElse(null);

        if (recentOrder == null) {
            return List.of();
        }

        return recentOrder.getOrderDetails().stream()
                .map(orderDetail -> orderDetail.getMenu())
                .filter(distinctByKey(menu -> menu.getMenuId()))
                .limit(2)
                .map(OrderDto::of)
                .toList();
    }

    private User getUser(Long userId) {
        return userJpaRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorMessage.INVALID_HEADER_ERROR));
    }

    private <T> java.util.function.Predicate<T> distinctByKey(java.util.function.Function<? super T, ?> keyExtractor) {
        java.util.Set<Object> seen = java.util.concurrent.ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }
}
