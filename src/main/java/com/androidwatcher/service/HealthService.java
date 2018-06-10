package com.androidwatcher.service;

import com.androidwatcher.dao.DeviceMapper;
import com.androidwatcher.dao.SvmDataMapper;
import com.androidwatcher.model.Device;
import com.androidwatcher.model.DeviceExample;
import com.androidwatcher.model.SvmData;
import com.androidwatcher.model.SvmDataExample;
import com.androidwatcher.util.SvmUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HealthService {

    @Resource
    DeviceMapper deviceMapper;

    @Resource
    SvmDataMapper svmDataMapper;

    public String getStatus(String deviceName){
        DeviceExample deviceExample=new DeviceExample();
        DeviceExample.Criteria criteria = deviceExample.createCriteria();
        criteria.andNameEqualTo(deviceName);
        List<Device> devices = deviceMapper.selectByExample(deviceExample);
        if(devices.isEmpty()){
            throw new RuntimeException("设备不存在");
        }
        Integer id=devices.get(0).getId();

        SvmDataExample svmDataExample=new SvmDataExample();
        SvmDataExample.Criteria svmDataExampleCriteria = svmDataExample.createCriteria();
        svmDataExampleCriteria.andDeviceIdEqualTo(id);
        List<SvmData> svmDatas = svmDataMapper.selectByExample(svmDataExample);
        if(svmDatas.isEmpty()){
            throw new RuntimeException("信息不存在");
        }

        String data=svmDatas.get(0).getData();
        return SvmUtil.predict(data)+"";
    }
}
