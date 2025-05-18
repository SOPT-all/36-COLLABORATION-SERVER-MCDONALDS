package org.sopt.controller;

import org.sopt.domain.Menu;
import org.sopt.dto.common.BaseResponse;
import org.sopt.dto.response.GetMenuListResponse;
import org.sopt.dto.response.GetMenuResponse;
import org.sopt.dto.type.SuccessMessage;
import org.sopt.service.MenuService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/menu")
public class MenuController {
    private final MenuService menuService;

    protected MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping()
    public BaseResponse<GetMenuListResponse> getMenuList(
            @RequestHeader Long userId
    ) {
        List<Menu> menus = menuService.getMenuList(userId);
        return BaseResponse.success(SuccessMessage.OK, GetMenuListResponse.of(menus));
    }

    @GetMapping("/{menuId}")
    public BaseResponse<GetMenuResponse> getMenuDetail(
            @RequestHeader Long userId,
            @PathVariable("menuId") Long menuId
    ){
        Menu menu = menuService.getMenuDetail(userId, menuId);
        return BaseResponse.success(SuccessMessage.OK, GetMenuResponse.of(menu));
    }
}
