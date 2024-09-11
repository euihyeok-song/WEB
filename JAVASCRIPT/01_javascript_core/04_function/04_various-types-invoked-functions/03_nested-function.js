/*
    함수 내부에 정의 된 함수를 중첩 함수 또는 내부 함수라고 한다. 
    중첩 함수를 포함하는 함수를 외부 함수라고 한다. 
    일반적으로 중첩 함수는 자신을 포함하는 외부 함수를 돕는 헬퍼(Helper)함수의 역할을 한다.
*/

function outer() {
    var outerVal = '외부함수';

    function inner(){
        var innerVal = '내부함수';

        /* 외부함수의 지역변수를 내부함수의 변수로 사용이 가능하다. */
        console.log(outerVal,innerVal);    
    }

    inner();
}

outer();