package org.sopt.dto;

import org.sopt.domain.Menu;

public record OrderDto(
        Long menuId,
        String menuName,
        String menuImg,
        Integer menuPrice
) {
    public static OrderDto of(Menu menu){
        return new OrderDto(
                menu.getMenuId(),
                menu.getMenuName(),
                menu.getSingleImgUrl(),
                menu.getSinglePrice()
        );
    }
}
