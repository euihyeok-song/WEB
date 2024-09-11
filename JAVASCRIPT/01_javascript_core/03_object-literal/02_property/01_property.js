var obj = {
    normal: 'normal value',
    '@ s p a c e @': 'space value',     // 띄어쓰기나 특수기호(_, $ 제외)를 지양해야 한다.
    '': '',                             // 가능은 하지만 권장 X
    0: 1,                               // 숫자는 문자열로 내부적으로 변환
    var: 'var',                         // 예약어 권장 X
    normal: 'new value'                 // 중복되면 나중에 작성한 프로퍼티만 인정
};

/* 기존에 없다면 프로퍼티가 추가된다. */
obj['test'] = 'test value';         // 동적으로 프로퍼티 추가
console.log(obj);

console.log(obj.test);
console.log(obj.normal);