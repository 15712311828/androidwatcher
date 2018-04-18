package com.androidwatcher.vo.param;

import com.androidwatcher.common.DeviceContext;
import com.androidwatcher.model.Location;
import lombok.Data;

import java.util.Date;

@Data
public class LocationUploadParam {

    private Float longitude;

    private Float latitude;

    public Location toLocation(){
        Location location=new Location();
        location.setDeviceid(DeviceContext.getId());
        location.setLongitude(longitude);
        location.setLatitude(latitude);
        location.setTime(new Date());
        return location;
    }
}
