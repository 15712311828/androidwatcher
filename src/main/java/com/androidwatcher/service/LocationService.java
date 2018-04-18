package com.androidwatcher.service;

import com.androidwatcher.common.DeviceContext;
import com.androidwatcher.common.UserContext;
import com.androidwatcher.dao.LocationMapper;
import com.androidwatcher.model.Location;
import com.androidwatcher.model.LocationExample;
import com.androidwatcher.vo.param.LocationUploadParam;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class LocationService {

    @Resource
    private LocationMapper locationMapper;

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

    public List<Location> getAll(){
        LocationExample locationExample = new LocationExample();
        LocationExample.Criteria criteria = locationExample.createCriteria();
        criteria.andTimeGreaterThan(new Date(new Date().getTime()-5*60*1000));
        return locationMapper.selectByExample(locationExample);
    }

}
