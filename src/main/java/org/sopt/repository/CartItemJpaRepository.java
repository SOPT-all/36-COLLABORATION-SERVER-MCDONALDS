package org.sopt.repository;

import java.util.List;
import org.sopt.domain.CartItem;
import org.sopt.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemJpaRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findAllByUser(User user);
}
