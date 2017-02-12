package com.epam.mp.converter.impl;

import com.epam.mp.converter.GenericConverter;
import com.epam.mp.dao.FunctionDao;
import com.epam.mp.dao.impl.MapBatchFunctionDao;
import com.epam.mp.entity.GenericTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;


import static com.epam.mp.constant.ApplicationConstants.INDEX_OF_THE_FUNCTION_NAME;

@Component
public class PostValidateStringConverter implements
        GenericConverter<List<String>, GenericTask<List<String>, List<Double>>> {

    @Autowired
    @Qualifier("mapBatchFunctionDao")
    private FunctionDao<Function<List<String>, List<Double>>> mapBatchFunctionDao;

    @Override
    public GenericTask<List<String>, List<Double>> convertTask(List<String> inputObject) {
        Function<List<String>, List<Double>> function = mapBatchFunctionDao
                .getFunctionByName(inputObject.get(INDEX_OF_THE_FUNCTION_NAME).toLowerCase());
        inputObject.remove(INDEX_OF_THE_FUNCTION_NAME);
        return buildTask(function, inputObject);
    }

    private GenericTask<List<String>, List<Double>> buildTask(Function<List<String>, List<Double>> function,
                                                              List<String> stringsParameters) {
        return new GenericTask.GenericTaskBuilder<List<String>, List<Double>>().function(function)
                .parameters(stringsParameters).build();
    }
}
