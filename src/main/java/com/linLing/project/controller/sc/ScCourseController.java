package com.linLing.project.controller.sc;

import com.linLing.project.dao.DataBase;
import com.linLing.project.dao.sc.ScCourseDao;
import com.linLing.project.po.*;
import com.linLing.project.utils.CommonUtil;
import com.linLing.project.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/scCourse")
@Transactional
public class ScCourseController extends SessionUtil {
    /**
     * 返回结果
     */
    private ResponseResult result = new ResponseResult();

    @Autowired
    private ScCourseDao dao;

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
            ScCourse scCourse = dao.findById(id).get();
            if ("0".equals(scCourse.getCourseState())){
                scCourse.setCourseState("1");
            }else{
                scCourse.setCourseState("0");
            }
            dao.save(scCourse);
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
            String sqlStr ="SELECT * FROM sc_course where course_state = 1";
            List<Object> list = new ArrayList<>();
            //查询
            //课程Name
            if (!"".equals(pageParameter.getParameters().get("courseName")) && null != pageParameter.getParameters().get("courseName")) {
                sqlStr += "\tAND course_name LIKE ?\n";
                list.add("%" + pageParameter.getParameters().get("courseName") + "%");
            }
            //任课老师ID
            if (!"".equals(pageParameter.getParameters().get("userId")) && null != pageParameter.getParameters().get("userId")) {
                sqlStr += "\tAND user_id = ?\n";
                list.add(pageParameter.getParameters().get("userId"));
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
    public ResponseResult save(ScCourse scCourse) {
        try {
            if (scCourse.getId() != null) {
                ScCourse searchScCourse = dao.findById(scCourse.getId()).get();
                result = CommonUtil.setResult("0", "修改成功", dao.save(CommonUtil.mergeObject(scCourse, searchScCourse)));
            } else {
                scCourse.setCourseState("1");
                ScCourse data = dao.saveAndFlush(scCourse);
                result = CommonUtil.setResult("0", "保存成功",data);
            }
        } catch (Exception ex) {
            result = CommonUtil.setResult("1", ex.getMessage(), null);
        }
        return result;
    }
}