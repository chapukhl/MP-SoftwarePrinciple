package com.epam.mp.converter;

public interface GenericConverter<T,U> {

    U convertTask(T inputObject);
}
