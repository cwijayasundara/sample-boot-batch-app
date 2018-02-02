package com.cham.samplebootbatchapp.reader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GenericJdbcTemplateReaderimpl implements GenericJdbcTemplateReader{

    @Autowired
    public JdbcTemplate jdbcTemplate;

    public <T> List<T> read(String sql, Class<T> mappedClass){
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper(mappedClass));
    }
}
