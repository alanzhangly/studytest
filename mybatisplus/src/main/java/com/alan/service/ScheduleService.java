package com.alan.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Ke Zhang on 2017/10/9.
 */
@Service
public class ScheduleService {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Scheduled(cron="0 0/2 8-20 * * ?")
    public void executeFileDownLoadTask() {

        // 间隔2分钟,执行工单上传任务
        Thread current = Thread.currentThread();
//        System.out.println("定时任务1:"+current.getId());
        System.out.println("定时任务1:"+current.getId()+ ",name:"+current.getName() + "--" + sdf.format(new Date()));
    }

    @Scheduled(cron="0 0/1 8-20 * * ?")
    public void executeUploadTask() {

        // 间隔1分钟,执行工单上传任务
        Thread current = Thread.currentThread();
//        System.out.println("定时任务2:"+current.getId());
        System.out.println("定时任务2:"+current.getId() + ",name:"+current.getName() + "--" + sdf.format(new Date()));
    }

    @Scheduled(cron="0 0/3 5-23 * * ?")
    public void executeUploadBackTask() {

        // 间隔3分钟,执行工单上传任务
        Thread current = Thread.currentThread();
//        System.out.println("定时任务3:"+current.getId());
        System.out.println("定时任务3:"+current.getId()+ ",name:"+current.getName() + "--" + sdf.format(new Date()));
    }
}
