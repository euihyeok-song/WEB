/* return 이후에는 실행되지 않는다. */
function hello(name){
    return `${name}님 안녕?!`;
    console.log(name);         
}

console.log(hello('유관순'));

/* 반환이 없으면 undefined를 반환한다. */
function func() {
    console.log('함수가 호출됨');
}

console.log(func());