package com.ohgiraffers.userservice.company.command.application.vo.request;

import lombok.Data;

@Data
public class CompanyDeleteRequestVO {

    private int id;
    private Boolean active;
}
