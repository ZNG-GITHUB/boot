package com.zng.system.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by John.Zhang on 2018/2/26.
 */
public class TestJob_2 implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd 24hh:mm:ss").format(new Date())+":正在执行第二个");
    }
}
