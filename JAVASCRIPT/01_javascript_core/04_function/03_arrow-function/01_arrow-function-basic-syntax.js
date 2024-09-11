var message;

message = function() {
    return "Hello World!";
};

console.log(message());

/* () -> {} 는 java에서는 익명 객체와 익명함수를 한번에 선언하는 개념이 람다식 */
/* =>는 이름이 없을 경우 함수를 람다식으로 선언 (객체 생성) */
message = () => {
    return "Arrow Function!";
};

console.log(message());

/* 람다식: {}와 return을 생략이 가능하다 */
/* 중괄호{} 생략: 함수 내부 실행 구문이 하나만 있는 경우 */
/* return 생략: 하나의 값 또는 하나의 값으로 취급되는 표현식일 경우 암묵적으로 return 시킴 */
message = () => "Arrow Function2!";
message = () => 1 + 2;
console.log(message());

/* 소괄호 생략 X: 함수 내부 실행 구문이 0개거나 2개 이상인 경우 */
message = (val1, val2) => "Arrow" + val1 + val2;
console.log(message("Function", '!'));

/* 소괄호 생략: 매개변수가 하나만 있을 경우 생략 가능 */
message = val1 => "Arrow" + val1;
console.log(message("Fumctions are Good!"));