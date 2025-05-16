package org.sopt.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sopt.dto.common.BaseResponse;
import org.sopt.dto.request.CreateOrderRequest;
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
            @RequestHeader Long userId,
            @RequestBody @Valid CreateOrderRequest request
    ){
        orderService.createOrder(userId, request);

        return ResponseEntity.ok(
                BaseResponse.success(SuccessMessage.OK, null)
        );
    }
}
