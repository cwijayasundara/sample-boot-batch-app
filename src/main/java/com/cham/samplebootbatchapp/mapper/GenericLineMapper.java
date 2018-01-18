package com.cham.samplebootbatchapp.mapper;

import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;

/**
 * Created by Chaminda Wijayasundara on 11 Jan 2018
 */

public class GenericLineMapper<T> {

    private DefaultLineMapper<T> defaultLineMapper;

    private DelimitedLineTokenizer delimitedLineTokenizer;

    private BeanWrapperFieldSetMapper<T> beanWrapperFieldSetMapper;

    public DefaultLineMapper getDefaultLineMapper() {
        return defaultLineMapper;
    }

    public GenericLineMapper(String[] params, Class mappedClass){

        delimitedLineTokenizer = new DelimitedLineTokenizer();
        delimitedLineTokenizer.setNames(params);

        beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<T>();
        beanWrapperFieldSetMapper.setTargetType(mappedClass);

        defaultLineMapper = new DefaultLineMapper<T>();
        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
        defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);
    }

}

