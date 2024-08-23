package com.ohgiraffers.springdatajpa.menu.aggregate.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="tbl_category")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Category {

    @Id
    @Column(name="category_code")
    private int categoryCode;

    @Column(name="category_name")
    private String categoryName;

    @Column(name="ref_category_code")
    private Integer refCategoryCode;
}
