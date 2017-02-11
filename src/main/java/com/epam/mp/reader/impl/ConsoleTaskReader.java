package com.epam.mp.reader.impl;

import com.epam.mp.reader.TaskReader;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

@Component
public class ConsoleTaskReader implements TaskReader {

    private BufferedReader consoleBufferedReader = createConsoleReader();

    public String readTask() throws IOException {
        return consoleBufferedReader.readLine();
    }

    private static BufferedReader createConsoleReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}
