package com.zng.system.job.controller;

import com.zng.common.entity.ResponseModel;
import com.zng.system.job.entity.JobDto;
import com.zng.system.job.service.JobService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.bind.annotation.*;

/**
 * Created by John.Zhang on 2018/2/26.
 */
@RestController
@RequestMapping(value="/job")
public class JobController
{
    @Autowired
    private JobService jobService;

    @PostMapping(value="/addjob")
    public ResponseModel addjob(@RequestBody JobDto jobDto){
        return jobService.addjob(jobDto);
    }

    @PostMapping(value="/pausejob")
    public ResponseModel pausejob(@RequestBody JobDto jobDto){
        return jobService.pausejob(jobDto);
    }

    @PostMapping(value="/resumejob")
    public ResponseModel resumejob(@RequestBody JobDto jobDto){
        return jobService.resumejob(jobDto);
    }

    @PostMapping(value="/reschedulejob")
    public ResponseModel rescheduleJob(@RequestBody JobDto jobDto){
        return jobService.rescheduleJob(jobDto);
    }

    @PostMapping(value="/deletejob")
    public ResponseModel deletejob(@RequestBody JobDto jobDto) throws Exception{
        return jobService.deletejob(jobDto);
    }

    @GetMapping(value="/queryjob")
    public ResponseModel queryjob(){
        return jobService.queryjob();
    }
}
