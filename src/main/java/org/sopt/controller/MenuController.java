package org.sopt.controller;

import org.sopt.dto.common.BaseResponse;
import org.sopt.dto.response.MenuListResponse;
import org.sopt.dto.response.MenuResponse;
import org.sopt.dto.type.SuccessMessage;
import org.sopt.service.MenuService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/menu")
public class MenuController {
    private final MenuService menuService;

    protected MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping()
    public BaseResponse<MenuListResponse> getMenuList(
            @RequestHeader Long userId
    ) {
        return BaseResponse.success(SuccessMessage.OK, menuService.getMenuList(userId));
    }

    @GetMapping("/{menuId}")
    public BaseResponse<MenuResponse> getMenuDetail(
            @RequestHeader Long userId,
            @PathVariable("menuId") Long menuId
    ){
        return BaseResponse.success(SuccessMessage.OK, menuService.getMenuDetail(userId, menuId));
    }
}
