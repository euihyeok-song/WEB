var str = '안녕하세요';
console.log(typeof str);

var str2 = `안녕하세요.
반갑습니다.`;
console.log('multiline: ', str2);

var lastName = '홍';
var firstName = '길동';
console.log('제 이름은 ' + lastName + ' ' + firstName + '입니다.');

/* 표현식 삽입(${}, Template Literals)과 백틱(`)을 함께 사용하면 가독성도 좋고 간편하게 조합할 수 있다. */
console.log(`제 이름은 ${lastName} ${firstName} ${1 + 2}입니다.`);