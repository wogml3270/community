package com.koreait.community;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Component
public class MyFilesUtils {
    // 폴더 만들기
    public void makeFolders(String path){
        File folder = new File(path);
        if(!folder.exists()){ // exists : 존재 여부
            folder.mkdirs();
        }
    }
    // 폴더 삭제
    public void delFolderFiles(String path, boolean isDelFolder){
        File file = new File(path);
        if(file.exists() && file.isDirectory()){
            File[] fileArr = file.listFiles(); // listFiles : 파일들을 객체로 만들어준다
            for(File f : fileArr){
                if(f.isDirectory()){ // 재귀처리(원래 있던곳으로 다시 돌아오도록 처리), 폴더인지 확인
                    delFolderFiles(f.getPath(), true);
                }else{
                    f.delete(); // 폴더가 아니라면 삭제
                }
            }
        }
        if(isDelFolder){
            file.delete();
        }
    }
    public void delFile(String path){
        File f = new File(path);
        if(f.exists()){
            f.delete();
        }
    }

    // 랜덤파일명 만들기
    public String getRandomFileNm(){
        return UUID.randomUUID().toString();
    }
    public String getRandomFileNm(String fileNm){
        return getRandomFileNm() + getExt(fileNm);
    }
    // 확장자 구하기
    public String getExt(String fileNm){
        return fileNm.substring(fileNm.lastIndexOf("."));
//        int lastIdx = fileNm.lastIndexOf(".");
//        return fileNm.substring(lastIdx);
    }
    // 파일 저장 => 저장된 랜덤 파일명 리턴
    public String saveFile(String path, MultipartFile mf){
        makeFolders(path);
        String randomFileNm = getRandomFileNm(mf.getOriginalFilename());
        File targetFile = new File(path, randomFileNm);
        try {
            mf.transferTo(targetFile);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return randomFileNm;
    }
}
