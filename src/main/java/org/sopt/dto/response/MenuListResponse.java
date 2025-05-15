package org.sopt.dto.response;

import java.util.List;

public record MenuListResponse(
        List<MenuSummary> menuList
) {
    public record MenuSummary(
            Long menuId,
            String menuName,
            String menuImg,
            String menuPrice
    ) {
    }
}
