package com.epam.mp.converter;

import com.epam.mp.entity.Task;

import java.util.List;

public interface TaskConverter {

    Task convertTask(List<String> params);
}
