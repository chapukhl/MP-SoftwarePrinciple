package com.epam.mp.pipeline;

import java.io.IOException;

public interface GenericComputationPipeline<T> {

    T compute(String taskString) throws IOException;
}
