package com.linLing.project.controller.sc;

import com.linLing.project.dao.DataBase;
import com.linLing.project.dao.sc.ScPolicyDao;
import com.linLing.project.po.*;
import com.linLing.project.utils.CommonUtil;
import com.linLing.project.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/scPolicy")
@Transactional
public class ScPolicyController extends SessionUtil {
    /**
     * 返回结果
     */
    private ResponseResult result = new ResponseResult();

    @Autowired
    private ScPolicyDao dao;

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
            ScPolicy scPolicy = dao.findById(id).get();
            if ("0".equals(scPolicy.getPolicyState())){
                scPolicy.setPolicyState("1");
            }else{
                scPolicy.setPolicyState("0");
            }
            dao.save(scPolicy);
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
            String sqlStr ="SELECT * FROM sc_policy where policy_state = 1";
            List<Object> list = new ArrayList<>();
            //查询
            //政策名称
            if (!"".equals(pageParameter.getParameters().get("policyName")) && null != pageParameter.getParameters().get("policyName")) {
                sqlStr += "\tAND policy_name LIKE ?\n";
                list.add("%" + pageParameter.getParameters().get("policyName") + "%");
            }
            //发布部门
            if (!"".equals(pageParameter.getParameters().get("releaseBm")) && null != pageParameter.getParameters().get("releaseBm")) {
                sqlStr += "\tAND release_bm LIKE ?\n";
                list.add("%" + pageParameter.getParameters().get("releaseBm") + "%");
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
    public ResponseResult save(ScPolicy scPolicy) {
        try {
            if (scPolicy.getId() != null) {
                ScPolicy searchScPolicy = dao.findById(scPolicy.getId()).get();
                result = CommonUtil.setResult("0", "修改成功", dao.save(CommonUtil.mergeObject(scPolicy, searchScPolicy)));
            } else {
                scPolicy.setPolicyState("1");
                ScPolicy data = dao.saveAndFlush(scPolicy);
                result = CommonUtil.setResult("0", "保存成功",data);
            }
        } catch (Exception ex) {
            result = CommonUtil.setResult("1", ex.getMessage(), null);
        }
        return result;
    }
}