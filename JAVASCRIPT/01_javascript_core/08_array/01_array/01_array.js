
/*
    배열(Array) - iterable +  length 프로퍼티
                자바기반의 배열 + 컬렉션의 특징 가짐 (모든 칸이 모두 같은 자료형일 필요 X)
*/


/* 1. 배열 리터럴을 통해 배열 생성 */
const arr = ['바나나', '복숭아', '키위']

console.log(arr);
console.log(arr.length);

/* 2. 배열 빌트인 생성자 함수 */
const arr2 = Array();
console.log(arr2);
console.log(arr2.length);

const arr3 = Array(10);
console.log(arr3);
console.log(arr3.length);

const arr4 = Array(1, 2, 3);
console.log(arr4);

/* 배열의 인덱스 개념 프로퍼티와 length 프로퍼티를 활용해 반복문을 돌릴 수 있다.(iterable) */
for(let i = 0; i < arr4.length; i++){
    console.log(arr4[i]);
}

console.log(typeof arr4);

