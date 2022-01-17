const msgDel = {
    isDel: '정말 삭제하시겠습니까?',
    fnIsDel: function (target) {
        return `${target}을 ` + this.isDel;
    }
}

const regex = {
    id : /^([a-z A-Z 0-9]{4,15})$/,
    pw : /^([a-z A-Z 0-9 !@%^_]{4,20})$/,
    nm : /^([가-힣]{2,5})$/,
    ctnt : /^([^><]*)$/,
    isWrongWith : function(target, val){
        return (target && val) ? !this[target].test(val) : true;
    },
    msg :{
        id: '대소문자, 숫자 조합으로 4~15글자',
        pw: '대소문자 + 숫자 + !@%^_ 조합으로 4~20글자',
        nm: '한글 조합으로 2~5글자',
        ctnt: '<, >는 사용할 수 없습니다.',
    }
}
const myFetch = {
    send: function(fetchObj, cb){
        return fetchObj
            .then(res => { return res.json(); })
            .then(cb)
            .catch(e => { console.log(e) });
    },
    get: function(url, cb, param) {
        if(param){                              // map: 기존의 배열과 똑같은 배열을 하나 만들어준다
            const queryString = '?' + Object.keys(param).map(key => `${key}=${param[key]}`).join('&');
            url += queryString;
        }
        return this.send(fetch(url), cb);
    },
    // url: 문자열, cb: 함수, param: 객체
    post: function(url, cb, param){
        return this.send(fetch(url, {
            'method': 'post',
            'headers': { 'Content-Type': 'application/json'},
            'body': JSON.stringify(param) // stringify: json을 문자로, 문자를 json으로 변환
        }), cb);
    }
}