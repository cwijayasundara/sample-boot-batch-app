package com.cham.samplebootbatchapp.listnener;

import com.cham.samplebootbatchapp.pojo.Person;
import com.cham.samplebootbatchapp.reader.GenericJdbcTemplateReaderimpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final String sql;

    private final Class mappedClass;

    @Autowired
    private GenericJdbcTemplateReaderimpl genericJdbcTemplate;

    //default constructor
    public JobCompletionNotificationListener(Class mappedClass, String query){
        this.mappedClass = mappedClass;
        this.sql = query;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");

            List<Person> results = genericJdbcTemplate.read(sql,mappedClass);

            for (Person person : results) {
                log.info("Found <" + person + "> in the database.");
            }
        }
    }
}
