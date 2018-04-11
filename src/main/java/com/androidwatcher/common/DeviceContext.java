package com.androidwatcher.common;


import com.androidwatcher.model.Device;

public class DeviceContext {

    private static ThreadLocal<Device> DEVICE = new ThreadLocal<Device>();

    public static void set(Device device){
        DEVICE.set(device);
    }

    public static Device get(){
        return DEVICE.get();
    }

    public static void remove(){
        if(DEVICE.get()!=null) {
            DEVICE.remove();
        }
    }

    public static int getId(){
        Device device = DEVICE.get();
        return device==null?null:device.getId();
    }

    public static String getName(){
        Device device = DEVICE.get();
        return device==null?null:device.getName();
    }

}
