package com.cham.samplebootbatchapp;

import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.batch.core.Job;

import javax.sql.DataSource;

public class FunctionalTestBase {

    public JobLauncherTestUtils jobLauncherTestUtils = new JobLauncherTestUtils();

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    public Job testJob;

    @Autowired
    public DataSource dataSource;

    @Autowired
    public JobLauncher jobLauncher;

    @Autowired
    public JobRepository jobRepository;


    /*@Bean(name="dataSource")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:db;DB_CLOSE_DELAY=-1");
        dataSource.setUsername("sa");
        dataSource.setPassword("sa");
        return dataSource;
    }

    public JobRepository getJobRepository() throws Exception {
        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
        factory.setDataSource(dataSource);
        factory.setTransactionManager(new DataSourceTransactionManager(dataSource));
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    public JobLauncher getJobLauncher() throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(getJobRepository());
        jobLauncher.afterPropertiesSet();
        return jobLauncher;
    }*/
}
