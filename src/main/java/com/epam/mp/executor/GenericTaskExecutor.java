package com.epam.mp.executor;

import com.epam.mp.entity.GenericTask;
import com.epam.mp.entity.GenericTaskOutput;

public interface GenericTaskExecutor<T, R> {

    R executeTask(GenericTask<T, R> task);
}
