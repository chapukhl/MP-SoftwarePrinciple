package com.epam.mp.entity;

import java.util.function.BiFunction;
import java.util.function.Function;

public class GenericTask<T, R> {

    private T parameters;

    private Function<T, R> function;


    private GenericTask() {

    }

    public T getParameters() {
        return parameters;
    }

    public void setParameters(T parameters) {
        this.parameters = parameters;
    }

    public Function<T, R> getFunction() {
        return function;
    }

    public void setFunction(Function<T, R> function) {
        this.function = function;
    }

    @Override
    public String toString() {
        return "Task{" +
                "parameters=" + parameters +
                ", function=" + function +
                '}';
    }

    public static class GenericTaskBuilder<T, R> {

        private GenericTask<T, R> genericTask;

        public GenericTaskBuilder() {
            genericTask = new GenericTask<>();
        }

        public GenericTaskBuilder function(Function<T,R> function) {
            genericTask.setFunction(function);
            return this;
        }


        public GenericTaskBuilder parameters(T parameters) {
            genericTask.setParameters(parameters);
            return this;
        }


        public GenericTask<T, R> build() {
            return genericTask;
        }
    }
}
