package com.koreait.community;

public class MyCustomTag {
    public static String profileImg(String idVal, String classVal, String profileImgVal){
        String fixProfileImgVal = "/res/img/defaultProfile.png";
        if(profileImgVal != null){
            fixProfileImgVal = "/images/user";
        }
        return String.format("<div id=\"?\" class=\"?\"><img src=\"%s\"></div>", idVal, classVal, fixProfileImgVal);
    }
}
