/* 스프레드 문법, 전개(펼치는 용도) 문법 */

let arr = [10, 30, 20];
console.log(arr);         // [10, 30, 20]
console.log(...arr);      // 배열 내부에 있는 요소들을 흩뿌려주는 느낌 => 10, 30, 20

console.log(`가장 큰 값: ${Math.max(10, 30, 20)}`);
console.log(`가장 큰 값: ${Math.max(...arr)}`);

/* 배열을 결합(concat)해서 하나의 배열로 만듦 */
let arr1 = [10, 30, 20];
let arr2 = [100, 300, 200];

console.log([...arr1, ...arr2]);
console.log([10, ...arr1, -1, ...arr2, 2]);