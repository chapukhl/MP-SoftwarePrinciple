package com.epam.mp.notifier.impl;

import com.epam.mp.notifier.TaskNotifier;
import org.springframework.stereotype.Component;

@Component
public class GreetingConsoleNotifier implements TaskNotifier {

    @Override
    public void postNotification() {
        System.out.println("Hello! This is Task Aggregator. " +
                "You are using interactive mode now. Use the next format " +
                "<aggregate function> number1 number2 ... numberN (e.g. sum 12 2 81) " +
                "To Switch to batch type. ");
    }
}

