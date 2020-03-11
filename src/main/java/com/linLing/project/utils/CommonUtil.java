package com.linLing.project.utils;

import com.linLing.project.po.ResponseResult;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;


/**
 * 公用方法
 */
public class CommonUtil {
    /**
     * logger
     */
    private static final Logger logger = Logger.getLogger(CommonUtil.class);

    public static ResponseResult setResult(String code, String msg, Object data) {
        ResponseResult result = new ResponseResult();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }
    /**
     * 下划线转驼峰
     *
     * @param str 需要转换的字符串
     * @return 转换完成的字符串
     */
    public static String lineToHump(String str) {
        // 正则
        Pattern linePattern = Pattern.compile("_(\\w)");
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 驼峰转下划线
     *
     * @param str 需要转换的字符串
     * @return 转换完成的字符串
     */
    public static String humpToLine(String str) {
        Pattern humpPattern = Pattern.compile("[A-Z]");
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 合并数据
     *
     * @param newOne 新数据
     * @param oldOne 旧数据
     * @param <T>    泛型
     * @return 合并后的oldOne
     */
    public static <T> T mergeObject(T newOne, T oldOne) {
        for (Field f : newOne.getClass().getDeclaredFields()
        ) {
            try {
                f.setAccessible(true);
                Object o = f.get(newOne);
                if (null != o) {
                    f.set(oldOne, o);
                }
            } catch (IllegalAccessException e) {
                logger.error(e.getMessage());
            }
        }
        return oldOne;
    }
}
