{
    let idChkState = 2; // 0: 아이디 사용 불가능, 1: 아이디 사용 가능, 2: 체크안함
    const joinFrmElem = document.querySelector('#joinFrm');
    const idChkMsg = joinFrmElem.querySelector('#idChkMsg');
    const idRegex = /^([a-z A-Z 0-9]{4,15})$/; // 대소문자 + 숫자 조합으로 4~15 글자인 경우
    const pwRegex = /^([a-z A-Z 0-9 !@%^_]{4,20})$/; // 대소문자 + 숫자 + !@%^_ 조합으로 4~20글자인 경우
    const krNmRegex = /^([가-힣]{2,5})$/; // 이름은 한글로 2~5글자 조합인경우
    const msg1 = '아이디는 영문 대소문자, 숫자 조합 4~15글자가 되어야합니다.';
    const msg2 = '비밀번호는 대소문자, 숫자, 특수문자(!, @, %, ^, _) 조합으로 4~20글자가 되어야합니다.';
    const msg3 = '이름은 한글로 2~5글자가 되어야합니다.';
    const msg4 = '비밀번호를 확인해주세요.';
    const msg5 = '성별을 체크해주세요.';

// 아이디 중복체크 했을 시 뜨는 문구 함수
    const setIdChkMsg = (data) => {
        idChkState = data.result; // 0 or 1
        console.log(idChkState);
        switch (data.result) {
            case 0:
                idChkMsg.innerHTML = `<div style="color: red; font-weight: bold;">이미 사용중인 아이디입니다.</div>`;
                idChkState = 0;
                break;
            case 1:
                idChkMsg.innerHTML = `<div style="color: deepskyblue; font-weight: bold;">사용할 수 있는 아이디입니다.</div>`;
                idChkState = 1;
                break;
        }
    }

    if (joinFrmElem) {
        joinFrmElem.addEventListener('submit', (e) => {
            const uid = joinFrmElem.uid.value;
            const upw = joinFrmElem.upw.value;
            const nm = joinFrmElem.nm.value;
            const upwChk = joinFrmElem.upwChk.value;
            // const gender = joinFrmElem.gender;
            if (!idRegex.test(uid)) {
                alert(msg1);
                e.preventDefault();
            } else if (!pwRegex.test(upw)) {
                alert(msg2);
                e.preventDefault();
            } else if (!krNmRegex.test(nm)) {
                alert(msg3);
                e.preventDefault();
            } else if (upw !== upwChk) {
                alert(msg4);
                e.preventDefault();
            }
            if (idChkState !== 1) {
                switch (idChkState) {
                    case 0:
                        alert('다른 아이디를 사용해 주세요.');
                        break;
                    case 2:
                        alert('아이디 중복 체크를 해주세요.');
                        break;
                }
                e.preventDefault(); // 페이지 새로고침을 막아주는 함수
            }
        });

        joinFrmElem.uid.addEventListener('keyup', () => {
            const idChkMsg = joinFrmElem.querySelector('#idChkMsg');
            idChkMsg.innerHTML = '';
            idChkState = 2;
        })
        // 아이디 중복체크 버튼 이벤트
        const idChkBtnElem = joinFrmElem.querySelector('#idChkBtn');
        idChkBtnElem.addEventListener('click', () => {
            const idVal = joinFrmElem.uid.value;
            if (idVal.length < 4) {
                alert('아이디는 최소 4자 이상입니다.');
                return;
            }else if (!idRegex.test(idVal)) {
                alert(msg1);
                return;
            }
            // fetch(`/user/idChk/${idVal}`) // `` : 문자열 안에 변수값을 넣기 편하다
            //     .then(res => res.json())
            //     .then((data) => {
            //         setIdChkMsg(data);
            //     }).catch((e) => {
            //     console.log(e);
            // })
            myFetch.get(`/user/idChk/${idVal}`, (data) =>{
                setIdChkMsg(data);
            });
        });
    }
}