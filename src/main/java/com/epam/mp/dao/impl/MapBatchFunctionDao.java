package com.epam.mp.dao.impl;

import com.epam.mp.dao.FunctionDao;
import com.epam.mp.pipeline.GenericComputationPipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

import static com.epam.mp.constant.ApplicationConstants.INDEX_OF_THE_INPUT_FILE;

@Component
public class MapBatchFunctionDao implements FunctionDao<Function<List<String>, List<Double>>> {

    @Autowired
    @Qualifier("commonComputationPipeline")
    private GenericComputationPipeline<Double> computationPipeline;

    private static Map<String, Function<List<String>, List<Double>>> batchMap = new HashMap<>();

    @PostConstruct
    private void addInitialFunctions() {
        batchMap.put("batch", this::computeBatch);
    }

    private List<Double> computeBatch(List<String> fileNames) {
        List<Double> resultsList = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(fileNames.get(INDEX_OF_THE_INPUT_FILE)))) {
            stream.forEach(stringTask -> {
                resultsList.add(computeSingleTask(stringTask));
            });
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return resultsList;
    }

    private Double computeSingleTask(String stringTask) {
        Double result = null;
        try {
            result = computationPipeline.compute(stringTask);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return result;
    }

    @Override
    public Set<String> getAllFunctionNames() {
        return batchMap.keySet();
    }

    @Override
    public Function<List<String>, List<Double>> getFunctionByName(String name) {
        return batchMap.get(name);
    }
}
