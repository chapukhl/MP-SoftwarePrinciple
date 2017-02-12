package com.epam.mp.dao.impl;

import com.epam.mp.dao.FunctionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

@Component
public class MapUtilityFunctionDao implements FunctionDao<Supplier<String>>{

    @Autowired
    private MapDoubleFunctionDao mapDoubleFunctionDao;

    private static Map<String, Supplier<String>> utilityFunctionMap = new HashMap<>();

    @PostConstruct
    private void addInitialFunctions() {

        utilityFunctionMap.put("functions", () -> mapDoubleFunctionDao.getAllFunctionNames().toString());
    }


    @Override
    public Set<String> getAllFunctionNames() {
        return utilityFunctionMap.keySet();
    }

    @Override
    public Supplier<String> getFunctionByName(String name) {
        return utilityFunctionMap.get(name);
    }
}
