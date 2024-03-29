package com.zazergel.aop.order.service;

import com.zazergel.aop.exception.NotFoundException;
import com.zazergel.aop.order.dto.OrderDto;
import com.zazergel.aop.order.mapper.OrderMapper;
import com.zazergel.aop.order.model.Order;
import com.zazergel.aop.order.repository.OrderRepo;
import com.zazergel.aop.user.model.User;
import com.zazergel.aop.user.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final UserRepo userRepo;
    private final OrderRepo orderRepo;
    private final OrderMapper orderMapper;


    @Transactional
    public Order create(OrderDto orderDto) {
        User user = userRepo.findById(orderDto.getUserId())
                .orElseThrow(() -> new NotFoundException("User with id" +
                                                         orderDto.getUserId() + " does not exist!"));
        return orderRepo.save(orderMapper.toOrder(orderDto, user));
    }

    public OrderDto getOrderById(Long id) {
        return orderMapper.toOrderDto(findOrderById(id));
    }

    @Transactional
    public OrderDto update(Long id, OrderDto orderDto) {
        Order order = findOrderById(id);

        if (orderDto.getDescription() != null) {
            order.setDescription(orderDto.getDescription());
        }

        order.setStatus("UPDATED");

        return orderMapper.toOrderDto(orderRepo.save(order));
    }


    @Transactional
    public void delete(Long id) {
        orderRepo.deleteById(id);
    }

    private Order findOrderById(Long id) {
        return orderRepo.findById(id).orElseThrow(() -> new NotFoundException("Order does not exist!"));
    }

}
