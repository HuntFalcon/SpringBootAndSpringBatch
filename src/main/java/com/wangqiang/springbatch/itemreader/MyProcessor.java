package com.wangqiang.springbatch.itemreader;

import org.springframework.batch.item.ItemProcessor;

public class MyProcessor implements ItemProcessor<String, String> {
    @Override
    public String process(String s) throws Exception {
        return null;
    }
}
