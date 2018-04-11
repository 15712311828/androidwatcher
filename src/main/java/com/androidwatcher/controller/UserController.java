package com.androidwatcher.controller;

import com.androidwatcher.common.JsonResult;
import com.androidwatcher.service.UserService;
import com.androidwatcher.util.ValidUtil;
import com.androidwatcher.vo.param.UserAddParam;
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

    @RequestMapping("/add")
    public JsonResult add(@RequestBody @Valid UserAddParam userAddParam){
        ValidUtil.checkUserLogin();
        userService.add(userAddParam.getName(),userAddParam.getPassword());
        return JsonResult.success();
    }

    @RequestMapping("/login")
    public JsonResult login(@RequestBody @Valid UserAddParam userAddParam,HttpServletResponse response){
        userService.login(userAddParam.getName(),userAddParam.getPassword(),response);
        return JsonResult.success();
    }

    @RequestMapping("/name")
    public JsonResult name(){
        String name = userService.name();
        return JsonResult.success(name);
    }
}
