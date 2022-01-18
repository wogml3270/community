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
        cmtFrmElem.addEventListener('submit', (e) =>{
            e.preventDefault();
        });

        cmtFrmElem.btn_submit.addEventListener('click', () =>{
            const cmtVal = cmtFrmElem.ctnt.value;
            if(cmtVal.length === 0){
                alert('댓글 내용을 작성해주세요');
            }else if(regex.isWrongWith('ctnt', cmtVal)){
                alert(regex.msg.ctnt);
            }else{ // 댓글 insert 시도
                insBoardCmtAjax(cmtVal);
            }
        });

        const insBoardCmtAjax = (val) =>{
            const param = {
                'iboard': dataElem.dataset.iboard,
                'ctnt': val
            };
            myFetch.post('/board/cmt', (data) =>{
                console.log(data); // data에 담겨져 있는 값은 0 or 1이다
                switch(data.result){
                    case 0:
                        alert('댓글 등록에 실패하였습니다.');
                        break;
                    default:
                        // 기존 table태그가 있는지 확인
                        const cmtListElem = document.querySelector('#cmt_list')
                        let table = cmtListElem.querySelector('table');
                        if(!table){ // 없으면 table 생성
                            cmtListElem.innerHTML = null; // 댓글 없음 내용 삭제
                            table = makeTable();
                            cmtListElem.appendChild(table);
                        }
                        const item = {
                            icmt: data.result,
                            iuser: parseInt(dataElem.dataset.iuser),
                            writernm: dataElem.dataset.nm,
                            profileimg: dataElem.dataset.profileimg,
                            ctnt: cmtFrmElem.ctnt.value,
                        }
                        const tr = makeTr(item);
                        table.appendChild(tr);

                        cmtFrmElem.ctnt.value = null;
                        break;
                }
            }, param);
        }
    }
    // 댓글 리스트(cmt list)
        // 통신 시작
        const getCmtList = () =>{
            const iboard = dataElem.dataset.iboard;
            myFetch.get(`/board/cmt/${iboard}`, setCmtList);
        }

        // 통신 결과물 세팅
        const setCmtList = (list) => {
            const cmtListElem = document.querySelector('#cmt_list');

            // 댓글이 없으면 "댓글 없음"
            if (list.length === 0) {
                cmtListElem.innerHTML = `<strong style="color:#fc0000;">작성된 댓글이 없습니다.</strong>`
                return;
            }

            const table = makeTable();
            cmtListElem.appendChild(table);

            list.forEach(item => {
                const tr = makeTr(item);
                table.appendChild(tr);
            });
        }

        const makeTable = () =>{
            const table = document.createElement('table');
            table.innerHTML = `
            <tr>
                <th>번호</th>
                <th>댓글 내용</th>
                <th>작성자</th>
                <th></th>
             </tr>`;
        return table;
    }

    const makeTr = item => {
        const tr = document.createElement('tr');

        const imgSrc = item.profileimg === null
            ? '/res/img/defaultProfile.png'
            : `/images/user/${item.iuser}/${item.profileimg}`;

        tr.innerHTML = `
                <td>${item.icmt}</td>
                <td>${item.ctnt}</td>
                <td>
                    <span>${item.writernm}</span>
                    <div class="circular_img size30">
                        <img src="${imgSrc}" onerror="this.style.display='none';">
                    </div>
                </td>
            `;
        const td = document.createElement('td');
        tr.appendChild(td);

        if(parseInt(dataElem.dataset.iuser) === item.iuser) {
            const modBtn = document.createElement('input');
            modBtn.type = 'button';
            modBtn.value = '수정';

            const delBtn = document.createElement('input');
            delBtn.type = 'button';
            delBtn.value = '삭제';

            delBtn.addEventListener('click', () => {
                if(confirm('삭제하시겠습니까?')) {
                    delCmt(item.icmt, tr);
                }
            });

            td.appendChild(modBtn);
            td.appendChild(delBtn);
        }
        return tr;
    }

    const delCmt = (icmt, tr) => {
        myFetch.delete(`/board/cmt/${icmt}`, data => {
            if(data.result) {
                tr.remove();
                // 만약 댓글이 하나도 없다면
                if(getTrLen() === 1){
                    const cmtListElem = document.querySelector('#cmt_list');
                    cmtListElem.innerHTML = `<strong style="color:#fc0000;">작성된 댓글이 없습니다.</strong>`;
                }
            } else {
                alert('댓글을 삭제할 수 없습니다.');
            }
        });
    }

    const getTrLen = () =>{
        const cmtListElem = document.querySelector('#cmt_list');
        const trArr = cmtListElem.querySelectorAll('table tr');
        return trArr.length;
    }

    getCmtList();

}

// Restful API > POST, GET, PUT, DELETE