var dog = {
    name: '뽀삐'
};

dog.name = '두부';      // 수정
console.log(dog);

dog.age = 3;           // 추가
console.log(dog);

delete dog.age;        // 삭제
console.log(dog);