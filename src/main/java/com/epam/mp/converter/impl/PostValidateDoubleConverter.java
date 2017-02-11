package com.epam.mp.converter.impl;

import com.epam.mp.converter.GenericConverter;
import com.epam.mp.dao.FunctionDao;
import com.epam.mp.entity.GenericTask;
import com.epam.mp.exception.TaskConvertationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.epam.mp.constant.ApplicationConstants.INDEX_OF_THE_FUNCTION_NAME;

@Component
public class PostValidateDoubleConverter implements GenericConverter<List<String>, GenericTask<Double, Double>> {

    @Autowired
    private FunctionDao<Double, Double> mapDoubleFunctionDao;


    @Override
    public GenericTask<Double, Double> convertTask(List<String> inputObject) {
        Function<Double, Double> function = mapDoubleFunctionDao
                .getFunctionByName(inputObject.get(INDEX_OF_THE_FUNCTION_NAME));
        inputObject.remove(INDEX_OF_THE_FUNCTION_NAME);
        List<Double> doubleParameters = convertToDoubleList(inputObject);
        return buildTask(function, doubleParameters);
    }

    private List<Double> convertToDoubleList(List<String> inputObject) {
        List<Double> doubleParameters = null;
        try {
            doubleParameters = inputObject.stream().map(Double::parseDouble)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new TaskConvertationException();
        }
        return doubleParameters;
    }

    private GenericTask<Double, Double> buildTask(Function<Double, Double> function, List<Double> doubleParameters) {
        return new GenericTask.GenericTaskBuilder<Double, Double>().function(function)
                .parameters(doubleParameters).build();
    }
}
