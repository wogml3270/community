{
    const profileViewElem = document.querySelector('#profile_view');
    const profileFileElem = document.querySelector('#profile_file');
    const dataElem = document.querySelector('#data');

    if(profileFileElem){
        profileFileElem.addEventListener('change', ()=>{
            const img = profileFileElem.files[0];
            if(img != null){
                uploadProfileImg(img);
            }
        });
    }
    // TODO 프로필 이미지 클릭 이벤트
    if(profileViewElem){
        profileViewElem.addEventListener('click', () =>{
            if(profileFileElem){
                profileFileElem.click();
            }
            console.log('ddd')
        });
    }

    const uploadProfileImg = (img) => {
        const fData = new FormData();
        fData.append('profileimg', img);

        fetch('/user/mypage/profile',{
            'method': 'post',
            'body': fData
        })
            .then(res => res.json())
            .then(data => {
                console.log(data);
                setProfileImg(data);
        })
            .catch(e =>{
                console.log(e);
            });
    }
    const setProfileImg = (data) =>{
        if(!data.result){ return; }
        const iuser = dataElem.dataset.iuser;
        const img = profileViewElem.querySelector('img');
        img.src = `/images/user/${iuser}/${data.result}`;
    }
}