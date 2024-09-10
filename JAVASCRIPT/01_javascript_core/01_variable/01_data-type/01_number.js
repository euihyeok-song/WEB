/*
    data-type은 값의 종류를 말하며 자바스크립트(ES6 - 에크마 스크립트)는 7개의 데이터 타입  - 동적 데이터 타입 언어
    (number, string, boolean, undefined(실제 아무 값도 업음), null(의도적으로 없음), symbol(중복되지 않도록 구분, 동등객체비교), object)를 제공한다.
    (symbol, object만 함수, 나머지는 모두 객체) 
*/

/* 01. 숫자 타입 */
/* 정수, 실수, 음수 모두 숫자(number) 타입이다.(내부적으로는 실수로만 인식) */
var integer = 10;       /* ASI로 ;를 안적어도 자동으로 적어주지만, 직접 적어주는 것을 권장 */
var double = 5.5;
var negative = -10;

console.log(typeof integer);
console.log(typeof double);
console.log(typeof negative);

console.log(10/4);             // 내부적으로 실수로만 인식

console.log(10/0);              // 양의 무한대: Infinity
console.log(10/-0);             // 음의 무한대: -Infinity

console.log(1*'문자열');        // NaN(Not a Number)
console.log("문자열");          // "" 와 ''는 동일 => string 취급


