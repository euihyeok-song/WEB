package com.ohgiraffers.restapi.section02.responseentity;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/entity")
public class ResponseEntityTestController {

    /* 설명.
     *  ResponseEntity란?
     *   HttpRequest를 응답하기 위해 httpEntity를 상속받아 HttpStatus, HttpHeaders, HttpBody를 정의하여
     *   사용되는 클래스이다. (필수는 아니지만 관례상 많이 사용하고 있다.)
    */

    /* 설명. Service와 Repository를 만들지 않고 이미 이를 통해서 DB에서 가져와 List에 저장했다고 가정 */
    private List<UserDTO> users;

    public ResponseEntityTestController() {
        this.users = new ArrayList<>();

        users.add(new UserDTO(1, "user01", "pass01", "홍길동", new java.util.Date()));
        users.add(new UserDTO(2, "user02", "pass02", "유관순", new java.util.Date()));
        users.add(new UserDTO(1, "user03", "pass03", "이순신", new java.util.Date()));
    }

    /* 설명. 1. 직접 ResponseEntity 객체 만들기 */
    @GetMapping("users")
    public ResponseEntity<ResponseMessage> findAllUsers(){
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(new MediaType("application","json"
                , Charset.forName("UTF-8")));

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("users", users);

        /* 설명. 응답 바디에 응답메시지, 헤더, 상태코드 까지 다 넣어서 보낼 수 있다. */
        ResponseMessage responseMessage = new ResponseMessage(200,"조회성공!", responseMap);

        /* 설명. ResponseEntity(응답바디, 헤더, 상태코드)*/
        return new ResponseEntity<>(responseMessage, headers, HttpStatus.OK);
    }

    /* 설명. 2. 빌더를 활용한 메소드 체이닝 방식으로 ResponseEntitiy 객체 만들기( 요즘 유행 ) */
    @GetMapping("/users/{userNo}")
    public ResponseEntity<ResponseMessage> findUserByNo(@PathVariable int userNo){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json",
            Charset.forName("UTF-8")));

        /* 설명. 요청 리소스(회원 번호와 일치하는 회원 한명)를 추출 */
        UserDTO foundUser = users
                            .stream()
                            .filter(user -> user.getNo() == userNo)
                            .collect(Collectors.toList()).get(0);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("user",foundUser);

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new ResponseMessage(200,"조회 성공!", responseMap));
    }

    /* 설명. json문자열을 받아 파싱하기(feat. 커멘드 객체 ) */
    /* 설명.
     *   기존에 우리가 배웠던 @ResponseParam과 달리 json 문자열이 핸들러 메소드로 넘어올 때는 @RequestBody를 붙이고,
     *   json 문자열의 각 프퍼티 별로 받을 수 도 있찌만 하나으 ㅣ타입으로 다 받아낼 때는 커맨드 객체(UserDTO)를 활용해야 하며
     *   커맨드 객체는 json 문자열의 프로퍼티 명과 일치해여 한다.
    */
    @PostMapping("users")
    /* 설명. 1. <?>: ResponseBody에 담을 내용이 없을경우 와일드카드 사용
    *       2. @RequstBody*/
    public ResponseEntity<?> registUser(@RequestBody UserDTO newUser){

        /* 설명. auto_increment가 안되니 soft delete를 가정하여 마지막 회원 번호 + 1로 newUser에 추가하기 */
        int lastUserNo = users.get(users.size() - 1).getNo();              // 컬렉션에 쌓인 마지막 회원 번호
        newUser.setNo(lastUserNo + 1);                                      // 마지막 회원 번호 + 1

        users.add(newUser);

        /* 설명. created를 통해 Response Header에 Location이라는 속성으로 생성된 리소스를 찾아갈 경로를 제시할 수 있디. */
        return ResponseEntity
                /* 설명. resource를 찾아가는 경로를 넣어줄 수 있다. - restapi 스럽다. */
                .created(URI.create("/entity/users/" + users.get(users.size() - 1).getNo()))
                .build();               // created를 썼을 때는 .build()를 해야한다.
    }

    @PutMapping("usres/{userNo}")
    public ResponseEntity<?> modifyUser(@RequestBody UserDTO modifyUSer, @PathVariable int userNo){

        UserDTO modifyUser =
                users.stream().filter(user -> user.getNo() == userNo)
                        .collect(Collectors.toList())
                        .get(0);

        modifyUser.setId(modifyUser.getId());
        modifyUser.setPwd(modifyUser.getPwd());
        modifyUser.setName(modifyUser.getName());

        return ResponseEntity
                .created(URI.create("/entity/users/" + userNo))      // Response Header의 Location 정보 제공을 위해
                .build();
    }

    @DeleteMapping("/users/{userNo}")
    public ResponseEntity<?> removeUser(@PathVariable int userNo) {

        UserDTO foundUser =
                users.stream().filter(user -> user.getNo() == userNo)
                        .collect(Collectors.toList())
                        .get(0);

        /* 설명. 객체를 찾아서 알아서 지워준다 */
        users.remove(foundUser);

        return ResponseEntity
                .noContent()        //204번 응답 코드
                .build();
    }
}
