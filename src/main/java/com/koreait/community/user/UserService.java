package com.koreait.community.user;

import com.koreait.community.Const;
import com.koreait.community.MyFilesUtils;
import com.koreait.community.UserUtils;
import com.koreait.community.user.model.UserDTO;
import com.koreait.community.user.model.UserEntity;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

//test
@Service
public class UserService {
    @Autowired
    private UserMapper mapper;
    @Autowired
    private UserUtils userUtils;
    @Autowired
    private MyFilesUtils myFilesUtils;

    public int login(UserEntity entity){
        UserEntity dbUser = null;
        try{
            dbUser = mapper.selUser(entity);
        }catch(Exception e){
            e.printStackTrace();
            return 0; // 알 수 없는 에러
        }
        if(dbUser == null){ // 아이디 없음
            return 2;
        }else if(!BCrypt.checkpw(entity.getUpw(), dbUser.getUpw())){
            return 3; // 비밀번호 틀림
        }
        dbUser.setUpw(null);
        dbUser.setRdt(null);
        dbUser.setMdt(null);
        userUtils.setLoginUser(dbUser);
        return 1;
    }

    public int join(UserEntity entity) { //uid, upw, nm, gender
        UserEntity copyEntity = new UserEntity();
        BeanUtils.copyProperties(entity, copyEntity);

        //비밀번호 암호화
        String hashedPw = BCrypt.hashpw(copyEntity.getUpw(), BCrypt.gensalt());
        copyEntity.setUpw(hashedPw);
        return mapper.insUser(copyEntity);
    }

    //아이디가 없으면 리턴 1, 있으면 리턴 0
    public int idChk(String uid){
        UserEntity entity = new UserEntity();
        entity.setUid(uid);

        UserEntity result = mapper.selUser(entity);

        return result == null ? 1 : 0;
    }
    // 이미지 업로드 처리 및 저장 path 리턴
    public String uploadProfileImg(MultipartFile mf){
        if(mf == null){
            return null;
        }
        UserEntity loginUser = userUtils.getLoginUser();

        final String PATH = Const.UPLOAD_IMG_PATH + "/user/" + loginUser.getIuser();
        String fileNm = myFilesUtils.saveFile(PATH, mf);
        System.out.println(fileNm);

        if(fileNm == null){
            return null;
        }
        UserEntity entity = new UserEntity();
        entity.setIuser(loginUser.getIuser());

        // 기존 파일명
        String oldFilePath = PATH + "/" + loginUser.getProfileimg();
        myFilesUtils.delFile(oldFilePath);

        // 파일명을 t_user 테이블에 update
        entity.setProfileimg(fileNm);
        mapper.updUser(entity);

        // 세션 프로필 파일명을 수정해 준다.
        loginUser.setProfileimg(fileNm);
        return fileNm;
    }
    public int changePassword(UserDTO dto){
        dto.setIuser(userUtils.getLoginUserPk());
        UserEntity dbUser = mapper.selUser(dto);
        if(!BCrypt.checkpw(dto.getCurrentupw(), dbUser.getUpw())) {
            return 2; // 현재 비밀번호 다름
        }
        String hashedPw = BCrypt.hashpw(dto.getUpw(), BCrypt.gensalt());
        dto.setUpw(hashedPw);
        return mapper.updUser(dto);
    }
}
