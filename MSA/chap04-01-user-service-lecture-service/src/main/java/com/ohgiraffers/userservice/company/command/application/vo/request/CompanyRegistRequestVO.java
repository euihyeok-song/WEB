package com.ohgiraffers.userservice.company.command.application.vo.request;

import lombok.Data;

@Data
public class CompanyRegistRequestVO {

    private int id;
    private String name;
    private String address;
    private Integer employeeNumber;
    private Integer career;
    private String license;
    private String introduction;
    private String contactableTime;
    private Integer memberId;

}
