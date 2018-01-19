package com.cham.samplebootbatchapp;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.JobExecution;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@ActiveProfiles("int")
@RunWith(SpringRunner.class)
@SpringBootTest(classes={DemoJobDef.class,SampleBootBatchAppApplication.class })
@ContextConfiguration(classes={DemoJobDef.class})
public class SampleBootBatchAppApplicationTests extends FunctionalTestBase {

	@Before
	public void setUp() throws Exception{
		jobLauncherTestUtils.setJobLauncher(jobLauncher);
		jobLauncherTestUtils.setJobRepository(jobRepository);
		jobLauncherTestUtils.setJob(testJob);
	}

	@Test
	public void hasTheJobCompleted() throws Exception {
		JobExecution jobExecution = jobLauncherTestUtils.launchJob();
		Assert.assertEquals("COMPLETED", jobExecution.getExitStatus().getExitCode());
		List<String> personList = jdbcTemplate
				.queryForList("SELECT first_name from people", String.class);
		junit.framework.Assert.assertNotNull(personList);
		junit.framework.Assert.assertEquals(10, personList.size());
	}


	@After
	public void tearDown() throws Exception{

	}
}
