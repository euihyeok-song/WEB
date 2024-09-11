/* 
    자바스크립트는 객체 지향(기반) 프로그래밍 언어로 원시 값을 제외한 나머지 값
    (함수, 배열, 정규 표현식 등)은 객체이다.
 */

// var name = '홍씨';
// var age = 10;
var student = {                 // literal 객체 선언
    name: "유관순",
    age: 16,
    getInfo: function () {      // Anonymous 함수 (이름이 없는 익명 함수) - 외부에서 호출시에는 student.getinfo()로 호출 

        /* 메소드(객체내의 함수)에서 프로퍼티를 활용할 경우에는 this.을 반드시 명시해야 한다.*/
        return `${this.name}(은)는 ${this.age}세 입니다.`;      // 객체 내부에 프로퍼티로 있는 함수를 "메소드" 라고 한다
    }
};       

console.log(student.getInfo);           // studnet의 getInfo 프로퍼티에 접근
console.log(student.getInfo());         // 메소드일 경우 실핼할 때는 ()를 명시한다.

student.name = '강감찬';
console.log(student.getInfo());
