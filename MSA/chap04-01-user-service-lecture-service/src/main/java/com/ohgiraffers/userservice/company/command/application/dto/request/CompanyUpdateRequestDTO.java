package com.ohgiraffers.userservice.company.command.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyUpdateRequestDTO {

    private Integer memberId;
    private Boolean approved;

}
