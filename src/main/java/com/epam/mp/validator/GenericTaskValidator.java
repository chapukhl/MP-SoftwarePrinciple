package com.epam.mp.validator;

import java.util.List;

public interface GenericTaskValidator<T> {

    void validateTask(List<T> params);
}
