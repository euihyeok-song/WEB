/* null 병합 연산자(??) - null이나 undefiend일 경우 우항도 출력됨 */
/* 
    좌항의 피연산자가 null이거나 undefined라면 우항의 결과가 남고(함수면 실행됨),
    그렇지 않으면 좌항이 남는다(함수면 실행)).
*/

var test = null ?? '기본 값';
console.log('test: ', test);

var value1 =  '' || '기본 값';
var value2 =  '' ?? '기본 값';
console.log('value1',value1);
console.log('value2',value2);