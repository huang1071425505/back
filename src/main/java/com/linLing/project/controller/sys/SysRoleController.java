package com.linLing.project.controller.sys;

import com.linLing.project.dao.DataBase;
import com.linLing.project.dao.sys.SysRoleDao;
import com.linLing.project.po.*;
import com.linLing.project.utils.CommonUtil;
import com.linLing.project.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/sysRole")
@Transactional
public class SysRoleController extends SessionUtil {
    /**
     * 返回结果
     */
    private ResponseResult result = new ResponseResult();

    @Autowired
    private SysRoleDao dao;

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
     * 获取有效数据
     */
    @RequestMapping(value = "/effective", method = RequestMethod.GET)
    public ResponseResult effective() {
        try {
            result = CommonUtil.setResult("0", "查询成功", dao.findByRoleState("1"));
        } catch (Exception ex) {
            result = CommonUtil.setResult("1", ex.getMessage(), null);
        }
        return result;
    }

    /**
     * roleCode验证
     */
    @RequestMapping(value = "/yzRoleCode/{roleCode}", method = RequestMethod.GET)
    public ResponseResult yzRoleCode(@PathVariable String roleCode) {
        try {
            if(dao.yzroleCode(roleCode)>0){
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
     * roleCode验证
     */
    @RequestMapping(value = "/yzRoleCode1/{roleCode}/{roleId}", method = RequestMethod.GET)
    public ResponseResult yzRoleCode(@PathVariable String roleCode,@PathVariable int roleId) {
        try {
            if(dao.yzroleCode1(roleCode,roleId)>0){
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
     * 信息（冻结/启用）
     */
    @RequestMapping(value = "/state/{id}", method = RequestMethod.GET)
    public ResponseResult delete(@PathVariable int id) {
        try {
            SysRole sysRole = dao.findById(id).get();
            if ("0".equals(sysRole.getRoleState())){
                sysRole.setRoleState("1");
            }else{
                sysRole.setRoleState("0");
            }
            dao.save(sysRole);
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
            String sqlStr ="SELECT * FROM sys_role where role_state = 1";
            List<Object> list = new ArrayList<>();
            //查询
            //code
            if (!"".equals(pageParameter.getParameters().get("roleCode")) && null != pageParameter.getParameters().get("roleCode")) {
                sqlStr += "\tAND role_code LIKE ?\n";
                list.add("%" + pageParameter.getParameters().get("roleCode") + "%");
            }
            //角色名
            if (!"".equals(pageParameter.getParameters().get("roleName")) && null != pageParameter.getParameters().get("roleName")) {
                sqlStr += "\tAND role_name LIKE ?\n";
                list.add("%" + pageParameter.getParameters().get("roleName") + "%");
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
    public ResponseResult save(SysRole sysRole) {
        try {
            if (sysRole.getRoleId() != null) {
                SysRole searchSysRole = dao.findById(sysRole.getRoleId()).get();
                result = CommonUtil.setResult("0", "修改成功", dao.save(CommonUtil.mergeObject(sysRole, searchSysRole)));
            } else {
                sysRole.setRoleState("1");
                SysRole data = dao.saveAndFlush(sysRole);
                result = CommonUtil.setResult("0", "保存成功",data);
            }
        } catch (Exception ex) {
            result = CommonUtil.setResult("1", ex.getMessage(), null);
        }
        return result;
    }


}
