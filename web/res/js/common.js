const msgDel = {
    isDel: '정말 삭제하시겠습니까?',
    fnIsDel: function (target) {
        return `${target}을/(를) ` + this.isDel;
    }
}