/*
    var 키워드로 선언한 변수는 암묵적으로 undefined로 초기화 되어 있다.
    변수를 선언한 이후 값을 할당하지 않는 변수는 undefined인데 개발자가
    의도적으로 변수에 할당하는 것은 본래 취지와 어긋나고 혼란을 줄 수 있으므로 지양한다.
*/

/* 값을 입력해서 넣어주지 않는 경우 */
var undef;
console.log(typeof undef);

/* null */
/* 변수에 값이 없다는 것을 의도적으로 명시할 때 사용한다. */
var nullVal = 'something';
nullVal = null;
console.log(nullVal);