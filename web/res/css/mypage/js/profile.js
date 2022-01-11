{
    // TODO 프로필 이미지 클릭 이벤트
    const profileViewElem = document.querySelector('#profile_view');
    const profileFileElem = document.querySelector('#profile_file');

    if(profileFileElem){
        profileFileElem.addEventListener('change', ()=>{
            const img = profileFileElem.files[0];
            if(img != null){
                uploadProfileImg(img);
            }
        });
    }
    if(profileViewElem){
        profileViewElem.addEventListener('click', () =>{
            if(profileFileElem){
                profileFileElem.click();
            }
        });
    }

    const uploadProfileImg = (img) => {
        const fData = new FormData();
        Fdata.append('profileimg', img);
        fetch('/user/mypage/profile',{
            'method': 'post',
            'body': fData
        })
            .then(res => res.json())
            .then(data => {
                console.log(data);
        })
            .catch((e) =>{
                console.log(e);
            });
    }
}