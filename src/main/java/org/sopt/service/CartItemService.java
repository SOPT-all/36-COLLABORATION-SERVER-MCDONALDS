package org.sopt.service;

import java.util.ArrayList;
import java.util.List;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.sopt.domain.CartItem;
import org.sopt.domain.Menu;
import org.sopt.domain.User;
import org.sopt.dto.CartItemDto;
import org.sopt.dto.request.CreateCartItemRequest;
import org.sopt.dto.type.ErrorMessage;
import org.sopt.exception.CustomException;
import org.sopt.repository.CartItemRepository;
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

    private final CartItemRepository cartItemRepository;

    @Transactional
    public void createCartItem(Long userId, CreateCartItemRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorMessage.INVALID_HEADER_ERROR));

        Menu menu = menuRepository.findById(request.menuId())
                .orElseThrow(() -> new CustomException(ErrorMessage.NOT_FOUND_ERROR));

        CartItem cartItem = CartItem.create(
                request.isSet(),
                request.amount(),
                user,
                menu
        );

        cartItemRepository.save(cartItem);
    }

    public List<CartItemDto> getAllCartItems(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorMessage.INVALID_HEADER_ERROR));

        List<CartItem> cartItems = cartItemRepository.findAllByUser(user);
        List<CartItemDto> result = new ArrayList<>();

        for (CartItem cartItem : cartItems) {
            Menu menu = cartItem.getMenu();
            String imageUrl = cartItem.getIsSet() ? menu.getSetImgUrl() : menu.getSingleImgUrl();

            CartItemDto dto = CartItemDto.of(
                    cartItem,
                    menu.getMenuName(),
                    imageUrl
            );
            result.add(dto);
        }

        return result;
    }

}