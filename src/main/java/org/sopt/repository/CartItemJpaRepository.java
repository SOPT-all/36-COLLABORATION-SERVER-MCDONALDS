package org.sopt.repository;

import java.util.List;
import org.sopt.domain.CartItem;
import org.sopt.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemJpaRepository extends JpaRepository<CartItem, Long> {

    @Query("SELECT ci FROM CartItem ci JOIN FETCH ci.menu WHERE ci.user = :user")
    List<CartItem> findAllByUserWithMenu(@Param("user") User user);
}
