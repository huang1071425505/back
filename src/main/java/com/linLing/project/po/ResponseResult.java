package com.linLing.project.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author cli
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResult {
    private String code;
    private String msg;
    private Object data;
    private Object pra;

}