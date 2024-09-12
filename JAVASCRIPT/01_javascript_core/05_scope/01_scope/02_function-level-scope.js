
/*
    C, 자바 등 대부분의 프로그래밍 언어는 모든 코드 블록(if, for, while, try/catch 등)이
    지역 스코프를 만든느 블록 레벨 스코프(block level scope)를 가진다. 
    하지만 var 키워드로 선언 된 변수는 함수의 코드 블록(함수 몸체)만을 지역 스코프로 인정하는
    함수 레벨 스코프(function level scope)를 가진다. 

    이후 이 부분을 통일하기 위해 ES6에서 도입된 let, const 키워드를 다루게 된다.
*/


/* var는 동일한 변수 명을 중복 선언 가능 */
var i = 0;

/* var는 함수 레벨 스코프이므로, 블록 레벨 스코프는 무시한다. */ 
for(var i = 0; i < 10; i++){

}

console.log(i);