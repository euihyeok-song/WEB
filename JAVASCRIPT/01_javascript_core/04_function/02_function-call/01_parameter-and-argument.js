function hello(name){
    console.log('넘어온 값: ', name);

    /* 모든 인수(전달 인자)는 함수 안에서 암묵적ㅇ\으로 arguments 객체의 프로퍼티로 보관된다. */
    console.log('arguments: ',arguments);
    console.log('두번째 인수: ', arguments[1]);

    return `${name}님 안녕하세요!`;
}

var result = hello('홍길동');
console.log(result);

result = hello();
console.log(result);

result = hello('이순신', '유관순');     // 먼저 넘어오는 값만 출력됨
console.log(result);