package com.androidwatcher.service;

import com.androidwatcher.common.UserContext;
import com.androidwatcher.dao.UserMapper;
import com.androidwatcher.exception.BusinessException;
import com.androidwatcher.model.User;
import com.androidwatcher.model.UserExample;
import com.androidwatcher.util.UserLoginUtil;
import com.androidwatcher.util.ValidUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public void add(String name,String password){
        UserExample userExample=new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andNameEqualTo(name);
        long result=userMapper.countByExample(userExample);
        ValidUtil.checkNonExist(result,"用户名已存在");

        User user=new User();
        user.setName(name);
        user.setPassword(password);
        userMapper.insert(user);
    }

    public void login(String name, String password, HttpServletResponse response){
        UserExample userExample=new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andNameEqualTo(name);
        criteria.andPasswordEqualTo(password);
        long result=userMapper.countByExample(userExample);
        if(result==0){
            throw new BusinessException("用户名或密码错误");
        }
        else{
            UserLoginUtil.keepLogin(name,response);
        }
    }

    public String name(){
        return UserContext.getName();
    }
}