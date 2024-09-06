package com.ohgiraffers.userservice.company.command.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyRegistRequestDTO {

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
