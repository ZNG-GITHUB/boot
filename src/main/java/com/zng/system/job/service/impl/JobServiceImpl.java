package com.zng.system.job.service.impl;

import com.zng.common.entity.ResponseCode;
import com.zng.common.entity.ResponseModel;
import com.zng.system.job.controller.JobController;
import com.zng.system.job.entity.JobDto;
import com.zng.system.job.service.JobService;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by John.Zhang on 2018/2/26.
 */
@Service(value = "jobService")
public class JobServiceImpl implements JobService{

    private static Logger log = LoggerFactory.getLogger(JobController.class);

    @Autowired
    private SchedulerFactoryBean sf;


    @Override
    @Transactional
    public ResponseModel addjob(JobDto jobDto) {

        String jobClassName = jobDto.getJobClassName();
        String jobGroupName = jobDto.getJobGroupName();
        List<JobDto.TriggerDTO> triggers = jobDto.getTriggers();
        Scheduler sched = sf.getScheduler();

        // 启动调度器
        try {
            sched.start();
        } catch (SchedulerException e) {
            log.debug(e.toString());
            return new ResponseModel(ResponseCode.Error,"添加失败：任务调度器启动失败",e);
        }

        //构建job信息
        JobDetail jobDetail = null;
        try {
            jobDetail = JobBuilder.newJob(getClass(jobClassName).getClass()).withIdentity(jobClassName, jobGroupName).build();
        } catch (Exception e) {
            log.debug(e.toString());
            return new ResponseModel(ResponseCode.Error,"添加失败：任务信息构建失败",e);
        }

        Set<CronTrigger> cronTriggers = new HashSet<>();
        for(JobDto.TriggerDTO triggerDTO : triggers){
            //表达式调度构建器(即任务执行的时间)
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(triggerDTO.getCronExpression());
            //按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobClassName, jobGroupName).withSchedule(scheduleBuilder).build();
            cronTriggers.add(trigger);
        }
        try {
            sched.scheduleJob(jobDetail, cronTriggers,true);
        } catch (SchedulerException e) {
            log.debug(e.toString());
            return new ResponseModel(ResponseCode.Error,"添加失败：创建定时任务失败",e);
        }
        return new ResponseModel();
    }

    @Override
    @Transactional
    public ResponseModel pausejob(JobDto jobDto) {

        String jobClassName = jobDto.getJobClassName();
        String jobGroupName = jobDto.getJobGroupName();
        Scheduler sched = sf.getScheduler();
        try {
            sched.pauseJob(JobKey.jobKey(jobClassName, jobGroupName));
        } catch (SchedulerException e) {
            log.debug(e.toString());
            return new ResponseModel(ResponseCode.Error,"暂停失败",e);
        }
        return new ResponseModel();
    }

    @Override
    @Transactional
    public ResponseModel resumejob(JobDto jobDto) {

        String jobClassName = jobDto.getJobClassName();
        String jobGroupName = jobDto.getJobGroupName();

        Scheduler sched = sf.getScheduler();
        try {
            sched.resumeJob(JobKey.jobKey(jobClassName, jobGroupName));
        } catch (SchedulerException e) {
            log.debug(e.toString());
            return new ResponseModel(ResponseCode.Error,"启动失败",e);
        }
        return new ResponseModel();
    }

    @Override
    @Transactional
    public ResponseModel rescheduleJob(JobDto jobDto) {

        String jobClassName = jobDto.getJobClassName();
        String jobGroupName = jobDto.getJobGroupName();
        List<JobDto.TriggerDTO> triggers = jobDto.getTriggers();

        try {
            Scheduler scheduler = sf.getScheduler();
            TriggerKey triggerKey = TriggerKey.triggerKey(jobClassName, jobGroupName);

            for(JobDto.TriggerDTO triggerDTO : triggers){
                // 表达式调度构建器
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(triggerDTO.getCronExpression());

                CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

                // 按新的cronExpression表达式重新构建trigger
                trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

                // 按新的trigger重新设置job执行
                scheduler.rescheduleJob(triggerKey, trigger);
            }

        } catch (SchedulerException e) {
            log.debug(e.toString());
            return new ResponseModel(ResponseCode.Error,"更新失败",e);
        }
        return new ResponseModel();
    }

    @Override
    @Transactional
    public ResponseModel deletejob(JobDto jobDto) {

        String jobClassName = jobDto.getJobClassName();
        String jobGroupName = jobDto.getJobGroupName();

        Scheduler sched = sf.getScheduler();
        try {
            sched.pauseTrigger(TriggerKey.triggerKey(jobClassName, jobGroupName));
            sched.unscheduleJob(TriggerKey.triggerKey(jobClassName, jobGroupName));
            sched.deleteJob(JobKey.jobKey(jobClassName, jobGroupName));
        } catch (SchedulerException e) {
            log.debug(e.toString());
            return new ResponseModel(ResponseCode.Error,"删除失败",e);
        }
        return new ResponseModel();
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseModel queryjob() {

        Scheduler scheduler = sf.getScheduler();

        List<JobDto> jobs = new ArrayList<>();
        try {
            for (String groupName : scheduler.getJobGroupNames()) {
                for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
                    JobDto jobDto = new JobDto();
                    jobDto.setJobClassName(jobKey.getName());
                    jobDto.setJobGroupName(jobKey.getGroup());
                    List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
                    List<JobDto.TriggerDTO> triggerDTOs = new ArrayList<>();
                    for(Trigger trigger : triggers){
                        JobDto.TriggerDTO triggerDTO = new JobDto.TriggerDTO();
                        if(trigger instanceof CronTrigger){
                            CronTrigger cronTrigger = (CronTrigger)trigger;
                            triggerDTO.setCronExpression(cronTrigger.getCronExpression());
                            triggerDTO.setDescription(cronTrigger.getDescription());
                            triggerDTO.setNextFireTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(cronTrigger.getNextFireTime()));
                            triggerDTO.setPreviousFireTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(cronTrigger.getPreviousFireTime()));
                            triggerDTO.setPriority(cronTrigger.getPriority());
                            triggerDTO.setTriggerState(scheduler.getTriggerState(cronTrigger.getKey()).name());
                        }
                        triggerDTOs.add(triggerDTO);
                    }
                    jobDto.setTriggers(triggerDTOs);
                    jobs.add(jobDto);
                }
            }
        } catch (SchedulerException e) {
            log.debug(e.toString());
            return new ResponseModel(ResponseCode.Error,"查询失败",e);
        }

        return new ResponseModel(jobs);
    }

    private static Job getClass(String classname) throws Exception{
        Class<?> class1 = Class.forName(classname);
        return (Job)class1.newInstance();
    }
}
