package com.androidwatcher.util;



import com.androidwatcher.common.DeviceContext;
import com.androidwatcher.exception.BusinessException;

import java.util.List;

public class ValidUtil {

    public static void checkNonExist(Long result) {
        if(result>0){
            throw new BusinessException();
        }
    }

    public static void checkNonExist(Long result,String message) {
        if(result>0){
            throw new BusinessException(message);
        }
    }

    public static void checkExist(List list) {
        if(list.size()==0){
            throw new BusinessException();
        }
    }

    public static void checkExist(List list,String message) {
        if(list.size()==0){
            throw new BusinessException(message);
        }
    }

    public static void checkDeviceLogin(){
        if(null== DeviceContext.get()){
            throw new BusinessException("请先登录");
        }
    }

}
