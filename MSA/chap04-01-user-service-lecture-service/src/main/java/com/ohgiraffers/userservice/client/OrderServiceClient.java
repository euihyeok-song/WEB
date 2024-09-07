package com.ohgiraffers.userservice.client;

import com.ohgiraffers.userservice.config.FeignClientConfig;
import com.ohgiraffers.userservice.vo.ResponseOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/* 설명. user 입장에서 통신하고자 하는 서버의 "이름"으로 통신함 + url은 gateway의 주소를 넣어줌 */
//@FeignClient(name="swcamp-order-service",url="localhost:8000")
@FeignClient(name="swcamp-order-service", url="localhost:8000", configuration = FeignClientConfig.class)
public interface OrderServiceClient {

    /* 설명. 타입은 order 서비스로 부터 받아올 응답의 반환형을 써준다 */
    @GetMapping("/order-service/orders/users/{userId}")
    List<ResponseOrder> getUserOrders(@PathVariable String userId);
}
