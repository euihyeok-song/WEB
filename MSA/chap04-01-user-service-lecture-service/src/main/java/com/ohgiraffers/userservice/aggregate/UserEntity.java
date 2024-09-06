package com.ohgiraffers.userservice.aggregate;

import jakarta.persistence.*;
import lombok.Data;

/* 설명. auto-ddl에 의해서 자동으로 반영되서 entity 만들어짐 */
@Data
@Entity
@Tablwe(name="tbl_member")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* 설명. Column 내부 설정은 모델링이 완료되었다면 굳이 생성할 필요 X */
    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
//    private String pwd;
    private String encryptedPwd;        // 암호화 이후 @Data를 통해 setter를 자동으로 만들어주기 떄문에, 이름을 맞춰줌

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, unique = true)
    private String userId;                      // 회원 가입 시 생성될 고유 회원 번호(닉네임 개념)
}
