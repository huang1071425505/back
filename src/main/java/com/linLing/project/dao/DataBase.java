package com.linLing.project.dao;

import com.linLing.project.po.Pages;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

public interface DataBase {


    public Pages findByPage(int page, int size, String sql, Object[] params);

    /**
     * 执行任意sql
     *
     * @param sql    sql语句
     * @param params 参数
     * @return 执行结果
     */
    public List<HashMap> runSql(String sql, Object[] params);

    /**
     * 分页查询
     *
     * @param sql    sql语句
     * @param params sql参数
     * @param page   页数
     * @param size   每页条数
     * @param sort   排序字段
     * @param dir    asc/desc升序降序
     * @return 分页数据
     */
    public Pages findSql(String sql, Object[] params, int page, int size, String sort, String dir);
}