package com.cham.samplebootbatchapp.reader;

import org.springframework.batch.item.*;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;

public class GenericJdbcCursorItemReader<T> implements ItemReader<T>, ItemStream {

    private JdbcCursorItemReader<T> itemReader;

    public GenericJdbcCursorItemReader(DataSource dataSource, String sql, Class<T> mappedClass){
        itemReader = new JdbcCursorItemReader<T>();
        itemReader.setDataSource(dataSource);
        itemReader.setRowMapper(new BeanPropertyRowMapper(mappedClass));
        itemReader.setSql(sql);
    }

    public JdbcCursorItemReader getItemReader() {
        return itemReader;
    }

    /**
     * Synchronized method to read data from DB to support multiple threads.
     */

    @Override
    public synchronized T read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return itemReader.read();
    }

    /**
     * Method to open IO stream for ItemReader.
     */
    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        itemReader.open(executionContext);
    }

    /**
     * Method to update IO stream for ItemReader.
     */
    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        itemReader.update(executionContext);
    }

    /**
     * Method to close IO stream for ItemReader.
     */
    @Override
    public void close() throws ItemStreamException {
        itemReader.close();
    }

}