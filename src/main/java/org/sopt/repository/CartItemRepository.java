package org.sopt.repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.sopt.domain.CartItem;
import org.sopt.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findAllByUser(User user);
}
