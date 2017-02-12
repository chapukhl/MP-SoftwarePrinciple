package com.epam.mp.dao;


import java.util.Set;
import java.util.function.Function;

public interface FunctionDao<T> {

    Set<String> getAllFunctionNames();

    T getFunctionByName(String name);
}
