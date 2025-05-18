package org.sopt.repository;

import org.sopt.domain.Order;
import org.sopt.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderJpaRepository extends JpaRepository<Order, Long> {

    Optional<Order> findTopByUserOrderByCreatedAtDesc(User user);

}
