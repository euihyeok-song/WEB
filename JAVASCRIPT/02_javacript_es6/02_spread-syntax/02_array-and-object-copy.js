
/* 스프레드 문법을 활용한 배열 및 객체 복사 */

/* 1. 배열 복사 */
let arr = [10, 30, 20];
let arrCopy = [...arr];         // 스프레드 문법을 활용한 깊은 복사

console.log(arr);
console.log(arrCopy);
console.log(arr === arrCopy);

/* 2. 객체 복사 */
let obj = {
    name: '홍길동',
    age: 20, 
    addr: '서울시',
    hobbies: ['축구', '농구']
};

let objCopy = {...obj};

console.log(obj);
console.log(objCopy);
console.log(obj === objCopy);

/* 추가적으로 나머지 연산 개념으로 활용도 가능하다. */
let classNum = 30;                           // 기존 obj에 없고 추가되는 개념
let name = '강감찬';                         // 기존과 다른 name
let newObj = {classNum, ...obj, name};      // 나머지(rest) 연산자(순서를 유의해서 하자.)
console.log(newObj);
