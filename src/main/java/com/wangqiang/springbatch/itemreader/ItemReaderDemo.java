package com.wangqiang.springbatch.itemreader;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@EnableBatchProcessing
@Configuration
public class ItemReaderDemo {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

//    @Autowired
//    JdbcCursorItemReaderBuilder jdbcCursorItemReaderBuilder;

    @Bean
    public Job itemReaderDemoJob(){
        return jobBuilderFactory.get("itemReaderDemoJob")
                .start(itemReaderDemoStep())
                .build();
    }

    @Bean
    public Step itemReaderDemoStep() {
        return stepBuilderFactory.get("itemReaderDemoStep")
                .<String,String>chunk(1)
                .reader(itemReaderDemoRead())
                //.processor(new MyProcessor())
                .writer(list -> {
                    for (String item:list){
                        System.out.println(item+"1");
                    }
                }).build();
    }

    private ItemWriter<String> itemReaderDemoWrite() {
        return null;
    }

//    public JdbcCursorItemReader jdbcCursorItemReader(){
//        JdbcCursorItemReader<Object> build = new JdbcCursorItemReaderBuilder<>().build();
//        build.setVerifyCursorPosition();
//    }

    @Bean
    public MyReader itemReaderDemoRead() {
        List<String> date=Arrays.asList("cat","dog","pig","duck");
        return new MyReader(date);
    }
}
