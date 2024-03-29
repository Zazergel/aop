package com.zazergel;

import com.zazergel.aop.AopApplication;
import com.zazergel.aop.aspect.LoggingAspect;
import com.zazergel.aop.order.dto.OrderDto;
import com.zazergel.aop.order.mapper.OrderMapper;
import com.zazergel.aop.order.model.Order;
import com.zazergel.aop.order.repository.OrderRepo;
import com.zazergel.aop.order.service.OrderService;
import com.zazergel.aop.user.model.User;
import com.zazergel.aop.user.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = AopApplication.class)
@AutoConfigureTestDatabase(replace = Replace.ANY)
@ActiveProfiles("test")
@Import(LoggingAspect.class)
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @MockBean
    private OrderRepo orderRepo;

    @MockBean
    private UserRepo userRepo;
    @MockBean
    private OrderMapper orderMapper;

    @Test
    void testCreateOrder() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        OrderDto orderDto = new OrderDto("Test orderDto", userId);
        Order order = new Order();

        when(userRepo.findById(userId)).thenReturn(Optional.of(user));

        when(orderMapper.toOrder(any(OrderDto.class), any(User.class))).thenReturn(order);

        when(orderRepo.save(any(Order.class))).thenReturn(order);


        orderService.create(orderDto);
        
        verify(userRepo).findById(userId);
        verify(orderMapper).toOrder(any(OrderDto.class), any(User.class));
        verify(orderRepo).save(any(Order.class));
    }

    @Test
    void testGetOrderById() {
        Long orderId = 1L;
        Order order = new Order();
        order.setId(orderId);
        OrderDto orderDto = new OrderDto("Test OrderDto", 1L);

        when(orderRepo.findById(orderId)).thenReturn(Optional.of(order));
        when(orderMapper.toOrderDto(any(Order.class))).thenReturn(orderDto);

        orderService.getOrderById(orderId);

        verify(orderRepo).findById(orderId);
        verify(orderMapper).toOrderDto(any(Order.class));
    }

    @Test
    void testUpdateOrder() {
        Long orderId = 1L;
        OrderDto orderDto = new OrderDto("Test OrderDto", 1L);
        Order order = new Order();
        order.setId(orderId);

        when(orderRepo.findById(orderId)).thenReturn(Optional.of(order));
        when(orderRepo.save(any(Order.class))).thenReturn(order);

        orderService.update(orderId, orderDto);

        verify(orderRepo).save(any(Order.class));
    }

    @Test
    void testDeleteOrder() {
        Long orderId = 1L;

        doNothing().when(orderRepo).deleteById(orderId);

        orderService.delete(orderId);

        verify(orderRepo).deleteById(orderId);
    }
}