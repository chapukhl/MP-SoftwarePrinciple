package com.epam.mp.pipeline.impl;

import com.epam.mp.converter.GenericConverter;
import com.epam.mp.entity.GenericResult;
import com.epam.mp.entity.GenericTask;
import com.epam.mp.entity.GenericTaskOutput;
import com.epam.mp.executor.GenericTaskExecutor;
import com.epam.mp.pipeline.GenericComputationPipeline;
import com.epam.mp.validator.GenericTaskValidator;
import com.epam.mp.writer.GenericFileWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class BatchComputationPipeline implements GenericComputationPipeline<GenericResult<List<Double>,String>> {

    @Autowired
    @Qualifier("preValidateConverter")
    private GenericConverter<String, List<String>> preValidateConverter;

    @Autowired
    private GenericTaskValidator<String> taskValidator;

    @Autowired
    @Qualifier("postValidateBatchConverter")
    private GenericConverter<List<String>, GenericTask<List<String>,
            GenericResult<List<Double>,String>>> postValidateConverter;

    @Autowired
    @Qualifier("batchTaskExecutor")
    private GenericTaskExecutor<List<String>, GenericResult<List<Double>,String>> executor;

    @Autowired
    @Qualifier("postExecuteBatchConverter")
    private GenericConverter<GenericResult<List<Double>,String>,
            GenericTaskOutput<List<String>>> postExecuteBatchConverter;

    @Autowired
    private GenericFileWriter<String, List<String>> writer;


    @Override
    public GenericResult<List<Double>,String> compute(String taskString) throws IOException {
        List<String> listParams = preValidateConverter.convertTask(taskString);
        taskValidator.validateTask(listParams);
        GenericTask<List<String>, GenericResult<List<Double>,String>> task =
                postValidateConverter.convertTask(listParams);
        GenericResult<List<Double>,String> result = executor.executeTask(task);
        GenericTaskOutput<List<String>> output = postExecuteBatchConverter.convertTask(result);
        writer.writeToFile(output, result.getAdditionalInfo());

        return result;
    }
}
