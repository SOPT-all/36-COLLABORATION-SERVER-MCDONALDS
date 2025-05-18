package org.sopt.controller;

import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.sopt.dto.common.BaseResponse;
import org.sopt.dto.request.CreateCartItemRequest;
import org.sopt.dto.request.UpdateCartItemRequest;
import org.sopt.dto.response.GetCartItemListResponse;
import org.sopt.dto.type.SuccessMessage;
import org.sopt.service.CartItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;

    @PostMapping
    public ResponseEntity<BaseResponse<Void>> createCartItem(
            @RequestHeader Long userId,
            @RequestBody @Valid CreateCartItemRequest request
    ) {
        cartItemService.createCartItem(userId, request);

        return ResponseEntity.ok(
                BaseResponse.success(SuccessMessage.OK, null)
        );
    }

    @GetMapping
    public ResponseEntity<BaseResponse<GetCartItemListResponse>> getCartItem(
            @RequestHeader Long userId
    ) {
        return ResponseEntity.ok(
                BaseResponse.success(
                        SuccessMessage.OK,
                        GetCartItemListResponse.of(cartItemService.getAllCartItems(userId))
                )
        );
    }


    @PatchMapping("/{cartItemId}")
    public ResponseEntity<BaseResponse<Void>> updateCartItemAmount(
            @RequestHeader Long userId,
            @PathVariable Long cartItemId,
            @RequestBody@Valid UpdateCartItemRequest request
    ) {
        cartItemService.updateCartItemAmount(userId, cartItemId, request.amount());

        return ResponseEntity.ok(
                BaseResponse.success(SuccessMessage.OK, null)
        );
    }

}