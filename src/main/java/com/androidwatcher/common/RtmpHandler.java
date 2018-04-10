package com.androidwatcher.common;


import lombok.extern.slf4j.Slf4j;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class RtmpHandler {

    private static ConcurrentHashMap<String,Long> lives = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String,Thread> threads = new ConcurrentHashMap<>();

    private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(8);

    static {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                for(ConcurrentHashMap.Entry entry:lives.entrySet()){
                    String key = (String)entry.getKey();
                    if(System.currentTimeMillis()-((Long)entry.getValue())>10000){
                        Thread thread = threads.get(key);
                        if(null!=thread){
                            thread.interrupt();
                        }
                        lives.remove(key);
                        threads.remove(key);
                    }
                }
            }
        },10000,10000);
    }

    public static void hold(){
        String name=UserContext.getName();
        if(lives.get(name)==null){
            fixedThreadPool.execute(()->start(name));
        }
        lives.put(name,System.currentTimeMillis());
    }

    private static void start(String name){

        log.info("thread name {}",Thread.currentThread().getName());
        threads.put(name,Thread.currentThread());

        while(!Thread.currentThread().isInterrupted()){
            log.info("{} run",name);

        }
        log.info("{} exit",name);
    }
}
