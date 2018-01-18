package com.cham.samplebootbatchapp.reader;

import com.cham.samplebootbatchapp.mapper.GenericLineMapper;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by Chaminda Wijayasundara on 11 Jan 2018
 */

public class GenericFlatFileItemReader<T> extends FlatFileItemReader<T>{

    private FlatFileItemReader<T> delegate;

    // constructor
    public GenericFlatFileItemReader(String filePath, String[] params, Class targetClass){

        delegate = new FlatFileItemReader<T>();
        delegate.setResource(new ClassPathResource(filePath));

        GenericLineMapper<T> genericLineMapper = new GenericLineMapper<T>(params, targetClass);
        delegate.setLineMapper(genericLineMapper.getDefaultLineMapper());
    }

    public FlatFileItemReader getDelegate() {
        return delegate;
    }

}
