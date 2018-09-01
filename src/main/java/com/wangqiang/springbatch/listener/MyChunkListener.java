package com.wangqiang.springbatch.listener;

import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.core.annotation.BeforeChunk;
import org.springframework.batch.core.scope.context.ChunkContext;

public class MyChunkListener {

    @BeforeChunk
    public void berforeChunk(ChunkContext context){
        System.out.println("ChunkContext context before" +context.getStepContext().getStepName());
    }

    @AfterChunk
    public void faterChunk(ChunkContext context){
        System.out.println("ChunkContext context fater" +context.getStepContext().getStepName());

    }
}
