package com.zng.system.job.service;

import com.zng.common.entity.ResponseModel;
import com.zng.system.job.entity.JobDto;

/**
 * Created by John.Zhang on 2018/2/26.
 */
public interface JobService {

    ResponseModel addjob(JobDto jobDto);

    ResponseModel pausejob(JobDto jobDto);

    ResponseModel resumejob(JobDto jobDto);

    ResponseModel rescheduleJob(JobDto jobDto);

    ResponseModel deletejob(JobDto jobDto);

    ResponseModel queryjob();
}
