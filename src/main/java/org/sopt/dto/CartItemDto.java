package org.sopt.dto;

import lombok.Builder;
import org.sopt.domain.CartItem;

@Builder
public record CartItemDto(

        Long cartItemId,
        Long menuId,
        Integer amount,
        Boolean isSet,
        String menuName,
        String imageUrl
) {

    public static CartItemDto of(CartItem cartItem, String menuName, String imageUrl) {
        return CartItemDto.builder()
                .cartItemId(cartItem.getCartItemId())
                .menuId(cartItem.getMenu().getMenuId())
                .amount(cartItem.getAmount())
                .isSet(cartItem.getIsSet())
                .menuName(menuName)
                .imageUrl(imageUrl)
                .build();
    }
}
