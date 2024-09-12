
/* String은 읽기 전용이다. 배열과는 다르게 수정이 불가능 하다. */
const obj = new String('홍길동');
obj[0] = '김';              // 수정 불가
console.log(obj);
console.log(obj[0]);        // java의 charAt() 메소드 대신 인덱스로 바로 접근 가능 
console.log(obj[1]);
console.log(obj[2]);
console.log(obj.length);