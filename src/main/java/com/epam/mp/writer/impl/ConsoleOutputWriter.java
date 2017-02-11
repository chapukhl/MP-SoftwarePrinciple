package com.epam.mp.writer.impl;

import com.epam.mp.entity.GenericTaskOutput;
import com.epam.mp.writer.OutputWriter;
import org.springframework.stereotype.Component;

@Component
public class ConsoleOutputWriter implements OutputWriter {

    @Override
    public void writeOutput(GenericTaskOutput output) {
        System.out.println(output.getOutput());
    }
}
