package com.epam.mp.reader.impl;

import com.epam.mp.reader.GenericConsoleReader;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class ConsoleTaskReader implements GenericConsoleReader {

    private BufferedReader consoleBufferedReader = createConsoleReader();

    public String readConsoleTask() throws IOException {
        System.out.print(NEW_LINE_SYMBOL);
        return consoleBufferedReader.readLine();
    }

    private static BufferedReader createConsoleReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}
