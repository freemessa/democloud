package com.example.snmpdemo.utils;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Slf4j
public class TargetUtil {

    static public Result getValidateError(BindingResult bindingResult) {

        if(bindingResult.hasErrors() == false)
            return null;

        Map<String,String> fieldErrors = new HashMap<String, String>();

        for(FieldError error : bindingResult.getFieldErrors()){
            fieldErrors.put(error.getField(), error.getCode() + "|" + error.getDefaultMessage());
        }

        Result ret = new Result(422, "输入错误"); //rfc4918 - 11.2. 422: Unprocessable Entity
        ret.putData("fieldErrors", fieldErrors);

        return ret;
    }

    public static String toHexString(byte[] array) {
        return DatatypeConverter.printHexBinary(array);
    }

    public static byte[] toByteArray(String s) {
        return DatatypeConverter.parseHexBinary(s);
    }
}
