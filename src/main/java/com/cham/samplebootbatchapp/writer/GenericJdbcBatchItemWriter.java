package com.cham.samplebootbatchapp.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Chaminda Wijayasundara on 12 Jan 2018
 */

public class GenericJdbcBatchItemWriter<T> implements ItemWriter <T> {

    private JdbcBatchItemWriter<T> deligate;

    public GenericJdbcBatchItemWriter(String sql, DataSource dataSource){
        deligate = new JdbcBatchItemWriter<T>();
        deligate.setDataSource(dataSource);
        deligate.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        deligate.setSql(sql);
    }

    public void write(List<? extends T> items) throws Exception{
        deligate.write(items);
    }

    public JdbcBatchItemWriter<T> getDeligate(){
        return this.deligate;
    }
}

