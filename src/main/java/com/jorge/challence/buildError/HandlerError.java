package com.jorge.challence.buildError;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Component
public class HandlerError {
  
  public HashMap<String, HashMap<String, String>> getErrors(BindingResult result){
    HashMap<String, String> hasError = new HashMap<String, String>();
    HashMap<String, HashMap<String, String>> toErorrs = new HashMap<String, HashMap<String, String>>();

    List<FieldError> fieldErrors = result.getFieldErrors();
    fieldErrors.forEach( f -> {
      hasError.put(f.getField(), f.getDefaultMessage());
    });
    toErorrs.put("errors: ", hasError);
    return toErorrs;
  }
}
