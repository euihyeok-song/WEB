/* 10(정수)이 "20"(문자열)을 만나서 암묵적 형변환(implicit-coercion)이 일어남 */
console.log(10 + "20");

console.log(`10 + 20: ${10 + 20}`);

console.log(1 + '');
console.log(NaN + '');
console.log(Infinity + '');
console.log(true + '');
console.log(null + '');
console.log(undefined + '');
// console.log(Symbol() + '');
console.log({} + '');
console.log([1, 2] + '');
console.log(function(){} + '');