const msgDel = {
    isDel: '정말 삭제하시겠습니까?',
    fnIsDel: function (target) {
        return `${target}을/(를) ` + this.isDel;
    }
}

const regex = {
    id : /^([a-z A-Z 0-9]{4,15})$/,
    pw : /^([a-z A-Z 0-9 !@%^_]{4,20})$/,
    nm : /^([가-힣]{2,5})$/,
    isWrongWith : function(target, val){
        return !this[target].test(val);
    },
    msg :{
        id: '대소문자, 숫자 조합으로 4~15글자',
        pw: '대소문자 + 숫자 + !@%^_ 조합으로 4~20글자',
        nm: '한글 조합으로 2~5글자'
    }
}