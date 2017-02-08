package com.epam.mp.executor;

import com.epam.mp.entity.Task;
import com.epam.mp.entity.TaskOutput;

public interface TaskExecutor {

    TaskOutput executeTask(Task task);
}
