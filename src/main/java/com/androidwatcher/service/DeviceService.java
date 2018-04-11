package com.androidwatcher.service;

import com.androidwatcher.common.DeviceContext;
import com.androidwatcher.dao.DeviceMapper;
import com.androidwatcher.model.Device;
import com.androidwatcher.model.DeviceExample;
import com.androidwatcher.util.DeviceLoginUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Service
public class DeviceService {

    @Resource
    private DeviceMapper deviceMapper;

    public void login(String name, HttpServletResponse response){
        DeviceExample deviceExample=new DeviceExample();
        DeviceExample.Criteria criteria = deviceExample.createCriteria();
        criteria.andNameEqualTo(name);

        Long result= deviceMapper.countByExample(deviceExample);

        if(result==0){
            Device device=new Device();
            device.setName(name);
            deviceMapper.insert(device);
        }

        DeviceLoginUtil.keepLogin(name,response);
    }

    public String name(){
        return DeviceContext.getName();
    }
}
