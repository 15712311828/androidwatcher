package com.androidwatcher.vo.param;

import com.androidwatcher.common.DeviceContext;
import com.androidwatcher.model.Location;
import lombok.Data;

import java.util.Date;

@Data
public class LocationUploadParam {

    private Float longitude;

    private Float dimension;

    public Location toLocation(){
        Location location=new Location();
        location.setDeviceid(DeviceContext.getId());
        location.setLongitude(longitude);
        location.setDimension(dimension);
        location.setTime(new Date());
        return location;
    }
}
