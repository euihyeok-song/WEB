package com.ohgiraffers.userservice.company.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "tbl_company")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "employee_number", nullable = false)
    private Integer employeeNumber;

    @Column(name = "career", nullable = false)
    private Integer career;

    @Column(name = "license")
    private String license;

    @Column(name = "introduction")
    private String introduction;

    @Column(name = "contactable_time")
    private String contactableTime;

    @Column(name = "created_at", nullable = false)
    private String createdAt;

    @Column(name = "updated_at", nullable = false)
    private String updatedAt;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @Column(name = "approved", nullable = false)
    private Boolean approved;

    @Column(name = "member_id", nullable = false)
    private Integer memberId;
}
