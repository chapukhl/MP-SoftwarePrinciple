package com.epam.mp.writer.impl;

import com.epam.mp.entity.GenericTaskOutput;
import com.epam.mp.writer.GenericFileWriter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Component
public class FileOutputWriter implements GenericFileWriter<String, List<String>> {

    @Override
    public void writeToFile(GenericTaskOutput<List<String>> output, String fileIdentifier) {
        if (fileIdentifier != null) {
            Path path = Paths.get(fileIdentifier);

            writeToFile(path, output.getOutput());
        }
    }

    private void writeToFile(Path path, List<String> lines) {
        try {
            Files.write(path, lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
