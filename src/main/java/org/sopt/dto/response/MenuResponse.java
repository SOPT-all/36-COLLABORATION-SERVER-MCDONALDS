package org.sopt.dto.response;

public record MenuResponse(
        Long menuId,
        String menuName,
        String singleImg,
        String singlePrice,
        String setImg,
        String setPrice
) {
}
