
/* 정규 표현식 - 회원가입시 회원의 입력값에 유효성 검사시 많이 사용 */

/* /j/는 Regex 객체 */
let regex = /j/i;               // 패턴: j, 플래그 옵션: i => 대소문자 구별 없이(ignore case) j라는 값이 들어있는지 판단
regex = new RegExp('j','i');    // 위와 동일
regex = new RegExp(/j/,'i');    // ...
regex = new RegExp(/j/i);       // ...

let target= 'JavaScript';

/* test 메소드: 정규표현식 regex의 패턴을 확인하여 매칭 결과를 boolean으로 반환 */
console.log(regex.test(target));

