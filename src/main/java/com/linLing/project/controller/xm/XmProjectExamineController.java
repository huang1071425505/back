package com.linLing.project.controller.xm;

import com.linLing.project.dao.DataBase;
import com.linLing.project.dao.xm.XmProjectDao;
import com.linLing.project.dao.xm.XmProjectExamineDao;
import com.linLing.project.po.*;
import com.linLing.project.utils.CommonUtil;
import com.linLing.project.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/xmProjectExamine")
@Transactional
public class XmProjectExamineController extends SessionUtil {
    /**
     * 返回结果
     */
    private ResponseResult result = new ResponseResult();

    @Autowired
    private XmProjectExamineDao dao;

    @Autowired
    private DataBase dataBase;

    @Autowired
    private XmProjectDao xmProjectDao;

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
     * 获取列表
     */
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public ResponseResult page(@RequestBody PageParameter pageParameter) {
        try {
            //sql
            String sqlStr ="SELECT * FROM xm_project_examine  where 1 = 1";
            List<Object> list = new ArrayList<>();
            //查询
            //项目ID
            if (!"".equals(pageParameter.getParameters().get("projectId")) && null != pageParameter.getParameters().get("projectId")) {
                sqlStr += "\tAND project_id = ?\n";
                list.add(pageParameter.getParameters().get("projectId"));
            }
            //评审状态
            if (!"".equals(pageParameter.getParameters().get("examineState")) && null != pageParameter.getParameters().get("examineState")) {
                sqlStr += "\tAND examine_state = ?\n";
                list.add(pageParameter.getParameters().get("examineState"));
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
    public ResponseResult save(XmProjectExamine xmProjectExamine) {
        try {
            if (xmProjectExamine.getId() != null) {
                xmProjectExamine.setExamineState("1");
                XmProjectExamine searchXmProjectExamine = dao.findById(xmProjectExamine.getId()).get();
                XmProjectExamine xmProjectExamine1 = dao.save(CommonUtil.mergeObject(xmProjectExamine, searchXmProjectExamine));
                if(dao.findOneByProjectIdAndExamineState(xmProjectExamine.getProjectId())==0){
                    XmProject xmProject = xmProjectDao.findById(xmProjectExamine.getProjectId()).get();
                    xmProject.setProjectState("3");
                    xmProjectDao.save(xmProject);
                }
                result = CommonUtil.setResult("0", "修改成功",xmProjectExamine1);
            } else {
                XmProjectExamine data = dao.saveAndFlush(xmProjectExamine);
                result = CommonUtil.setResult("0", "保存成功",data);
            }
        } catch (Exception ex) {
            result = CommonUtil.setResult("1", ex.getMessage(), null);
        }
        return result;
    }
}