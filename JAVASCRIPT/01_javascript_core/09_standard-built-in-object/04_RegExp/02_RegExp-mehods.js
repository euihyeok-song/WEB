
const target = 'Java JavaScript';

/* exec: 인수로 전달받은 문자열에 대해 정규 표현식의 패턴을 검색하여 매칭 결과를 배열로 반환 */
console.log(/va/.exec(target));         // 가장 먼저 찾은 va의 인덱스를 반환
console.log(/hello/.exec(target));

/* test: 정규 표현식 패턴을 검색하여 매칭 결과를 boolean으로 반환 */
console.log(/va/.test(target));

/* match: 대상 문자열과 인수로 전달 받은 정규 표현식의 매칭 결과를 배열로 반환 */
console.log(target.match(/va/));        // 문자열에서 제공하는 match 함수