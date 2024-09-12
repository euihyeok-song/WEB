
const student1 = {
    name: "유관순",
    age: 16,
    getInfo: function() {
        return `${this.name}(은)는 ${this.age}세 입니다.`;
    }
}

const student2 = {
    name: "유관순",
    age: 16,
    getInfo: function() {
        return `${this.name}(은)는 ${this.age}세 입니다.`;
    }
}

/* 객체 리터럴 방식으로 수백명의 학생 객체를 만들어야 된다면? */

/* 2. 생성자 함수 박싱 (자바의 클래스 + 생성자) */
function Student(name, age){        // 구분을 위해서 함수의 이름을 대문자로 시작한다. 
    this.name = name;
    this.age = age;
    this.getInfo = function(){
        return `${this.name}(은)는 ${this.age}세 입니다.`;
    }
}

/* 객체 => cconsturctor + prototype */
const student3 = new Student('장보고',30);
const student4 = new Student('신사임당',40);

console.log(student3);
console.log(student4);