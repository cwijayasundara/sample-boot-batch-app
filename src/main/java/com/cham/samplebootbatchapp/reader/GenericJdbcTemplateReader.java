package com.cham.samplebootbatchapp.reader;

import java.util.List;

public interface GenericJdbcTemplateReader {
    public <T> List<T> read(String sql, Class<T> mappedClass);
}
