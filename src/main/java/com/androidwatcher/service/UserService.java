package com.androidwatcher.service;

import com.androidwatcher.common.UserContext;
import com.androidwatcher.dao.UserMapper;
import com.androidwatcher.model.User;
import com.androidwatcher.model.UserExample;
import com.androidwatcher.util.LoginUtil;
import com.sun.deploy.net.HttpResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public void login(String name, HttpServletResponse response){
        UserExample userExample=new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andNameEqualTo(name);

        Long result=userMapper.countByExample(userExample);

        if(result==0){
            User user=new User();
            user.setName(name);
            userMapper.insert(user);
        }

        LoginUtil.keepLogin(name,response);
    }

    public String name(){
        return UserContext.getName();
    }
}
