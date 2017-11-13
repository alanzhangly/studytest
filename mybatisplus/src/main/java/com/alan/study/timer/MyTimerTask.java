package com.alan.study.timer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

/**
 * Created by Ke Zhang on 2017/10/9.
 */
public class MyTimerTask extends TimerTask {
    public static int time  = 3;
    @Override
    public void run() {
//        try {
//            Thread.sleep(5 * 1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("-- 执行任务 -- " + Thread.currentThread().getName());

        int i = 1/0;
//        if(time > 0){
//            try {
//                Thread.sleep(time * 1000);
//                time --;
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

//        this.cancel();
    }
}
