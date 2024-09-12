
const arr = [
    '홍길동',
    20,
    true,
    null,
    undefined,
    NaN,
    Infinity,
    [],
    {},
    function() {}
];

console.log(arr);

/* 
    writable - true면 값을 수정할 수 있다.
    enumerable - true면 반복문에서 반복할 수 있다.
    configurable - ture면 프로퍼티가  삭제 가능하다.
*/
console.log(Object.getOwnPropertyDescriptors([1,2,3]));