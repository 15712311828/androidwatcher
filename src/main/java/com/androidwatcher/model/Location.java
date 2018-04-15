package com.androidwatcher.model;

import java.util.Date;

public class Location {
    private Integer id;

    private Integer deviceid;

    private Float longitude;

    private Float dimension;

    private Date time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(Integer deviceid) {
        this.deviceid = deviceid;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getDimension() {
        return dimension;
    }

    public void setDimension(Float dimension) {
        this.dimension = dimension;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}