package com.example.minio.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AjaxResult {

    private String resultCode;

    private String msg;

    private Object obj;

    public static AjaxResult success() {
        AjaxResult result = new AjaxResult();
        result.setResultCode("0");
        result.setMsg("success");
        return  result;
    }

    public static AjaxResult success(Object obj) {
        AjaxResult result = new AjaxResult();
        result.setResultCode("0");
        result.setMsg("success");
        result.setObj(obj);
        return  result;
    }

    public static AjaxResult success(String resCode, Object obj) {
        AjaxResult result = new AjaxResult();
        result.setResultCode(resCode);
        result.setMsg("success");
        result.setObj(obj);
        return  result;
    }
}
