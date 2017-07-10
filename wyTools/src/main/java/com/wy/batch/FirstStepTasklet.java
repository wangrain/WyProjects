package com.wy.batch;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

/**
 * 名称：  FirstStepTasklet
 * 作者:   rain.wang
 * 日期:   2016/8/24
 * 简介:
 */
public class FirstStepTasklet implements Tasklet {

    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        System.out.println("First Step !");
        System.out.println(chunkContext.getStepContext().getJobParameters().get("wy"));
        stepContribution.getExitStatus();
        return RepeatStatus.CONTINUABLE;
    }
}
