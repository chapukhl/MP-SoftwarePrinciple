package com.epam.mp.entity;

import java.util.function.Function;

public class GenericResult<T, U> {

    private T result;

    private U additionalInfo;

    private GenericResult() {

    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public U getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(U additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    @Override
    public String toString() {
        return "GenericResult{" +
                "result=" + result +
                ", additionalInfo=" + additionalInfo +
                '}';
    }

    public static class GenericResultBuilder<T, U> {

        private GenericResult<T, U> genericResult;

        public GenericResultBuilder() {
            genericResult = new GenericResult<>();
        }

        public GenericResult.GenericResultBuilder result(T result) {
            genericResult.setResult(result);
            return this;
        }


        public GenericResult.GenericResultBuilder info(U info) {
            genericResult.setAdditionalInfo(info);
            return this;
        }


        public GenericResult<T, U> build() {
            return genericResult;
        }
    }
}
