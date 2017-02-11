package com.epam.mp.main;

import com.epam.mp.pipeline.ComputationPipeline;
import com.epam.mp.pipeline.impl.CommonComputationPipeline;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Main {


    public static void main(String[] args) throws IOException {
        ApplicationContext ctx =
                new AnnotationConfigApplicationContext("com.epam.mp.*");
        ComputationPipeline pipeline = ctx.getBean(CommonComputationPipeline.class);
        while (true) {
            pipeline.compute();
        }
    }
}
