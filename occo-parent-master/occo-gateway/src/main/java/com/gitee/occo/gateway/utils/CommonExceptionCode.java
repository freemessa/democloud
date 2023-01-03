package com.gitee.occo.gateway.utils;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CommonExceptionCode {
    private Integer code;
    private String msg;
    public static List<CommonExceptionCode> values(){
        return new ArrayList<>();
    }

}
