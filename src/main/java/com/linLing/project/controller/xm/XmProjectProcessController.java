package com.linLing.project.controller.xm;

import com.linLing.project.dao.DataBase;
import com.linLing.project.dao.xm.XmProjectProcessDao;
import com.linLing.project.po.*;
import com.linLing.project.utils.CommonUtil;
import com.linLing.project.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/xmProjectProcess")
@Transactional
public class XmProjectProcessController extends SessionUtil {
    /**
     * 返回结果
     */
    private ResponseResult result = new ResponseResult();

    @Autowired
    private XmProjectProcessDao dao;

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
            XmProjectProcess xmProjectProcess = dao.findById(id).get();
            if ("0".equals(xmProjectProcess.getRecordState())){
                xmProjectProcess.setRecordState("1");
            }else{
                xmProjectProcess.setRecordState("0");
            }
            dao.save(xmProjectProcess);
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
            String sqlStr ="SELECT * FROM xm_project_process where record_state = 1";
            List<Object> list = new ArrayList<>();
            //查询
            //项目ID
            if (!"".equals(pageParameter.getParameters().get("projectId")) && null != pageParameter.getParameters().get("projectId")) {
                sqlStr += "\tAND project_id = ?\n";
                list.add(pageParameter.getParameters().get("projectId"));
            }
            //记录类型
            if (!"".equals(pageParameter.getParameters().get("recordType")) && null != pageParameter.getParameters().get("recordType")) {
                sqlStr += "\tAND record_type = ?\n";
                list.add(pageParameter.getParameters().get("recordType"));
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
    public ResponseResult save(XmProjectProcess xmProjectProcess) {
        try {
            if (xmProjectProcess.getId() != null) {
                XmProjectProcess searchXmProjectProcess = dao.findById(xmProjectProcess.getId()).get();
                result = CommonUtil.setResult("0", "修改成功", dao.save(CommonUtil.mergeObject(xmProjectProcess, searchXmProjectProcess)));
            } else {
                xmProjectProcess.setRecordState("1");
                XmProjectProcess data = dao.saveAndFlush(xmProjectProcess);
                result = CommonUtil.setResult("0", "保存成功",data);
            }
        } catch (Exception ex) {
            result = CommonUtil.setResult("1", ex.getMessage(), null);
        }
        return result;
    }
}