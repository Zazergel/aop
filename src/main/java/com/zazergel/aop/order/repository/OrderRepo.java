package com.zazergel.aop.order.repository;

import com.zazergel.aop.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {
}
