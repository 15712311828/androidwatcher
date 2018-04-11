package com.androidwatcher.controller;

import com.androidwatcher.common.JsonResult;
import com.androidwatcher.service.DeviceService;
import com.androidwatcher.util.ValidUtil;
import com.androidwatcher.vo.param.DeviceLoginParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/device")
public class DeviceController {

    @Resource
    private DeviceService deviceService;

    @RequestMapping("/login")
    public JsonResult login(@RequestBody @Valid DeviceLoginParam param, HttpServletResponse response){
        deviceService.login(param.getName(),response);
        return JsonResult.success();
    }

    @RequestMapping("/name")
    public JsonResult name(){
        ValidUtil.checkDeviceLogin();
        return JsonResult.success(deviceService.name());
    }
}
