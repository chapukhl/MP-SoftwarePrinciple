package com.epam.mp.util;

import com.epam.mp.dao.FunctionDao;

public final class DaoChecker {

    private DaoChecker(){}

    public static boolean checkDaoContainFunction(FunctionDao functionDao, String functionName){
        return functionDao.getAllFunctionNames().contains(functionName.toLowerCase());
    }
}
