package com.ohgiraffers.userservice.dto;

import com.ohgiraffers.userservice.vo.ResponseOrder;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {

    /* 설명. ModelMapper가 인지해주기 위해서 DTO에서 entity나 vo에서 선언해준 이름과 동일하게 맞춰줘야 함 */
    private String email;
    private String name;
    private String pwd;

    /* 설명. ServiceImpl 계층에서 고유 회원 생성하며 UserId의 개념을 추가(응답할 때도 활용) */
    private String userId;

    /* 설명. FeignClient 이후 (주문내역 담기) */
    private List<ResponseOrder> orders;


}
