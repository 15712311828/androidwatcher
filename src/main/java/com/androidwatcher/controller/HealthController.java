package com.androidwatcher.controller;

import com.androidwatcher.common.JsonResult;
import com.androidwatcher.service.HealthService;
import com.androidwatcher.vo.param.HealthStatusParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/health")
public class HealthController {

    @Resource
    HealthService healthService;

    @RequestMapping("/status")
    public JsonResult status(@RequestBody @Valid HealthStatusParam healthStatusParam){
        String status = healthService.getStatus(healthStatusParam.getDeviceName());
        return JsonResult.success(status);
    }
}
