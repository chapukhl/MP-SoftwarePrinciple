package com.epam.mp.dao;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;


@Component
public class MapDoubleFunctionDao implements FunctionDao<List<Double>, Double> {

    private static Map<String, Function<List<Double>, Double>> functionMap = new HashMap<>();

    @PostConstruct
    private void addInitialFunctions() {
        functionMap.put("sum", (List<Double> parameters) -> parameters.stream().mapToDouble(Double::doubleValue).sum());
        functionMap.put("max", (List<Double> parameters) -> parameters.stream().mapToDouble(Double::doubleValue).max()
                .getAsDouble());
        functionMap.put("min", (List<Double> parameters) -> parameters.stream().mapToDouble(Double::doubleValue).min()
                .getAsDouble());
        functionMap.put("avg", (List<Double> parameters) -> parameters.stream().mapToDouble(Double::doubleValue)
                .average().getAsDouble());

    }

    @Override
    public Set<Function<List<Double>, Double>> getAllFunctions() {

        Set<Function<List<Double>, Double>> functions = Collections.emptySet();
        functions.addAll(functionMap.values());

        return functions;
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
