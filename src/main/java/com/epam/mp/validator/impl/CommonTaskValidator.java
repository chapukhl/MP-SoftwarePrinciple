package com.epam.mp.validator.impl;

import com.epam.mp.dao.FunctionDao;
import com.epam.mp.exception.TaskValidationException;
import com.epam.mp.validator.GenericTaskValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import static com.epam.mp.constant.ApplicationConstants.INDEX_OF_THE_FUNCTION_NAME;
import static com.epam.mp.constant.ApplicationConstants.MIN_PARAMETER_NUMBER;

import java.util.List;

@Component
public class CommonTaskValidator implements GenericTaskValidator<String> {



    @Autowired
    private FunctionDao mapDoubleFunctionDao;

    @Override
    public void validateTask(List<String> params) {
        if (params.size() == INDEX_OF_THE_FUNCTION_NAME) {
            throw new TaskValidationException("Enter function name");
        }
        String functionName = params.get(INDEX_OF_THE_FUNCTION_NAME);
        if (!checkFunctionName(functionName)) {
            throw new TaskValidationException("Incorrect function name!");
        }
        if (params.size() == MIN_PARAMETER_NUMBER) {
            throw new TaskValidationException("Enter parameters");
        }
    }

    private boolean checkFunctionName(String functionName) {
       return mapDoubleFunctionDao.getAllFunctionNames().contains(functionName.toLowerCase());
    }
}
