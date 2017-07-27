package com.kaishengit.quartz.jobs;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class Weixin implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
       JobDataMap jobDataMap =jobExecutionContext.getMergedJobDataMap();
        Integer accountId= (Integer) jobDataMap.get("account");
        String message= (String) jobDataMap.get("name");
        System.out.println("accountId"+accountId+"message"+message);
    }
}
