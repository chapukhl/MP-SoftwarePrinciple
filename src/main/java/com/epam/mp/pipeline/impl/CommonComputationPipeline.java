package com.epam.mp.pipeline.impl;

import com.epam.mp.converter.GenericConverter;
import com.epam.mp.entity.GenericTask;
import com.epam.mp.entity.GenericTaskOutput;
import com.epam.mp.executor.GenericTaskExecutor;
import com.epam.mp.pipeline.GenericComputationPipeline;
import com.epam.mp.validator.GenericTaskValidator;
import com.epam.mp.writer.impl.ConsoleOutputWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class CommonComputationPipeline implements GenericComputationPipeline<Double> {

    @Autowired
    @Qualifier("preValidateConverter")
    private GenericConverter<String, List<String>> preValidateConverter;

    @Autowired
    private GenericTaskValidator<String> taskValidator;

    @Autowired
    @Qualifier("postValidateDoubleConverter")
    private GenericConverter<List<String>, GenericTask<Double, Double>> postValidateConverter;

    @Autowired
    @Qualifier("doubleTaskExecutor")
    private GenericTaskExecutor<Double,Double> executor;

    @Autowired
    @Qualifier("postExecuteDoubleConverter")
    private GenericConverter<Double, GenericTaskOutput<String>> resultConverter;

    @Autowired
    private ConsoleOutputWriter writer;

    @Override
    public Double compute(String stringTask) throws IOException {

            List<String> listParams = preValidateConverter.convertTask(stringTask);
            taskValidator.validateTask(listParams);
            GenericTask<Double, Double> task = postValidateConverter.convertTask(listParams);
            Double result = executor.executeTask(task);
            GenericTaskOutput output = resultConverter.convertTask(result);
            writer.writeOutput(output);

            return result;
    }
}
