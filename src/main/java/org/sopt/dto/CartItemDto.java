package org.sopt.dto;

import lombok.Builder;
import org.sopt.domain.CartItem;

public record CartItemDto(
        Long cartItemId,
        Long menuId,
        Integer amount,
        Boolean isSet,
        String menuName,
        String imageUrl
) {
    public static CartItemDto of(CartItem cartItem, String menuName, String imageUrl) {
        return new CartItemDto(
                cartItem.getCartItemId(),
                cartItem.getMenu().getMenuId(),
                cartItem.getAmount(),
                cartItem.getIsSet(),
                menuName,
                imageUrl
        );
    }
}
