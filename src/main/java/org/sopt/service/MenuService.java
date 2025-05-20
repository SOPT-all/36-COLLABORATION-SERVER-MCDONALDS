package org.sopt.service;

import org.sopt.domain.Menu;
import org.sopt.dto.response.GetMenuListResponse;
import org.sopt.dto.response.GetMenuResponse;
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

    public List<Menu> getMenuList(Long userId) {
        validateUserIdExist(userId);
        List<Long> menuIds = List.of(1L, 2L, 3L, 4L, 5L);
        return menuRepository.findAllById(menuIds);
    }

    public Menu getMenuDetail(Long userId, Long menuId) {
        validateUserIdExist(userId);
        return menuRepository.findById(menuId)
                .orElseThrow(() -> new CustomException(ErrorMessage.NOT_FOUND_ERROR));

    }

    private void validateUserIdExist(Long userId){
        if (!userRepository.existsById(userId)) {
            throw new CustomException(ErrorMessage.UNAUTHORIZED_ERROR);
        }
    }
}
