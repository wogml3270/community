{
    const dataElem = document.querySelector('#data');

    const delBtnElem = document.querySelector('#delBtn');
    if(delBtnElem) {
        delBtnElem.addEventListener('click', () => {
            const icategory = dataElem.dataset.icategory;
            const iboard = dataElem.dataset.iboard;

            if(confirm(msgDel.fnIsDel(`${iboard}번 글`))){
                location.href = `/board/del?icategory=${icategory}&iboard=${iboard}`;
            }
        });
    }
}