package com.epam.mp.dao;


import java.util.Set;
import java.util.function.Function;

public interface FunctionDao<T, R> {

    Set<Function<T, R>> getAllFunctions();

    Set<String> getAllFunctionNames();

    Function<T, R> getFunctionByName(String name);
}
