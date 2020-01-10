package com.linLing.project.utils;

import com.linLing.project.po.ResponseResult;

/**
 * 公用方法
 */
public class CommonUtil {
    public static ResponseResult setResult(String code, String msg, Object data) {
        ResponseResult result = new ResponseResult();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }
}
