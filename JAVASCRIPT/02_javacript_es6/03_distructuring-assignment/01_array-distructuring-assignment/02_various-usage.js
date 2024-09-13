
/* 다양한 사용법 */
let user = {};
[user.firstName, user.lastName] = "Gwansoon Yu".split(" ");

console.log(user);

/* rest(나머지) 요소를 한번에 가져오기 */

/* 6개의 배열 요소를 3개의 변수에 할당 */
let [sign1, sign2, ...rest] = 
        ['양자리', '황소자리', '쌍둥이자리', '게자리', '사자자리', '처녀자리'];

console.log(sign1);
console.log(sign2);
console.log(rest);      // 나머지 요소는 배열로 받아짐

/* 배열 구조 분해 할당을 활용한 변수 교환 */
let student = '유관순';
let teacher = '홍길동';

[student, teacher] = [teacher, student];

console.log(`학생: ${student}, 교사: ${teacher}`);

/* 기본 값을 설정하고 사용할 수도 있다. */
let [firstName5 = '아무개', lastName5 = '김'] = ["길동"];

console.log(firstName5);
console.log(lastName5);