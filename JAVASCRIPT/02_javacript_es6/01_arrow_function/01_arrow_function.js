
/* 화살표 함수의 특징 */
/* 화살표 함수를 써야 익명함수를 사용해도 전역변수를 사용이 가능하다 */

/* 1. 콜백함수로 화살표 함수를 사용하게 되면 해당 화살표 함수가 사용되는 곳에 따라 this의 의미가 정해진다. (반드시 써야하는 경우)*/
let theater = {
    store: '강남점',
    titles: ['베테랑2', '룩백', '그녀에게', '캐시아웃', '빅토리'],
    showMoiveList(){
        this.titles.forEach(

            /* 화살표 함수(콜백)에서의 this는 showMoiveList를 호출한 객체(theather) */
            title => console.log(this.store + ": " + title)  // arrow function에서의 this는 위의 store도 사용 가능
        );
    }

    // showMoiveList(){
    //     this.titles.forEach(

            /* 단순 익명 함수(콜백)의 this는 global 객체를 말한다. */

            // function(title){
            //     console.log(this.store + ": " + title)     // 함수로 선언하면 this는 함수 내부의 지역변수를 가르키므로 store을 사용 X
            // }

            // 이것과 같은 개념 (store라는 변수를 사용할 수가 없다)
            // anonymous
    //     );
    // }
};

let anonymous = function(title){
    console.log(this.store + ": " + title)      
}

theater.showMoiveList();


/* 2. 화살표 함수는 new 연산자와 함께 사용할 수 없다. (생성자 함수로 쓰일 수 없다.) */
const arrowFunc = () => {};
const normalFunc = function() {

};

// new arrowFunc();                 // 에러 발생
new normalFunc();

/* 3. 화살표 함수는 arguments를 지원하지 않는다.(외부 함수의 argements가 있다면 외부 함수 것이 적용될 순 있음) */
let test = function(){
    console.log('normal outer: ', arguments);       // 바깥의 arguments가 내부의 arguments에 영향을 미친다.
    const arrowFunc = () => console.log(arguments);
    arrowFunc(10,20);                
}

test(1, 2, 3, 4, 5);

