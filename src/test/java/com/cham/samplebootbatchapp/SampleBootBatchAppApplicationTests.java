package com.cham.samplebootbatchapp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes=DemoJobDef.class)
public class SampleBootBatchAppApplicationTests {

	// ref: https://docs.spring.io/spring-batch/4.0.x/reference/html/testing.html#testing
	private JobLauncherTestUtils jobLauncherTestUtils = new JobLauncherTestUtils();

	@Before
	public void setUp(){

	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void hasTheJobCompleted() throws Exception {
		JobExecution jobExecution = jobLauncherTestUtils.launchJob();
		Assert.assertEquals("COMPLETED", jobExecution.getExitStatus().getExitCode());
	}

}
