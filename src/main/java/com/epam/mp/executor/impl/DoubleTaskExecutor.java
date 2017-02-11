package com.epam.mp.executor.impl;

import com.epam.mp.entity.GenericTask;
import com.epam.mp.executor.GenericTaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class DoubleTaskExecutor implements GenericTaskExecutor<Double, Double> {


    @Override
    public Double executeTask(GenericTask<Double, Double> task) {
        return task.getFunction().apply(task.getParameters());
    }
}
