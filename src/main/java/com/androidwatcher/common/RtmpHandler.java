package com.androidwatcher.common;


import lombok.extern.slf4j.Slf4j;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.avcodec;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_objdetect;
import org.bytedeco.javacv.*;

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
        Loader.load(opencv_objdetect.class);

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
        String name= DeviceContext.getName();
        if(lives.get(name)==null){
            fixedThreadPool.execute(()->start(name));
        }
        lives.put(name,System.currentTimeMillis());
    }

    private static void start(String name){

        log.info("thread name {}",Thread.currentThread().getName());
        threads.put(name,Thread.currentThread());
        FrameGrabber grabber=null;
        FrameRecorder recorder=null;
        try {
            long startTime = 0;
            grabber = FFmpegFrameGrabber.createDefault("rtmp://118.89.229.227/live/"+name);
            grabber.start();
            OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
            //如果想要保存图片,可以使用 opencv_imgcodecs.cvSaveImage("hello.jpg", grabbedImage);来保存图片
            recorder =  FrameRecorder.createDefault("rtmp://118.89.229.227/live/"+name+"handled", 640, 360);
            recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
            recorder.setFormat("flv");
            recorder.setFrameRate(15);
            recorder.setGopSize(15);
            log.info("准备开始推流...");
            recorder.start();
            log.info("开始推流");
            Frame grabframe;
            opencv_core.IplImage grabbedImage;
            while (!Thread.currentThread().isInterrupted()&&(grabframe = grabber.grab()) != null) {
                grabbedImage = converter.convert(grabframe);
                Frame rotatedFrame = converter.convert(grabbedImage);

                if (startTime == 0) {
                    startTime = System.currentTimeMillis();
                }
                recorder.setTimestamp(1000 * (System.currentTimeMillis() - startTime));
                if (rotatedFrame != null) {
                    recorder.record(rotatedFrame);
                }

                Thread.sleep(10);
            }
        }
        catch (Exception e) {
            log.error("rtmp fail", e);
        }
        finally {
            try {
                if (recorder != null) {
                    recorder.stop();
                    recorder.release();
                }
            }
            catch (Exception e) {
                log.error("recorder close fail", e);
            }
            try {
                if (grabber != null) {
                    grabber.stop();
                }
            }
            catch (Exception e) {
                log.error("grabber close fail", e);
            }
        }
        log.info("{} rtmp exit",name);
    }
}
