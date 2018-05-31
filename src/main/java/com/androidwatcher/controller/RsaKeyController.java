package com.androidwatcher.controller;


import com.androidwatcher.common.JsonResult;
import com.androidwatcher.service.RsaService;
import com.androidwatcher.vo.RsaVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/rsa")
public class RsaKeyController {

    @Resource
    RsaService rsaService;

    @RequestMapping("/get")
    public JsonResult get(){
        RsaVo rsaVo = rsaService.get();
        return JsonResult.success(rsaVo);
    }
}
