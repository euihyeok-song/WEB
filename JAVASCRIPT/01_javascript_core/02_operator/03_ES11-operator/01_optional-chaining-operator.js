
/* ES11(ECMA11(2020))에서 도입된 연산자(?.)로 연산자의 좌항이 NULL 또는 undefined인 경우
    에러 대신 undefined를 반환하고 그렇지 안흥면 우항의 프로퍼티 참조를 이어간다.
*/

// var obj = null;
var obj =  {
    'value': 'abc'
}

/* ?. => null이면 undefined이고, null이 아니면 객체에 담겨있는 값이 나옴 */
var val = obj?.value;       // npe(Null Ponter Exception) 방지 코드
console.log(val);

var str = '';
var len = str?.length;
console.log(len);               // 0 => 빈 문자열 ''은 null이 아니여서 객체로써 접근은 가능하지만, 크기는 0이다.