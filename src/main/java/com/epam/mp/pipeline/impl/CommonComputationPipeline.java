package com.epam.mp.pipeline.impl;

import com.epam.mp.converter.GenericConverter;
import com.epam.mp.entity.GenericTask;
import com.epam.mp.entity.GenericTaskOutput;
import com.epam.mp.executor.GenericTaskExecutor;
import com.epam.mp.pipeline.ComputationPipeline;
import com.epam.mp.reader.TaskReader;
import com.epam.mp.validator.GenericTaskValidator;
import com.epam.mp.writer.impl.ConsoleOutputWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class CommonComputationPipeline implements ComputationPipeline {

    @Autowired
    private TaskReader taskReader;

    @Autowired
    @Qualifier("preValidateConverter")
    private GenericConverter<String, List<String>> preValidateConverter;

    @Autowired
    private GenericTaskValidator<String> taskValidator;

    @Autowired
    @Qualifier("postValidateDoubleConverter")
    private GenericConverter<List<String>, GenericTask<Double, Double>> postValidateConverter;

    @Autowired
    private GenericTaskExecutor<Double,Double> executor;

    @Autowired
    @Qualifier("postExecuteDoubleConverter")
    private GenericConverter<Double, GenericTaskOutput<String>> resultConverter;

    @Autowired
    private ConsoleOutputWriter writer;

    @Override
    public void compute() throws IOException {
        String taskString = taskReader.readTask();
        List<String> listParams = preValidateConverter.convertTask(taskString);
        taskValidator.validateTask(listParams);
        GenericTask<Double,Double> task = postValidateConverter.convertTask(listParams);
        GenericTaskOutput output = resultConverter.convertTask(executor.executeTask(task));
        writer.writeOutput(output);

    }
}
