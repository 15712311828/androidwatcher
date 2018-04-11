package com.androidwatcher.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class UserLoginUtil {

    public static final String KEY="a1132r";

    public static void keepLogin(String name,HttpServletResponse response){
        Cookie nameCookie=new Cookie("_u",CryptUtil.aesEncrypt(name,KEY));
        nameCookie.setPath("/");
        response.addCookie(nameCookie);
    }

    public static String getLoginName(Cookie[] cookies){
        String name=null;
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("_u")){
                try {
                    name = CryptUtil.aesDecrypt(cookie.getValue(), KEY);
                }
                catch (Exception e){
                    name=null;
                }
            }
        }
        return name;
    }
}
