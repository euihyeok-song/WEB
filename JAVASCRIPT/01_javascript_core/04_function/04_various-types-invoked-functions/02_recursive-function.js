function factorial(n) {
    if(n <= 1) return 1;
    return n * factorial(n-1);
}

console.log(factorial(1));  // 1! = 1
console.log(factorial(2));  // 2! = 2 * 1
console.log(factorial(3));  // 3! = 3 * 2 * 1
console.log(factorial(4));  // ...
console.log(factorial(5));

/* 재귀함수는 사용되는 변수의 갯수는 적으나 , 작성 시 stack overflow를 유의해야 한다. */ 