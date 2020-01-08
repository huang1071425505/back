package com.linLing.project.utils;

import com.linLing.project.po.ResponseResult;

public class CommonUtil {
    public static ResponseResult setResult(String code, String msg, Object data) {
        ResponseResult result = new ResponseResult();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }
}
