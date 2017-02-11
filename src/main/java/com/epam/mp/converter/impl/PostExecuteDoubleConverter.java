package com.epam.mp.converter.impl;

import com.epam.mp.converter.GenericConverter;
import com.epam.mp.entity.GenericTaskOutput;
import org.springframework.stereotype.Component;

@Component
public class PostExecuteDoubleConverter implements GenericConverter<Double, GenericTaskOutput<String>> {

    @Override
    public GenericTaskOutput<String> convertTask(Double result) {
        return new GenericTaskOutput<>("Result: " + result);
    }
}
