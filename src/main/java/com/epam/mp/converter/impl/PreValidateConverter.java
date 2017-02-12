package com.epam.mp.converter.impl;

import com.epam.mp.converter.GenericConverter;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Component
public class PreValidateConverter implements GenericConverter<String, List<String>> {

    private static final String REGEX = "\\s+";

    @Override
    public List<String> convertTask(String inputString){
        return new LinkedList<>(Arrays.asList(inputString.split(REGEX)));
    }
}
