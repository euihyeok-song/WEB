/* 
    Symbol은 ES6에서 추가된 7번째 원시 타입이다.
    주요 특징:
        1. 변경 불가능한 원시 타입의 값
        2. 각 Symbol 값은 고유하며 다른 어떤 값과도 충돌하지 않음
        3. 객체의 유일한 프로퍼티 키를 만들기 위해 주로 사용
        4. Symbol 함수를 통해 생성
*/

var sym1 = Symbol();
var sym2 = Symbol();
/* 선언한 원시타입 하나하나는 고유하다 */
console.log(sym1 == sym2);

var sym3 = Symbol('설명');
var sym4 = Symbol('설명');
console.log(sym3 == sym4);
console.log(sym3.description);      // 내부에 들어있는 값을 출력
console.log(sym4.description);

/* 아무런 속성이 없는 객체(literal 객체) 생성 - 자바와는 다르게 클래스 없이도 객체를 만들어 낼 수 있다. */
var obj = {};              // 리터럴 객체(추후에 따로 속성(프로퍼티)을 동적으로 추가 및 제거할 수 있다.)
obj[sym3] = '값1';          // 대괄호([])는 속성에 접근 또는 없으면 추가하는 개념
obj[sym4] = '값2';

console.log(obj);
console.log(obj[sym4]);
console.log(typeof sym4);

/* 숨겨진 프로퍼티 - 이런 용도로 많이 쓰임 */ 
var hidden = Symbol('숨겨진 프로퍼티');
obj[hidden] = '비밀 값';
obj['name'] = '홍길동';
console.log(obj);
console.log(obj[hidden]);
console.log(Object.keys(obj));    // Object.keys()의 인수에 객체를 넣으면 객체가 가진 프로퍼티를 보여주는 함수 (Symbol로 넣어준 값은 보이지 X)



