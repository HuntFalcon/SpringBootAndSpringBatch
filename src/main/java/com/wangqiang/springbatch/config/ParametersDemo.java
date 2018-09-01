package com.wangqiang.springbatch.config;

import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@EnableBatchProcessing
public class ParametersDemo implements StepExecutionListener {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    private Map<String,JobParameter> parameters;

    @Bean
    public Job parameterJob(){
        return jobBuilderFactory.get("parameterJob")
                .start(parameterStep())
                .build();
    }

    //JoB执行的是step，Job使用的数据肯定是step中使用
    //我们只需要给step传递数据，如何给step传递参数
    //使用step级别的监听来传递数据
    @Bean
    public Step parameterStep() {
        return stepBuilderFactory.get("parameterStep")
                .listener(this)
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("输出参数接受的值"+parameters.get("info"));
                        return RepeatStatus.FINISHED;
                    }
                }).build();

    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        parameters=stepExecution.getJobParameters().getParameters();
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }
}
