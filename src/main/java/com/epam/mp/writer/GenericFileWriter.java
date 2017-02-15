package com.epam.mp.writer;

import com.epam.mp.entity.GenericTaskOutput;

public interface GenericFileWriter<T,U> {

    void writeToFile(GenericTaskOutput<U> output,T fileIdentifier);
}
