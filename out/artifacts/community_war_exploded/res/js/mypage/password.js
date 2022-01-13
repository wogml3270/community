{
    const pwFrmElem = document.querySelector('#pwFrm');

    if (pwFrmElem) {
        pwFrmElem.addEventListener('submit', (e) => {
            const currentupwVal = pwFrmElem.currentupw.value;
            const upwVal = pwFrmElem.upw.value;
            const confirmupwVal = pwFrmElem.confirmupw.value;

            if(currentupwVal.length === 0){
                alert('현재 비밀번호를 작성해 주세요.');
                e.preventDefault();
            }else if(upwVal.length === 0){
                alert('변경 비밀번호를 작성해주세요.');
                e.preventDefault();
            }else if(upwVal !== confirmupwVal){
                alert('변경 비밀번호와 일치하지않습니다.');
                e.preventDefault();
            }else if(regex.isWrongWith('pw',currentupwVal)){
                alert(`현재 비밀번호가${regex.msg.pw}인지 확인해주세요.`);
                e.preventDefault();
            }else if(regex.isWrongWith('pw',upwVal)){
                alert(`변경 비밀번호가${regex.msg.pw}인지 확인해주세요.`);
                e.preventDefault();
            }
        });
    }
}