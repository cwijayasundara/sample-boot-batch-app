package com.cham.samplebootbatchapp;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=DemoJobDef.class)
public class DemoJobDefTest {

    // ref: https://docs.spring.io/spring-batch/4.0.x/reference/html/testing.html#testing
    private JobLauncherTestUtils jobLauncherTestUtils = new JobLauncherTestUtils();

    @Test
    public void hasTheJobCompleted() throws Exception {
        JobExecution jobExecution = jobLauncherTestUtils.launchJob();
        Assert.assertEquals("COMPLETED", jobExecution.getExitStatus().getExitCode());
    }
}