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
public class BatchComputationPipeline implements GenericComputationPipeline<List<Double>> {

    @Autowired
    @Qualifier("preValidateConverter")
    private GenericConverter<String, List<String>> preValidateConverter;

    @Autowired
    private GenericTaskValidator<String> taskValidator;

    @Autowired
    @Qualifier("postValidateStringConverter")
    private GenericConverter<List<String>, GenericTask<List<String>, List<Double>>> postValidateConverter;

    @Autowired
    @Qualifier("batchTaskExecutor")
    private GenericTaskExecutor<List<String>, List<Double>> executor;

//    @Autowired
//    @Qualifier("postExecuteDoubleConverter")
//    private GenericConverter<Double, GenericTaskOutput<String>> resultConverter;
//
//    @Autowired
//    private ConsoleOutputWriter writer;


    @Override
    public List<Double> compute(String taskString) throws IOException {
        List<String> listParams = preValidateConverter.convertTask(taskString);
        taskValidator.validateTask(listParams);
        GenericTask<List<String>, List<Double>> task = postValidateConverter.convertTask(listParams);
        List<Double> result = executor.executeTask(task);
//        GenericTaskOutput output = resultConverter.convertTask(result);
//        writer.writeOutput(output);

        return result;
    }
}
