
const str = 'JavaScript';
console.log(Object.getOwnPropertyDescriptors(str));             // String은 수정 불가
console.log(str.indexOf('a'));
console.log(str.indexOf('b'));
console.log(str.indexOf('a', 2));

if(str.indexOf('a') !== -1) console.log('a가 있다');
console.log('--------------------------------');

console.log(str.includes('a'));
console.log(str.includes('b'));
console.log(str.includes('a', 2));

if(str.includes('a')) console.log('a가 있다');  
console.log('--------------------------------');

console.log(str.search(/a/));
console.log(str.search(/b/));
console.log('--------------------------------');

console.log(str.startsWith('Ja'));
console.log(str.startsWith('va', 2));
console.log(str.endsWith('pt'));
console.log(str.endsWith('va', 4));
console.log('--------------------------------');

for(let i = 0; i < str.length; i++)
    console.log(str.charAt(i));
console.log('--------------------------------');

console.log(str.substring(1,4));
console.log(str.substring(1));
console.log(str.substring(4,1));
console.log(str.substring(-1));
console.log(str.substring(1,20));
console.log('--------------------------------');

console.log(str.slice(1,4));
console.log(str.slice(1));
console.log(str.slice(4,1));
console.log(str.slice(-1));
console.log(str.slice(1,20));
console.log('--------------------------------');

console.log(str.toUpperCase());
console.log(str.toLowerCase());
console.log('--------------------------------');

const str2 = '   JavaScript   ';
console.log(str2.trim());
console.log('--------------------------------');

console.log(str.replace('Java', 'Type'));
console.log(str.replace('a', 'b'));
console.log(str.replace(/j/ig,'Z'));
console.log('--------------------------------');

const str3 = 'Hello, Everyone! Nice to see you again.';
console.log(str3.split(' '));
console.log(str3.split(''));
console.log(str3.split());
console.log(str3.split(' ', 5));