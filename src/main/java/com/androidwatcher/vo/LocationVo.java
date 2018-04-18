package com.androidwatcher.vo;

import com.androidwatcher.model.Location;
import lombok.Data;

import java.util.Date;

@Data
public class LocationVo {


    private Integer deviceid;

    private String name;

    private Float longitude;

    private Float latitude;


    public static LocationVo fromLocation(Location location){
        LocationVo locationVo=new LocationVo();
        locationVo.deviceid=location.getDeviceid();
        locationVo.longitude=location.getLongitude();
        locationVo.latitude=location.getLatitude();
        return locationVo;
    }
}
