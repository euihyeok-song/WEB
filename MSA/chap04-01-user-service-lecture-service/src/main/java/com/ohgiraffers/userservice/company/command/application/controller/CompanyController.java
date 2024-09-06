package com.ohgiraffers.userservice.company.command.application.controller;

import com.varchar6.petcast.domain.company.command.application.dto.request.CompanyRegistRequestDTO;
import com.varchar6.petcast.domain.company.command.application.dto.response.CompanyRegistResponseDTO;
import com.varchar6.petcast.domain.company.command.application.service.CompanyService;
import com.varchar6.petcast.domain.company.command.application.vo.request.CompanyRegistRequestVO;
import com.varchar6.petcast.domain.company.command.application.vo.response.CompanyRegistResponseVO;
import com.varchar6.petcast.domain.member.command.application.vo.response.MemberRegistResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "commandCompanyController")
@RequestMapping("/api/v1/company")
@Slf4j
public class CompanyController {

    private final CompanyService companyService;
    private final ModelMapper modelMapper;

    @Autowired
    public CompanyController(CompanyService companyService,
                             ModelMapper modelMapper) {
        this.companyService = companyService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/regist")
    public ResponseEntity<CompanyRegistResponseVO> registCompany(@RequestBody CompanyRegistRequestVO newCompany){

        CompanyRegistRequestDTO companyRegistRequestDTO = modelMapper.map(newCompany, CompanyRegistRequestDTO.class);

        CompanyRegistResponseDTO companyRegistResponseDTO = companyService.registerCompany(companyRegistRequestDTO);

        CompanyRegistResponseVO responseMember = modelMapper.map(companyRegistResponseDTO, CompanyRegistResponseVO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseMember);
    }
}
