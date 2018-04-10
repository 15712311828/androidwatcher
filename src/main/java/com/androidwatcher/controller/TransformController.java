package com.androidwatcher.controller;


import com.androidwatcher.common.JsonResult;
import com.androidwatcher.common.RtmpHandler;
import com.androidwatcher.util.ValidUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transform")
public class TransformController {

    @RequestMapping("/hold")
    public JsonResult hold(){
        ValidUtil.checkLogin();
        RtmpHandler.hold();
        return JsonResult.success();
    }
}
