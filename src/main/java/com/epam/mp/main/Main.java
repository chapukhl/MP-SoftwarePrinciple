package com.epam.mp.main;

import com.epam.mp.exception.TaskConvertationException;
import com.epam.mp.exception.TaskValidationException;
import com.epam.mp.notifier.impl.GreetingConsoleNotifier;
import com.epam.mp.pipeline.GenericComputationPipeline;
import com.epam.mp.pipeline.impl.BatchComputationPipeline;
import com.epam.mp.pipeline.impl.CommonComputationPipeline;
import com.epam.mp.reader.GenericConsoleReader;
import com.epam.mp.reader.impl.ConsoleTaskReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Main {


    public static void main(String[] args) throws IOException {
        ApplicationContext ctx =
                new AnnotationConfigApplicationContext("com.epam.mp.*");
        GenericComputationPipeline pipeline;
        BatchComputationPipeline batchComputationPipeline = ctx.getBean(BatchComputationPipeline.class);
        CommonComputationPipeline commonComputationPipeline = ctx.getBean(CommonComputationPipeline.class);
        ctx.getBean(GreetingConsoleNotifier.class).postNotification();
        GenericConsoleReader consoleTaskReader = ctx.getBean(ConsoleTaskReader.class);


        while (true) {
            String taskString = consoleTaskReader.readConsoleTask();
            if (taskString.contains("batch")) {
                pipeline = batchComputationPipeline;
            } else {
                pipeline = commonComputationPipeline;
            }
            try {
                pipeline.compute(taskString);
            } catch (TaskValidationException | TaskConvertationException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
