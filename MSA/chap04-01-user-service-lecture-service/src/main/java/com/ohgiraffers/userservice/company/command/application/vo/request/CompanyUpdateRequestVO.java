package com.ohgiraffers.userservice.company.command.application.vo.request;

import lombok.Data;

@Data
public class CompanyUpdateRequestVO {

    private Integer memberId;
    private Boolean approved;

}
