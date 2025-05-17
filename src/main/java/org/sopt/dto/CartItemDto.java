package org.sopt.dto;

import jakarta.persistence.criteria.CriteriaBuilder.In;
import lombok.Builder;
import org.sopt.domain.CartItem;

public record CartItemDto(
        Long cartItemId,
        Long menuId,
        Integer amount,
        Boolean isSet,
        Integer price,
        String menuName,
        String imageUrl
) {
    public static CartItemDto of(CartItem cartItem, Integer price, String menuName, String imageUrl) {
        return new CartItemDto(
                cartItem.getCartItemId(),
                cartItem.getMenu().getMenuId(),
                cartItem.getAmount(),
                cartItem.getIsSet(),
                price,
                menuName,
                imageUrl
        );
    }
}
