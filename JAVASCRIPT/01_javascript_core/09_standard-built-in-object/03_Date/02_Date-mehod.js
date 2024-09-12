
const now = Date.now();
console.log(new Date(now));

const date = new Date();

console.log(Object.getOwnPropertyDescriptors(date));
console.log(date.getFullYear());
console.log(date.getMonth() + 1);           // month는 +1을 해준다.
console.log(date.getDate());
console.log(date.getDay());                 // 0 ~ 6까지 요일 체계가 나옴(0:일요일, 1:월요일)
console.log(date.getHours());
console.log(date.getMinutes());
console.log(date.getSeconds());
console.log(date.getMilliseconds());

date.setFullYear(2020);
date.setMonth(9-1);
date.setDate(12);
date.setHours(12);
date.setMinutes(33);
date.setSeconds(10);
console.log(date);
console.log(date.getDay());