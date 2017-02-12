package com.epam.mp.reader;

import java.io.IOException;
import java.util.List;

public interface GenericConsoleReader {

    String NEW_LINE_SYMBOL = ">";

    String readConsoleTask() throws IOException;

}
