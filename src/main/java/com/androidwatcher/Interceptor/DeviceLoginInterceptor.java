package com.androidwatcher.Interceptor;

import com.androidwatcher.common.DeviceContext;
import com.androidwatcher.dao.DeviceMapper;
import com.androidwatcher.model.Device;
import com.androidwatcher.model.DeviceExample;
import com.androidwatcher.util.DeviceLoginUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class DeviceLoginInterceptor implements HandlerInterceptor {

    @Resource
    private DeviceMapper deviceMapper;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        Cookie[] cookies = httpServletRequest.getCookies();
        if(cookies == null){
            return true;
        }
        String name= DeviceLoginUtil.getLoginName(cookies);
        if(name==null){
            return true;
        }
        DeviceExample example=new DeviceExample();
        DeviceExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);
        List<Device> devices = deviceMapper.selectByExample(example);
        if(devices.size()>=1){
            DeviceContext.set(devices.get(0));
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        DeviceContext.remove();
    }
}
