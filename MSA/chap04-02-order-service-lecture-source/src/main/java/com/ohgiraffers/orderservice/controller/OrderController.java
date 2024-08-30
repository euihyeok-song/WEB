package com.ohgiraffers.orderservice.controller;

import com.ohgiraffers.orderservice.dto.OrderDTO;
import com.ohgiraffers.orderservice.service.OrderService;
import com.ohgiraffers.orderservice.vo.ResponseOrderVO;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders/users/{userId}")
    public ResponseEntity<List<ResponseOrderVO>> getUserOrders(@PathVariable int userId) {
        List<OrderDTO> orderDTOList = orderService.getOrderByUserId(userId);

        List<ResponseOrderVO> returnValue = orderDTOToResponseOrder(orderDTOList);

        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }

    /* 설명. Mapper를 쓰지 않는 방식 사용 */
    private List<ResponseOrderVO> orderDTOToResponseOrder(List<OrderDTO> orderDTOList) {

        List<ResponseOrderVO> responseList = new ArrayList<>();

        for (OrderDTO orderDTO : orderDTOList) {
            ResponseOrderVO responseOrder = new ResponseOrderVO();
            responseOrder.setOrderDate(orderDTO.getOrderDate());
            responseOrder.setOrderTime(orderDTO.getOrderTime());
            responseOrder.setOrderMenus(orderDTO.getOrderMenus());

            responseList.add(responseOrder);
        }

        return responseList;
    }
}
