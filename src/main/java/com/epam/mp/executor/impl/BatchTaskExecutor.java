package com.epam.mp.executor.impl;

import com.epam.mp.entity.GenericResult;
import com.epam.mp.entity.GenericTask;
import com.epam.mp.executor.GenericTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BatchTaskExecutor implements GenericTaskExecutor<List<String>, GenericResult<List<Double>,String>> {


    @Override
    public GenericResult<List<Double>, String> executeTask(GenericTask<List<String>,
            GenericResult<List<Double>, String>> task) {
        return task.getFunction().apply(task.getParameters());
    }
}
