package org.sopt.dto.response;

import org.sopt.dto.OrderDto;

import java.util.List;

public record GetOrderRecentItemListResponse(
        List<OrderDto> recentItems
) {
    public static GetOrderRecentItemListResponse of(List<OrderDto> recentItems){
        return new GetOrderRecentItemListResponse(recentItems);
    }
}
