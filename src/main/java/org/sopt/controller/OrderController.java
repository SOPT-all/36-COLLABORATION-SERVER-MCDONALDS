package org.sopt.controller;

import lombok.RequiredArgsConstructor;
import org.sopt.dto.common.BaseResponse;
import org.sopt.dto.response.GetOrderRecentItemListResponse;
import org.sopt.dto.type.SuccessMessage;
import org.sopt.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<BaseResponse<Void>> createOrder(
            @RequestHeader Long userId
    ){
        orderService.createOrder(userId);

        return ResponseEntity.ok(
                BaseResponse.success(SuccessMessage.OK)
        );
    }

    @GetMapping("/recent")
    public ResponseEntity<BaseResponse<GetOrderRecentItemListResponse>> getRecentItems(
            @RequestHeader Long userId
    ){
        return ResponseEntity.ok(
                BaseResponse.success(
                        SuccessMessage.OK,
                        GetOrderRecentItemListResponse.of(orderService.getRecentItems(userId))
                )
        );
    }
}
