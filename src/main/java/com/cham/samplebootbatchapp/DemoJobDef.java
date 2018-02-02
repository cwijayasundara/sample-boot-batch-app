package com.cham.samplebootbatchapp;

import com.cham.samplebootbatchapp.jobdef.JobBase;
import com.cham.samplebootbatchapp.listnener.JobCompletionNotificationListener;
import com.cham.samplebootbatchapp.pojo.Person;
import com.cham.samplebootbatchapp.processor.PersonItemProcessor;
import com.cham.samplebootbatchapp.reader.GenericFlatFileItemReader;
import com.cham.samplebootbatchapp.writer.GenericJdbcBatchItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:application.properties")
@EnableBatchProcessing
public class DemoJobDef extends JobBase {

    @Value("${insertSqlString}")
    private String insertQuery;

    @Value("${filePath}")
    private String filePath;

    @Value("${file.headers}")
    private String[] headerParams;

    @Value("${spring.batch.job.name}")
    private String jobName;

    @Value("${spring.batch.step.name}")
    private String stepName;

    @Value("${spring.batch.job.chunk.size}")
    private int jobChunkSize;

    private String selectQuery = "SELECT first_name, last_name FROM people";

    @Bean
    public FlatFileItemReader<Person> reader() {
        return new GenericFlatFileItemReader<Person>(filePath,headerParams, Person.class).getDelegate();
    }

    @Bean
    public JobExecutionListenerSupport listener(){
        return new JobCompletionNotificationListener(Person.class, selectQuery);
    }

    @Bean
    public JdbcBatchItemWriter<Person> writer() {
        return new GenericJdbcBatchItemWriter<Person>(insertQuery,dataSource).getDeligate();
    }

    @Bean
    public PersonItemProcessor processor() {
        return new PersonItemProcessor();
    }

    @Bean
    public Job importUserJob() {
        return jobBuilderFactory.get(jobName)
                .incrementer(new RunIdIncrementer())
                .listener(listener())
                .flow(step1())
                .end()
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get(stepName)
                .<Person, Person> chunk(jobChunkSize)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }
}