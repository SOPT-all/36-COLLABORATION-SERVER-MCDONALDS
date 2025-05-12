package org.sopt.service;

import org.sopt.domain.Menu;
import org.sopt.dto.response.MenuListResponse;
import org.sopt.dto.type.ErrorMessage;
import org.sopt.exception.CustomException;
import org.sopt.repository.MenuJpaRepository;
import org.sopt.repository.UserJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
                        "₩" + menu.getSinglePrice().toString() + " ~"
                ))
                .toList();
        return new MenuListResponse(menuSummaries);
    }

    private void validateUserIdExist(Long userId){
        if (!userRepository.existsById(userId)) {
            throw new CustomException(ErrorMessage.UNAUTHORIZED_ERROR);
        }
    }
}
