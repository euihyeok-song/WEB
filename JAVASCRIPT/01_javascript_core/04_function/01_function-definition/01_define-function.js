/* function-declaration(함수 선언문) */
function hello(name){
    return `${name}님 안녕하세요!`;
}

console.log(hello('홍길동'));

/* function-expression(함수 표현식) */
/* 이름이 없는 함수를 먼저 설정 후, hello2라는 변수에 넣어줌 */
/*
    자바스크림트의 함수는 객체 타입의 값으로 값의 성질을 갖는 객체를 "일급 객체" 라고 한다.
    따라서 함수 리터럴로 생성한 함수 객체를 변수에 할당할 수 있다.
*/
var hello2 = function(name) {
    return `${name}님 반갑습니다!`;
};      // 값으로 취급됨으로 ; 필요

console.log(hello2('강감찬'));