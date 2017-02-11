package com.epam.mp.reader;

import com.epam.mp.config.ApplicationConfig;
import com.epam.mp.exception.TaskValidationException;
import com.epam.mp.validator.GenericTaskValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Arrays;
import java.util.Collections;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class CommonTaskValidatorTest {

    @Autowired
    private GenericTaskValidator<String> commonValidator;

    @Test(expected = TaskValidationException.class)
    public void emptyStringTest()  {
        commonValidator.validateTask(Collections.singletonList(""));
    }

    @Test(expected = TaskValidationException.class)
    public void incorrectFunctionNameTest()  {
        commonValidator.validateTask(Collections.singletonList("qwerty"));
    }

    @Test(expected = TaskValidationException.class)
    public void notEnoughParametersTest()  {
        commonValidator.validateTask(Collections.singletonList("sum"));
    }

    @Test
    public void correctDefenitionTest()  {
        commonValidator.validateTask(Arrays.asList("sum","4","5"));
        commonValidator.validateTask(Arrays.asList("min","10.5","10.49"));
        commonValidator.validateTask(Arrays.asList("avg","5","6","7"));
    }
}
