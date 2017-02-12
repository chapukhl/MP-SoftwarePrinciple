package com.epam.mp.executor.impl;

import com.epam.mp.entity.GenericTask;
import com.epam.mp.executor.GenericTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BatchTaskExecutor implements GenericTaskExecutor<List<String>, List<Double>> {

    @Override
    public List<Double> executeTask(GenericTask<List<String>, List<Double>> task) {
        return task.getFunction().apply(task.getParameters());
    }
}
