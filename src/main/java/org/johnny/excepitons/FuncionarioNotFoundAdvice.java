package org.johnny.excepitons;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class FuncionarioNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(FuncionarioNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String,String> exceptionHandler(FuncionarioNotFoundException exception){

        Map<String,String> errorMap=new HashMap<>();
        errorMap.put("errorMessage",exception.getMessage());

        return errorMap;

    }
}
