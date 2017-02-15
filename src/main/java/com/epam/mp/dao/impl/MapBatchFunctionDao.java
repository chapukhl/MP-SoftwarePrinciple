package com.epam.mp.dao.impl;

import com.epam.mp.dao.FunctionDao;
import com.epam.mp.entity.GenericResult;
import com.epam.mp.exception.TaskValidationException;
import com.epam.mp.pipeline.GenericComputationPipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.epam.mp.constant.ApplicationConstants.INDEX_OF_THE_INPUT_FILE;
import static com.epam.mp.constant.ApplicationConstants.INDEX_OF_THE_OUTPUT_FILE;

@Component
public class MapBatchFunctionDao implements FunctionDao<Function<List<String>, GenericResult<List<Double>, String>>> {


    @Value("${function.batch}")
    private String batchFunction;

    @Autowired
    @Qualifier("commonComputationPipeline")
    private GenericComputationPipeline<Double> computationPipeline;

    private static Map<String, Function<List<String>, GenericResult<List<Double>, String>>> batchMap = new HashMap<>();

    @PostConstruct
    private void addInitialFunctions() {
        batchMap.put(batchFunction, this::computeBatch);
    }

    private GenericResult<List<Double>, String> computeBatch(List<String> fileNames) {
        List<Double> resultsList = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(fileNames.get(INDEX_OF_THE_INPUT_FILE)))) {
            stream.forEach(stringTask -> fillResultList(resultsList, stringTask));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        GenericResult.GenericResultBuilder<List<Double>, String> builder = createBuilder(fileNames, resultsList);
        return builder.build();
    }

    private void fillResultList(List<Double> doubleList, String stringTask) {
        Double result = computeSingleTask(stringTask);
        if (result != null) {
            doubleList.add(result);
        }
    }

    private List<Double> filterNullValues(List<Double> resultsList) {
        resultsList = resultsList.stream().filter(Objects::isNull).collect(Collectors.toList());
        return resultsList;
    }

    private GenericResult.GenericResultBuilder<List<Double>, String> createBuilder(List<String> fileNames,
                                                                                   List<Double> resultsList) {
        GenericResult.GenericResultBuilder<List<Double>, String> builder = new GenericResult.
                GenericResultBuilder<List<Double>, String>().result(resultsList);

        if (fileNames.size() > INDEX_OF_THE_OUTPUT_FILE) {
            String fileOutput = fileNames.get(INDEX_OF_THE_OUTPUT_FILE);
            builder.info(fileOutput);
        }
        return builder;
    }

    private Double computeSingleTask(String stringTask) {
        Double result = null;
        try {
            result = computationPipeline.compute(stringTask);
        } catch (IOException | TaskValidationException e) {
            System.err.println(e.getMessage());
        }
        return result;
    }

    @Override
    public Set<String> getAllFunctionNames() {
        return batchMap.keySet();
    }

    @Override
    public Function<List<String>, GenericResult<List<Double>, String>> getFunctionByName(String name) {
        return batchMap.get(name);
    }
}
