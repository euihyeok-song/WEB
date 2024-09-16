
/* 배열 구조 분해 할당 => 배열 내부의 요소를 내가 원하는 원소 내부에 담는다는 개념 */

let nameArr = ["Gildong", "Hong"];
// let firstName = nameArr[0];
// let lastName = nameArr[1];

/* 우측 nameArr가 배열이자, 해당 배열에서 값을 꺼내서, firstName, lastName에 값을 할당 */
let [firstName, lastName] = nameArr;

console.log('firstName',firstName);
console.log('lastName',lastName);

/* 문자열에서 구분자를 기준으로 구분하여 각각의 변수에 담는 한줄 코드 */
let [firstName2, lastName2] = 'Saimdang Shin'.split(' ');  // 배열로 만들어 줌
let [firstName3, lastName3] = 'Saimdang Shin'.match(/[a-z]+/ig); //대소문자 무시하여 알파벳 찾아 배열로 반환

console.log('firstName2',firstName2);
console.log('lastName2',lastName2);
console.log('firstName3',firstName3);
console.log('lastName3',lastName3);

/* 구조분해 할당 시 쉼표를 활용하여 불필요한 배열 요소를 버릴 수도 있다. */
let [firstName4, , lastName4] = ['firstName', 'middleName', 'lastName'];
console.log(firstName4, lastName4);         // 0번 index와 2번 index만 뽑아서 배열에 담음