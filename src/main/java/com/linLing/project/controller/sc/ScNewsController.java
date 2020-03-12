package com.linLing.project.controller.sc;

import com.linLing.project.dao.DataBase;
import com.linLing.project.dao.sc.ScNewsDao;
import com.linLing.project.po.*;
import com.linLing.project.utils.CommonUtil;
import com.linLing.project.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/scNews")
@Transactional
public class ScNewsController extends SessionUtil {
    /**
     * 返回结果
     */
    private ResponseResult result = new ResponseResult();

    @Autowired
    private ScNewsDao dao;

    @Autowired
    private DataBase dataBase;

    /**
     * 获取单条数据
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseResult info(@PathVariable int id) {
        try {
            result = CommonUtil.setResult("0", "查询成功", dao.findById(id));
        } catch (Exception ex) {
            result = CommonUtil.setResult("1", ex.getMessage(), null);
        }
        return result;
    }

    /**
     * 信息（冻结/启用）
     */
    @RequestMapping(value = "/state/{id}", method = RequestMethod.GET)
    public ResponseResult delete(@PathVariable int id) {
        try {
            ScNews scNews = dao.findById(id).get();
            if ("0".equals(scNews.getNewsState())){
                scNews.setNewsState("1");
            }else{
                scNews.setNewsState("0");
            }
            dao.save(scNews);
            result = CommonUtil.setResult("0", "删除成功", "");
        } catch (Exception ex) {
            result = CommonUtil.setResult("1", ex.getMessage(), null);
        }
        return result;
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public ResponseResult page(@RequestBody PageParameter pageParameter) {
        try {
            //sql
            String sqlStr ="SELECT * FROM sc_news where news_state = 1";
            List<Object> list = new ArrayList<>();
            //查询
            //新闻名称
            if (!"".equals(pageParameter.getParameters().get("newsTitle")) && null != pageParameter.getParameters().get("newsTitle")) {
                sqlStr += "\tAND news_title LIKE ?\n";
                list.add("%" + pageParameter.getParameters().get("newsTitle") + "%");
            }
            final Pages pages = dataBase.findSql(sqlStr, list.toArray(), pageParameter.getPage(), pageParameter.getSize(), pageParameter.getSort(), pageParameter.getDir());
            result = CommonUtil.setResult("0", "查询成功", pages);
        } catch (Exception ex) {
            result = CommonUtil.setResult("1", ex.getMessage(), null);
        }
        return result;
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseResult save(ScNews scNews) {
        try {
            if (scNews.getId() != null) {
                ScNews searchScNews = dao.findById(scNews.getId()).get();
                result = CommonUtil.setResult("0", "修改成功", dao.save(CommonUtil.mergeObject(scNews, searchScNews)));
            } else {
                scNews.setNewsState("1");
                ScNews data = dao.saveAndFlush(scNews);
                result = CommonUtil.setResult("0", "保存成功",data);
            }
        } catch (Exception ex) {
            result = CommonUtil.setResult("1", ex.getMessage(), null);
        }
        return result;
    }

}