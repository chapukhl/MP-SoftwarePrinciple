package com.epam.mp.dao.impl;

import com.epam.mp.dao.FunctionDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;


@Component
public class MapDoubleFunctionDao implements FunctionDao<Function<List<Double>, Double>> {

    @Value("${function.sum}")
    private String sumFunction;

    @Value("${function.min}")
    private String minFunction;

    @Value("${function.max}")
    private String maxFunction;

    @Value("${function.avg}")
    private String avgFunction;

    private static Map<String, Function<List<Double>, Double>> functionMap = new HashMap<>();

    @PostConstruct
    private void addInitialFunctions() {
        functionMap.put(sumFunction, (List<Double> parameters) -> parameters.stream().mapToDouble(Double::doubleValue).sum());
        functionMap.put(maxFunction, (List<Double> parameters) -> parameters.stream().mapToDouble(Double::doubleValue).max()
                .getAsDouble());
        functionMap.put(minFunction, (List<Double> parameters) -> parameters.stream().mapToDouble(Double::doubleValue).min()
                .getAsDouble());
        functionMap.put(avgFunction, (List<Double> parameters) -> parameters.stream().mapToDouble(Double::doubleValue)
                .average().getAsDouble());

    }


    @Override
    public Set<String> getAllFunctionNames() {
        return functionMap.keySet();
    }

    @Override
    public Function<List<Double>, Double> getFunctionByName(String functionName) {
        return functionMap.get(functionName);
    }
}
