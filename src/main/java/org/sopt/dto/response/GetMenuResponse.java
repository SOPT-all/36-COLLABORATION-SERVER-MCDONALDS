package org.sopt.dto.response;

import org.sopt.domain.Menu;

import java.text.NumberFormat;

public record GetMenuResponse(
        Long menuId,
        String menuName,
        String singleImg,
        String singlePrice,
        String setImg,
        String setPrice
) {
    public static GetMenuResponse of(Menu menu) {
        return new GetMenuResponse(
                menu.getMenuId(),
                menu.getMenuName(),
                menu.getSingleImgUrl(),
                "₩" + NumberFormat.getInstance().format(menu.getSinglePrice()),
                menu.getSetImgUrl(),
                "₩" + NumberFormat.getInstance().format(menu.getSetPrice())
        );
    }
}
