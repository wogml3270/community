{
    // TODO data 버튼
    const dataElem = document.querySelector('#data');

    // TODO 삭제 버튼 클릭 시 이벤트
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

    // TODO 수정 버튼 클릭 시 이벤트
    const modBtnElem = document.querySelector('#modBtn');
    if(modBtnElem){
        modBtnElem.addEventListener('click', ()=>{
            const iboard = dataElem.dataset.iboard;
            location.href = `/board/mod?iboard=${iboard}`;
        })
    }
}