package com.ohgiraffers.userservice.company.command.application.service;

import com.varchar6.petcast.domain.company.command.application.dto.request.CompanyRegistRequestDTO;
import com.varchar6.petcast.domain.company.command.application.vo.response.CompanyRegistResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service(value="commandCompanyServiceImpl")
public class CompanySerivceImpl implements CompanyService{


    @Override
    public CompanyRegistResponseVO registerCompany(CompanyRegistRequestDTO companyRegistRequestDTO) {
        return null;
    }
}
