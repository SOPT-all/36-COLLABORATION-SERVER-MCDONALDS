package org.sopt.dto.response;

import org.sopt.domain.Menu;

import java.text.NumberFormat;
import java.util.List;

public record GetMenuListResponse(
        List<MenuSummary> menuList
) {
    public record MenuSummary(
            Long menuId,
            String menuName,
            String menuImg,
            String menuPrice
    ) {
        public static MenuSummary of(Menu menu) {
            return new MenuSummary(
                    menu.getMenuId(),
                    menu.getMenuName(),
                    menu.getSingleImgUrl(),
                    "₩" + NumberFormat.getInstance().format(menu.getSinglePrice()) + " ~"
            );
        }
    }

    public static GetMenuListResponse of(List<Menu> menuList) {
        List<MenuSummary> menus = menuList.stream()
                .map(MenuSummary::of)
                .toList();
        return new GetMenuListResponse(menus);
    }
}
