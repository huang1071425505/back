package com.linLing.project.dao;

import com.linLing.project.po.Pages;
import org.apache.commons.lang.StringEscapeUtils;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;

import static com.linLing.project.utils.CommonUtil.humpToLine;
import static com.linLing.project.utils.CommonUtil.lineToHump;
@Service
@Transactional
public class DataBaseImpl implements DataBase {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * 遍历参数中的key,将其命名方式转为驼峰
     *
     * @param queryResultList 待转换的HashMap
     * @return 转换完成的HashMap
     */
    public List<HashMap> hashMapLine2Hump(List<HashMap> queryResultList) {
        List<HashMap> result = new ArrayList<>();
        for (HashMap hashMap : queryResultList) {
            Iterator it = hashMap.keySet().iterator();
            LinkedHashMap map = new LinkedHashMap();
            while (it.hasNext()) {
                String key = (String) it.next();
                map.put(lineToHump(key), hashMap.get(key));
            }
            result.add(map);
        }
        return result;
    }

    /**
     * 分页查询数据库
     * @param page 当前页
     * @param size 每页显示个数
     * @param sql 执行的sql语句
     * @param params sql 语句参数
     * @return 分页对象
     */
    public Pages findByPage(int page, int size, String sql, Object[] params){

        Query query = this.entityManager.createNativeQuery(sql);
        if(page>0 && size>0) {
            query.setFirstResult((page - 1) * size);
            query.setMaxResults(size);
        }
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        if(params!=null) {
            for (int i = 0; i < params.length; i++) {
                query.setParameter(i + 1, params[i]);
            }
        }

        Query countQuery = this.entityManager.createNativeQuery("SELECT COUNT(*) FROM (" + sql + ") auto_counts_query");
        if(params!=null) {
            for (int i = 0; i < params.length; i++) {
                countQuery.setParameter(i + 1, params[i]);
            }
        }

        List<Map<String,Object>> datas = query.getResultList();
        return new Pages(page, size, Long.valueOf(countQuery.getSingleResult().toString()), datas);
    }

    /**
     * sql查询
     *
     * @param sql    sql语句
     * @param params 参数列表
     * @return 查询后的结果
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<HashMap> runSql(String sql, Object[] params) {

        // 查询
        Query query = this.entityManager.createNativeQuery(sql);
        // 添加条件
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i + 1, humpToLine(params[i].toString()));
        }
        // 统计总数
        Query countQuery = this.entityManager.createNativeQuery("SELECT COUNT(*) FROM (" + sql + ") QWSdsa_1232");
        // 添加条件
        for (int i = 0; i < params.length; i++) {
            countQuery.setParameter(i + 1, humpToLine(params[i].toString()));
        }
        // 遍历，将命名修改为驼峰命名法
        List<HashMap> queryResultList = query.getResultList();
        return hashMapLine2Hump(queryResultList);
    }

    /**
     * 封装的sql查询
     *
     * @param sql    sql语句
     * @param params 参数列表
     * @param page   页数
     * @param size   每页条数
     * @param sort   排序字段
     * @param dir   asc/desc升序降序
     * @return 查询后的结果
     */
    @SuppressWarnings("unchecked")
    @Override
    public Pages findSql(String sql, Object[] params, int page, int size, String sort, String dir) {

        if (sort != null && !"".equals(sort) && dir != null && !"".equals(dir)) {
            sql += "\tORDER BY " + StringEscapeUtils.escapeHtml(humpToLine(sort));
            if (dir.toLowerCase().equals("asc")) {
                sql += " asc\n";
            } else {
                sql += " desc\n";
            }
        }
        // 查询
        Query query = this.entityManager.createNativeQuery(sql);
        // 添加条件
        query.setFirstResult((page - 1) * size);
        query.setMaxResults(size);
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i + 1,params[i].toString());
        }
        // 统计总数
        Query countQuery = this.entityManager.createNativeQuery("SELECT COUNT(*) FROM (" + sql + ") QWSdsa_1232");
        // 添加条件
        for (int i = 0; i < params.length; i++) {
            countQuery.setParameter(i + 1, humpToLine(params[i].toString()));
        }
        // 遍历，将命名修改为驼峰命名法
        List<HashMap> queryResultList = query.getResultList();
        List<HashMap> resultList = hashMapLine2Hump(queryResultList);
        // 返回
        return new Pages(page, size, Long.valueOf(countQuery.getSingleResult().toString()), resultList);
    }

}
