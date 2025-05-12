package org.sopt.controller;

import org.sopt.dto.common.BaseResponse;
import org.sopt.dto.response.MenuListResponse;
import org.sopt.dto.type.SuccessMessage;
import org.sopt.service.MenuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
