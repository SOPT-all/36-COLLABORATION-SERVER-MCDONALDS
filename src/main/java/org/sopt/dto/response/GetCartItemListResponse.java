package org.sopt.dto.response;

import java.util.List;
import org.sopt.domain.CartItem;
import org.sopt.dto.CartItemDto;

public record GetCartItemListResponse(
        List<CartItemDto> cartItems
) {

    public static GetCartItemListResponse of(List<CartItemDto> cartItems) {
        return new GetCartItemListResponse(cartItems);
    }
}
