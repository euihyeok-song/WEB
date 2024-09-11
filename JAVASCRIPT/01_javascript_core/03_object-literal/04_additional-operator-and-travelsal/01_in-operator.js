/* 프로퍼티 존재 여부 확인하기 */
var student = {
    name: "유관순",
    age: 16,
    test: undefined
};

console.log('name' in student);
console.log('age' in student);
console.log('test' in student);

/* for in 문 */
/* 객체가 가진 모든 프로퍼티를 순회 */
/* JSON.parse(response)로 넘어오 response를 꺼내서 front에 뿌리기 가능하다. */
for(var key in student){
    console.log(`key: ${key}`);
    console.log(`student[${key}]: ${student[key]}`);
    // console.log(`<td>${student[key]}</td>`)
}           // 블록 뒤에는 ;를 붙이지는 않는다

