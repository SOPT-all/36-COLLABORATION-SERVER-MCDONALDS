package org.sopt.repository;

import org.sopt.domain.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailJpaRepository extends JpaRepository<OrderDetail, Long> {
}
