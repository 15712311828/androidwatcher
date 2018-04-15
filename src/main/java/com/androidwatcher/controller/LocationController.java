package com.androidwatcher.controller;


import com.androidwatcher.common.JsonResult;
import com.androidwatcher.model.Location;
import com.androidwatcher.service.LocationService;
import com.androidwatcher.util.ValidUtil;
import com.androidwatcher.vo.param.LocationUploadParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/location")
public class LocationController {


    @Resource
    private LocationService locationService;

    @RequestMapping("/upload")
    public JsonResult upload(@RequestBody @Valid LocationUploadParam locationUploadParam){
        ValidUtil.checkDeviceLogin();
        locationService.upload(locationUploadParam);
        return JsonResult.success();
    }

    @RequestMapping("/getAll")
    public JsonResult getAll(){
        ValidUtil.checkUserLogin();
        List<Location> all = locationService.getAll();
        return JsonResult.success(all);
    }
}
