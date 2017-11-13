package com.alan.study.timer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;

/**
 * Created by Ke Zhang on 2017/10/9.
 */
public class Test {

    public static void main(String[] args) throws Exception{
        Timer timer = new Timer();
        //第三秒后执行一次
//        timer.schedule(new MyTimerTask(), 3 *1000);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse("2017-10-09 14:49:30");
        Date date2 = sdf.parse("2017-10-09 12:07:32");
        timer.schedule(new MyTimerTask(), date , 2 * 1000);
//        timer.scheduleAtFixedRate(new MyTimerTask(), date , 2 * 1000);
//        timer.schedule(new MyTimerTask(), date);
//        timer.schedule(new MyTimerTask(), date);

    }
}
