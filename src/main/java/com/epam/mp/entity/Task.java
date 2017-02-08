package com.epam.mp.entity;

import java.util.List;
import java.util.function.BiFunction;

public class Task <T> {

    private List<T> numbers;

    private BiFunction<T,T,T>  function;

    public Task(List<T> numbers, BiFunction<T, T, T> function) {
        this.numbers = numbers;
        this.function = function;
    }

    @Override
    public String toString() {
        return "Task{" +
                "numbers=" + numbers +
                ", function=" + function +
                '}';
    }
}
