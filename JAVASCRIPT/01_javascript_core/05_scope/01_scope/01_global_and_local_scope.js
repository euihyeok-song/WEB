
var x = 'global x';
var y = 'global y';

/* nested 하게 중첩되는 함수 */
function outer(){
    var z = "outer's local z";

    console.log(x);
    console.log(y);
    console.log(z);

    function inner(){
        var x = "inner's local x";

        console.log(x);     // 변수를 가까운 변수부터 찾음
        console.log(y);
        console.log(z);
    }

    inner();
}

outer();

/* 
    스코프 체인 

    inner 스코프 -> outer 스코프 -> 전역 스코프 순으로 우선순위가 결정된다. 
    모든 스코프는 하나의 계층적 구조로 연결되며 자바스크립트 엔진은 스코프 체인을 통해
    변수를 참조하는 코드의 스코프에서 상위 스코프 방향(하 -> 상)으로 이동하며 선언된 변수를 검색한다.
    (하위 스코프에서 유효한 변수를 상위 스코프에서 참조할 수 없다.)
*/

/*
    스코프
    1. 블록 레벨 스코프(블록 단위의 중괄호 - ex. for(){} , if(){}. ...)
    2. 함수 레벨 스코프(함수에 있는 중괄호 - ex. function(){}) => var는 함수 레벨 스코프만 지원
*/