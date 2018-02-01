package com.cham.samplebootbatchapp.listnener;

import com.cham.samplebootbatchapp.pojo.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private String sql = "SELECT first_name, last_name FROM people";

    private Class mappedClass;

    //default constructor
    public JobCompletionNotificationListener(Class mappedClass){
        this.mappedClass = mappedClass;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");

            //List<Person> results = jdbcTemplate.query(sql,new BeanPropertyRowMapper(Person.class));
            List<Person> results = jdbcTemplate.query(sql,new BeanPropertyRowMapper(mappedClass));

            for (Person person : results) {
                log.info("Found <" + person + "> in the database.");
            }
        }
    }
}
