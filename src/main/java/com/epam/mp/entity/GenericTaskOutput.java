package com.epam.mp.entity;

public class GenericTaskOutput<T> {

    private T output;

    public GenericTaskOutput(T output) {
        this.output = output;
    }

    public T getOutput() {
        return output;
    }
}
