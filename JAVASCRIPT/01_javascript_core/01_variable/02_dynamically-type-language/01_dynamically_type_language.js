/* 동적 언어로 대입할 때 마다 값이 변하고, 그에 맞게 자료형이 바뀐다 */
var test;
console.log(typeof test);

test = 1;
console.log(typeof test);

test = 'JavaScript';
console.log(typeof test);

test = true;
console.log(typeof test);

test = Symbol();
console.log(typeof test);

test = {};
console.log(typeof test);

test = [];
console.log(typeof test);

/* function ()은 일급객체 */
test = function () {};
console.log(typeof test);