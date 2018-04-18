package com.androidwatcher.service;

import com.androidwatcher.common.DeviceContext;
import com.androidwatcher.common.UserContext;
import com.androidwatcher.dao.DeviceMapper;
import com.androidwatcher.dao.LocationMapper;
import com.androidwatcher.model.Device;
import com.androidwatcher.model.DeviceExample;
import com.androidwatcher.model.Location;
import com.androidwatcher.model.LocationExample;
import com.androidwatcher.vo.LocationVo;
import com.androidwatcher.vo.param.LocationUploadParam;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationService {

    @Resource
    private LocationMapper locationMapper;

    @Resource
    private DeviceMapper deviceMapper;

    public void upload(LocationUploadParam locationUploadParam){
        LocationExample locationExample=new LocationExample();
        LocationExample.Criteria criteria = locationExample.createCriteria();
        criteria.andDeviceidEqualTo(DeviceContext.getId());
        Long result=locationMapper.countByExample(locationExample);
        if(result==0){
            locationMapper.insert(locationUploadParam.toLocation());
        }
        else{
            locationMapper.updateByExampleSelective(locationUploadParam.toLocation(),locationExample);
        }
    }

    public List<LocationVo> getAll(){
        LocationExample locationExample = new LocationExample();
        LocationExample.Criteria criteria = locationExample.createCriteria();
        criteria.andTimeGreaterThan(new Date(new Date().getTime()-5*60*1000));
        List<LocationVo> locationVos=locationMapper.selectByExample(locationExample).stream().map(LocationVo::fromLocation).collect(Collectors.toList());
        for(LocationVo locationVo:locationVos){
            DeviceExample deviceExample=new DeviceExample();
            DeviceExample.Criteria criteria1 = deviceExample.createCriteria();
            criteria.andIdEqualTo(locationVo.getDeviceid());
            List<Device> devices = deviceMapper.selectByExample(deviceExample);
            if(devices.size()<=0){
                locationVo.setName("unknown");
            }
            else{
                locationVo.setName(devices.get(0).getName());
            }
        }
        return locationVos;
    }

}
