package com.linLing.project.controller.xm;

import com.linLing.project.dao.DataBase;
import com.linLing.project.dao.sys.SysUsersDao;
import com.linLing.project.dao.xm.XmProjectDao;
import com.linLing.project.dao.xm.XmProjectExamineDao;
import com.linLing.project.po.*;
import com.linLing.project.utils.CommonUtil;
import com.linLing.project.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/xmProject")
@Transactional
public class XmProjectController extends SessionUtil {
    /**
     * 返回结果
     */
    private ResponseResult result = new ResponseResult();

    @Autowired
    private XmProjectDao dao;

    @Autowired
    private DataBase dataBase;

    @Autowired
    private SysUsersDao sysUsersDao;

    @Autowired
    private XmProjectExamineDao xmProjectExamineDao;

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
     * projectCode验证
     */
    @RequestMapping(value = "/yzProjectCode/{projectCode}", method = RequestMethod.GET)
    public ResponseResult yzProjectCode(@PathVariable String projectCode) {
        try {
            if(dao.yzProjectCode(projectCode)>0){
                result = CommonUtil.setResult("1","存在重复code", null);
            }else{
                result = CommonUtil.setResult("0","验证通过", null);
            }
        } catch (Exception ex) {
            result = CommonUtil.setResult("1", ex.getMessage(), null);
        }
        return result;
    }

    /**
     * projectCode验证
     */
    @RequestMapping(value = "/yzProjectCode1/{projectCode}/{roleId}", method = RequestMethod.GET)
    public ResponseResult yzProjectCode(@PathVariable String projectCode,@PathVariable int id) {
        try {
            if(dao.yzProjectCode1(projectCode,id)>0){
                result = CommonUtil.setResult("1","存在重复code", null);
            }else{
                result = CommonUtil.setResult("0","验证通过", null);
            }
        } catch (Exception ex) {
            result = CommonUtil.setResult("1", ex.getMessage(), null);
        }
        return result;
    }

    /**
     * 按项目所属领域分类
     */
    @RequestMapping(value = "/fieldClassification", method = RequestMethod.GET)
    public ResponseResult fieldClassification() {
        try {
            result = CommonUtil.setResult("0", "查询成功", dao.findByProjectField());
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
            XmProject xmProject = dao.findById(id).get();
            if ("0".equals(xmProject.getProjectState())){
                xmProject.setProjectState("1");
            }else{
                xmProject.setProjectState("0");
            }
            dao.save(xmProject);
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
            String sqlStr ="SELECT x.*,d.dic_name FROM xm_project x left join  sys_dictionary d on d.id = x.project_field where  1=1 ";
            List<Object> list = new ArrayList<>();
            //查询
            //当前登录人
            if (!"".equals(pageParameter.getParameters().get("dqUser")) && null != pageParameter.getParameters().get("dqUser")) {
                sqlStr += "\tAND (x.project_student_id = "+getSysUsers().getUserId()+" or x.project_teacher_id = "+getSysUsers().getUserId()+")\n";
            }
            //状态
            if (!"".equals(pageParameter.getParameters().get("projectState")) && null != pageParameter.getParameters().get("projectState")) {
                sqlStr += "\tAND x.project_state LIKE ?\n";
                list.add("%" + pageParameter.getParameters().get("projectState") + "%");
            }
            //code
            if (!"".equals(pageParameter.getParameters().get("projectCode")) && null != pageParameter.getParameters().get("projectCode")) {
                sqlStr += "\tAND x.project_code LIKE ?\n";
                list.add("%" + pageParameter.getParameters().get("projectCode") + "%");
            }
            //项目名称
            if (!"".equals(pageParameter.getParameters().get("projectName")) && null != pageParameter.getParameters().get("projectName")) {
                sqlStr += "\tAND x.project_name LIKE ?\n";
                list.add("%" + pageParameter.getParameters().get("projectName") + "%");
            }
            //年度
            if (!"".equals(pageParameter.getParameters().get("projectYear")) && null != pageParameter.getParameters().get("projectYear")) {
                sqlStr += "\tAND x.project_year = ?\n";
                list.add(pageParameter.getParameters().get("projectYear"));
            }
            //申报学生Id
            if (!"".equals(pageParameter.getParameters().get("projectStudentId")) && null != pageParameter.getParameters().get("projectStudentId")) {
                sqlStr += "\tAND x.project_student_id = ?\n";
                list.add(pageParameter.getParameters().get("projectStudentId"));
            }
            final Pages pages = dataBase.findSql(sqlStr, list.toArray(), pageParameter.getPage(), pageParameter.getSize(), pageParameter.getSort(), pageParameter.getDir());
            result = CommonUtil.setResult("0", "查询成功", pages);
        } catch (Exception ex) {
            result = CommonUtil.setResult("1", ex.getMessage(), null);
        }
        return result;
    }

    /**
     * 项目创建及修改
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseResult save(XmProject xmProject) {
        try {
            if (xmProject.getId() != null) {
                XmProject searchXmProject = dao.findById(xmProject.getId()).get();
                result = CommonUtil.setResult("0", "修改成功", dao.save(CommonUtil.mergeObject(xmProject, searchXmProject)));
            } else {

                xmProject.setProjectState("1");
                xmProject.setProjectStudentId(getSysUsers().getUserId());
                xmProject.setProjectStudentName(getSysUsers().getUserName());
                xmProject.setCreateDate(new Timestamp(System.currentTimeMillis()));
                XmProject data = dao.saveAndFlush(xmProject);
                result = CommonUtil.setResult("0", "保存成功",data);
            }
        } catch (Exception ex) {
            result = CommonUtil.setResult("1", ex.getMessage(), null);
        }
        return result;
    }
    /**
     * 项目分配
     */
    @RequestMapping(value = "/distribute", method = RequestMethod.POST)
    public ResponseResult distribute(XmProject xmProject,String userIds) {
        try {
            XmProject searchXmProject = dao.findById(xmProject.getId()).get();
            xmProject.setProjectState("2");
            dao.save(CommonUtil.mergeObject(xmProject, searchXmProject));
            String[] arr = userIds.split(",");
            List<XmProjectExamine> xmProjectExamineList =new ArrayList<>();
            for(String userId:arr){
                XmProjectExamine xmProjectExamine =new XmProjectExamine();
                xmProjectExamine.setProjectId(xmProject.getId());
                xmProjectExamine.setUserId(Integer.parseInt(userId));
                SysUsers sysUsers = sysUsersDao.findOneByUserId(Integer.parseInt(userId));
                xmProjectExamine.setUserName(sysUsers.getUserName());
                xmProjectExamine.setExamineState("0");
                xmProjectExamineList.add(xmProjectExamine);
            }
            xmProjectExamineDao.saveAll(xmProjectExamineList);
            result = CommonUtil.setResult("0", "分配成功",null);
        } catch (Exception ex) {
            result = CommonUtil.setResult("1", ex.getMessage(), null);
        }
        return result;
    }
    /**
     * 项目立项
     */
    @RequestMapping(value = "/approval", method = RequestMethod.POST)
    public ResponseResult approval(XmProject xmProject) {
        try {
            XmProject xmProject1 = dao.findById(xmProject.getId()).get();
            xmProject1.setProjectState("5");
            dao.save(xmProject1);
            result = CommonUtil.setResult("0", "立项成功",null);
        } catch (Exception ex) {
            result = CommonUtil.setResult("1", ex.getMessage(), null);
        }
        return result;
    }
    /**
     * 项目结束
     */
    @RequestMapping(value = "/end", method = RequestMethod.POST)
    public ResponseResult end(XmProject xmProject) {
        try {
            XmProject xmProject1 = dao.findById(xmProject.getId()).get();
            xmProject1.setProjectState("6");
            dao.save(xmProject1);
            result = CommonUtil.setResult("0", "项目结束",null);
        } catch (Exception ex) {
            result = CommonUtil.setResult("1", ex.getMessage(), null);
        }
        return result;
    }

    /**
     * 项目验收
     */
    @RequestMapping(value = "/conclud", method = RequestMethod.POST)
    public ResponseResult conclud(XmProject xmProject) {
        try {
            XmProject xmProject1 = dao.findById(xmProject.getId()).get();
            xmProject1.setProjectState("7");
            dao.save(xmProject1);
            result = CommonUtil.setResult("0", "验收成功",null);
        } catch (Exception ex) {
            result = CommonUtil.setResult("1", ex.getMessage(), null);
        }
        return result;
    }

}
