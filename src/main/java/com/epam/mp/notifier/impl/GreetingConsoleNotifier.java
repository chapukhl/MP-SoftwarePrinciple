package com.epam.mp.notifier.impl;

import com.epam.mp.notifier.TaskNotifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GreetingConsoleNotifier implements TaskNotifier {

    @Value("${message.greeting}")
    private String greeting;

    @Override
    public void postNotification() {
        System.out.println(greeting);
    }
}

