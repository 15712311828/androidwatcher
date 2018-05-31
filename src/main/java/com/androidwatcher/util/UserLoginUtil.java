package com.androidwatcher.util;

import com.google.common.base.Splitter;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
public class UserLoginUtil {

    public static final String KEY="a1132r";

    public static void keepLogin(String name, HttpServletRequest request, HttpServletResponse response){
        Cookie nameCookie=new Cookie("_u",CryptUtil.aesEncrypt(name+"&"+request.getRemoteAddr(),KEY));
        log.info(request.getRemoteAddr());
        nameCookie.setPath("/");
        response.addCookie(nameCookie);
    }

    public static String getLoginName(Cookie[] cookies,HttpServletRequest request){
        String name=null;
        String ip=null;
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("_u")){
                try {
                    List<String> list = Splitter.on("&").splitToList(CryptUtil.aesDecrypt(cookie.getValue(), KEY));
                    if(list.size()<2){
                        continue;
                    }
                    name = list.get(0);
                    ip=list.get(1);
                }
                catch (Exception e){
                    name=null;
                }
            }
        }
        if(ip!=null&&ip.equals(request.getRemoteAddr())) {
            return name;
        }
        return null;
    }
}
