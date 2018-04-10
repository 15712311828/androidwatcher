package com.androidwatcher.controller;

import com.androidwatcher.common.JsonResult;
import com.androidwatcher.service.UserService;
import com.androidwatcher.util.ValidUtil;
import com.androidwatcher.vo.param.LoginParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/login")
    public JsonResult login(@RequestBody @Valid LoginParam param, HttpServletResponse response){
        userService.login(param.getName(),response);
        return JsonResult.success();
    }

    @RequestMapping("/name")
    public JsonResult name(){
        ValidUtil.checkLogin();
        return JsonResult.success(userService.name());
    }
}
