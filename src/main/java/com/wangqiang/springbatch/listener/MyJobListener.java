package com.wangqiang.springbatch.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class MyJobListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println(jobExecution.getJobInstance()+"job before listener");

    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println(jobExecution.getJobInstance()+"job fater listener");
    }
}
