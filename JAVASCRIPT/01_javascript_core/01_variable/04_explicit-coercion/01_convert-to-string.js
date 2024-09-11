
/* JS는 프로토타입(prototype) 기반의 객체 지향 프로그래밍 언어이다 */

/* 1. String 생성자 함수를 new 연산자 없이 호출해서 하는 방법 */
console.log(String(10));
console.log(String(NaN));
console.log(String(Infinity));
// console.log(new String(Infinity));
console.log(String(true));
console.log(String(false));

/* 2. Object.prototype.toString 메소드를 호출해서 하는 방법 */
console.log((10).toString());
// console.log(10 + '');                        // 위와 동일하지만 별로 좋지 않은 코드
console.log((NaN).toString());
console.log((Infinity).toString());
console.log((true).toString());
console.log((false).toString());