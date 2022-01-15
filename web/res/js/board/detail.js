{
    // data 버튼
    const dataElem = document.querySelector('#data');

    // 삭제 버튼 클릭 시 이벤트
    const delBtnElem = document.querySelector('#delBtn');
    if(delBtnElem){
        delBtnElem.addEventListener('click', () => {
            const icategory = dataElem.dataset.icategory;
            const iboard = dataElem.dataset.iboard;

            if(confirm(msgDel.fnIsDel(`${iboard}번 글`))){
                location.href = `/board/del?icategory=${icategory}&iboard=${iboard}`;
            }
        });
    }

    // 수정 버튼 클릭 시 이벤트
    const modBtnElem = document.querySelector('#modBtn');
    if(modBtnElem){
        modBtnElem.addEventListener('click', ()=>{
            const iboard = dataElem.dataset.iboard;
            location.href = `/board/mod?iboard=${iboard}`;
        })
    }
    // cmt
    const cmtFrmElem = document.querySelector('#cmtFrm');
    if(cmtFrmElem){ // true: 로그인 한 상태

        // input-text ctnt에서 enter 치면 submit 날아가기 때문에 막는다
        cmtFrmElem.addEventListener('submit', (e)=>{
            e.preventDefault();
        });

        cmtFrmElem.btn_submit.addEventListener('click', () =>{
            const cmtVal = cmtFrmElem.ctnt.value;
            if(cmtVal.length === 0){
                alert('댓글내용을 작성해주세요');
            }else if(regex.isWrongWith('ctnt', cmtVal)){
                alert(regex.msg.ctnt);
            }else{
                insBoardCmtAjax(cmtVal);
            }
        });

        const insBoardCmtAjax = (val) =>{
            const param = {
                'iboard': dataElem.dataset.iboard,
                'ctnt': val
            };
            myFetch.post('/board/cmt', (data) =>{
                console.log(data);
            }, param);
        }
    }
}

// Restful API = POST, GET(2개), PUT, DELETE
