package org.sopt.service;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.sopt.domain.CartItem;
import org.sopt.domain.Menu;
import org.sopt.domain.User;
import org.sopt.dto.CartItemDto;
import org.sopt.dto.request.CreateCartItemRequest;
import org.sopt.dto.type.ErrorMessage;
import org.sopt.exception.CustomException;
import org.sopt.repository.CartItemJpaRepository;
import org.sopt.repository.MenuJpaRepository;
import org.sopt.repository.UserJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartItemService {

    private final UserJpaRepository userRepository;
    private final MenuJpaRepository menuRepository;

    private final CartItemJpaRepository cartItemJpaRepository;

    @Transactional
    public void createCartItem(Long userId, CreateCartItemRequest request) {
        User user = getUser(userId);

        Menu menu = menuRepository.findById(request.menuId())
                .orElseThrow(() -> new CustomException(ErrorMessage.NOT_FOUND_ERROR));

        CartItem cartItem = CartItem.create(
                request.isSet(),
                request.amount(),
                user,
                menu
        );

        cartItemJpaRepository.save(cartItem);
    }

    @Transactional
    public void updateCartItemAmount(Long userId, Long cartItemId, Integer newAmount) {
        User user = getUser(userId);

        CartItem cartItem = cartItemJpaRepository.findById(cartItemId)
                .orElseThrow(() -> new CustomException(ErrorMessage.NOT_FOUND_ERROR));

        if (!cartItem.isOwnedBy(userId)) {
            throw new CustomException(ErrorMessage.UNAUTHORIZED_ERROR);
        }

        cartItem.updateAmount(newAmount);
    }

    public List<CartItemDto> getAllCartItems(Long userId) {
        User user = getUser(userId);

        List<CartItem> cartItems = cartItemJpaRepository.findAllByUser(user);
        List<CartItemDto> result = new ArrayList<>();

        for (CartItem cartItem : cartItems) {
            Menu menu = cartItem.getMenu();
            CartItemDto dto = CartItemDto.of(
                    cartItem,
                    cartItem.getIsSet() ? menu.getSetPrice() : menu.getSinglePrice(),
                    menu.getMenuName(),
                    cartItem.getIsSet() ? menu.getSetImgUrl() : menu.getSingleImgUrl()
            );
            result.add(dto);
        }

        return result;
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorMessage.INVALID_HEADER_ERROR));
    }
}