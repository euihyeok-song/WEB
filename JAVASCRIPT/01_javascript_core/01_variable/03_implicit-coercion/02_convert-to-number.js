console.log(10 - '5');
console.log(10 * '5');
console.log(10 / '5');
console.log(10 %'JavaScript');
console.log(10 > '5');

console.log('양수로 바꿔서 Number로 변환')
console.log(+'');                       // Falsy한 값
console.log(+'1');
console.log(+'JavaScript');
console.log(+true);
console.log(+false);                    // Falsy한 값
console.log(+null);                     // Falsy한 값
console.log(+undefined);
// console.log(+Symbol());
console.log(+{});
console.log(+[]);                       // Falsy한 값
console.log(+function(){})

/* 빈 문자열, 빈 배열, null, false는 0으로 true는 1로 변환 */


