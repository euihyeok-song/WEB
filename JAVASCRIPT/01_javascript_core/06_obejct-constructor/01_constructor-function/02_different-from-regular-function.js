
function Student(name, age){       
    /* 객체 내부에서의 this는 지역변수를 가르킴 */
    this.name = name;
    this.age = age;
    this.getInfo = function(){
        return `${this.name}(은)는 ${this.age}세 입니다.`;
    }
}

/* new가 없으면 일반 함수와 동일(함수명 첫 글자만 대문자일 뿐) */
const student1 = Student('강감찬',35);
const student2 = new Student('강감찬',35);
console.log(student1);
console.log(student2);

/* 일반적으로 this는 global 객체로 결과값이 {}이 나옴 */
const student3 = Student('강감찬',35);
console.log(this);   // 이때 함수 내부의 this(global 객체)는 생성되는 객체를 뜻하는 것이 아님

1

/* 
    생성자 함수가 일반 함수와 차이나도록 new 연산자 없이 호출되어도 객체가 생성되는 빌트인 함수로 만들 수 있다.
    (ES6에서 new.target을 지원함으로써 new 연산자 없이 호출되는 것을 처리할 수 있게 되었디.)
*/
function Dog(name, age){
    console.log('new.target: ', new.target);        // new,target new 키워드가 담겻

    /* new 키워드 없이 함수를 호출 했을 때 'new 함수명'해서 객체가 반환되도록 작성 */
    if(!new.target){
        return new Dog(name,age);                   // new를 안붙여도 new를 붙여서 만들어줌
    }
    this.name = name;
    this.age = age;
}

const dog = new Dog('뽀삐', 3);
console.log(dog);

console.log(String(1));


/*
    제공되는 빌트인 함수들: Object, String, Number, Boolean, Date, Regex, ...
*/