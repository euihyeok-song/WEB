
/* 콜백함수(다른 함수에게 넘어가 인수로 쓰이는 함수) 1 */
function increase(value){
    return value + 1;
}

/* 콜백함수 2 */
function decrease(value){
    return value - 1;
}

/* 함수를 값을 가진 객체로 인지하기 때문에 함수에 함수를 인자로 넘길 수도 있다 */
/* 고차함수: 콜백함수를 활용하는 함수 */
function apply(func, value){
    return func(value);
}

console.log(apply(increase, 5));        // increase 함수가 콜백
console.log(apply(decrease, 5));        // decrease 함수가 콜백

/* (콜백함수)를 기준으로 고차함수(sort)가 정렬해주는 개념 */
console.log([3, 2, 1, 5, 4].sort(function(left,right) {return right - left}));
/* 익명함수는 arrow function으로 표현 가능 */
console.log([3, 2, 1, 5, 4].sort((left,right) => right - left));

/* 비동기 처리(이벤트, 타이머, ajax, fetch, ...)에 활용되는 중요한 패턴 */
