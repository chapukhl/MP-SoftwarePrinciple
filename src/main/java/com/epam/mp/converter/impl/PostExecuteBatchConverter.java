package com.epam.mp.converter.impl;

import com.epam.mp.converter.GenericConverter;
import com.epam.mp.entity.GenericResult;
import com.epam.mp.entity.GenericTaskOutput;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostExecuteBatchConverter implements GenericConverter<GenericResult<List<Double>,String>,
        GenericTaskOutput<List<String>>> {

    @Override
    public GenericTaskOutput<List<String>> convertTask(GenericResult<List<Double>,String> inputObject) {
        return new GenericTaskOutput<>(inputObject.getResult().stream().map(Object::toString)
                .collect(Collectors.toList()));
    }
}
