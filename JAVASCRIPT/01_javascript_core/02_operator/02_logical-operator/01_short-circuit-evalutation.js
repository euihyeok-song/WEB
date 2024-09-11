
/* 1. OR의 경우 */
/* || 연산은 연산에 영향을 주는 결과만 남는다 (apple은 truthy한 값임으로, apple만 보고 연산을 끝냄) */
console.log('apple' || ' banana');      
/* ''는 false이지만 truthy한 banana가 있음으로 banana */
console.log('' || 'banana');
console.log('apple' || false);

/* 2. AND의 경우 */
console.log('apple' && 'banana');
/* false가 하나라도 있으면 false가 나오기 때문에, 결과에 영향을 미춤으로 false가 결과로 반환 */
console.log(false && 'banana');
console.log('apple' && false);

/* 3. 단축 평가 구문 */
var num = 3;
num % 2 == 0 && console.log('짝수입니다.');     // &&는 왼쪽이 맞으면 오른쪽이 실행
num % 2 == 0 || console.log('홀수입니다.');     // ||은 왼쪽이 틀리면 오른쪽이 실행