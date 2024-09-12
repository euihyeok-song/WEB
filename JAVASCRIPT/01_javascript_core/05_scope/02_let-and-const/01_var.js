
/* 1. 변수 중복 선언 허용 */
var msg = '안녕하세요';
console.log(msg);

var msg = '안녕히 가세요';
console.log(msg);

/* 값을 할당(초기화)되지 않는 중복 변수는 무시 */
var msg;
console.log(msg);


/* 2. 함수 레벨 스코프를 가지는 문제 */
/* 앞서 살펴봄

/* 3. 변수 호이스팅 */
/* 변수를 선언한 부분은 끌어올려지는 호이스팅이 발생해 코드 가독성 및 코드의 흐름에 지장을 줌 */
console.log(test);     // 2

test = '반갑습니다';      // 3
var test;              // 1

