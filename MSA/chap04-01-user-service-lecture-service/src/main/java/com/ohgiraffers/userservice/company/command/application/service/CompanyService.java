package com.ohgiraffers.userservice.company.command.application.service;

import com.varchar6.petcast.domain.company.command.application.dto.request.CompanyRegistRequestDTO;
import com.varchar6.petcast.domain.company.command.application.vo.response.CompanyRegistResponseVO;

public interface CompanyService {
    CompanyRegistResponseVO registerCompany(CompanyRegistRequestDTO companyRegistRequestDTO);
}
