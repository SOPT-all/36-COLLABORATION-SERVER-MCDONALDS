package org.sopt.service;

import org.sopt.domain.Menu;
import org.sopt.dto.response.MenuListResponse;
import org.sopt.dto.response.MenuResponse;
import org.sopt.dto.type.ErrorMessage;
import org.sopt.exception.CustomException;
import org.sopt.repository.MenuJpaRepository;
import org.sopt.repository.UserJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.text.NumberFormat;

@Service
public class MenuService {
    private final MenuJpaRepository menuRepository;
    private final UserJpaRepository userRepository;

    protected MenuService(final MenuJpaRepository menuRepository, final UserJpaRepository UserRepository) {
        this.menuRepository = menuRepository;
        this.userRepository = UserRepository;
    }

    public MenuListResponse getMenuList(Long userId) {
        validateUserIdExist(userId);
        List<Menu> menuList = menuRepository.findAll();
        List<MenuListResponse.MenuSummary> menuSummaries = menuList.stream()
                .map(menu -> new MenuListResponse.MenuSummary(
                        menu.getMenuId(),
                        menu.getMenuName(),
                        menu.getSingleImgUrl(),
                        "₩" + NumberFormat.getInstance().format(menu.getSinglePrice()) + " ~"
                ))
                .toList();
        return new MenuListResponse(menuSummaries);
    }

    public MenuResponse getMenuDetail(Long userId, Long menuId) {
        validateUserIdExist(userId);
        return menuRepository.findById(menuId)
                .map(menu -> new MenuResponse(
                        menu.getMenuId(),
                        menu.getMenuName(),
                        menu.getSingleImgUrl(),
                        "₩" + NumberFormat.getInstance().format(menu.getSinglePrice()),
                        menu.getSetImgUrl(),
                        "₩" + NumberFormat.getInstance().format(menu.getSetPrice())
                ))
                .orElseThrow(() -> new CustomException(ErrorMessage.NOT_FOUND_ERROR));
    }

    private void validateUserIdExist(Long userId){
        if (!userRepository.existsById(userId)) {
            throw new CustomException(ErrorMessage.UNAUTHORIZED_ERROR);
        }
    }
}
