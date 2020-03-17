package com.linLing.project.controller.sys;


import com.linLing.project.dao.DataBase;
import com.linLing.project.dao.sys.SysUsersDao;
import com.linLing.project.po.PageParameter;
import com.linLing.project.po.Pages;
import com.linLing.project.po.ResponseResult;
import com.linLing.project.po.SysUsers;
import com.linLing.project.utils.CommonUtil;
import com.linLing.project.utils.CryptosUtil;
import com.linLing.project.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sysUsers")
@Transactional
public class SysUsersController extends SessionUtil {
    /**
     * 返回结果
     */
    private ResponseResult result = new ResponseResult();

    @Autowired
    private SysUsersDao dao;

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
     * 获取本人信息
     */
    @RequestMapping(value = "/myInfo", method = RequestMethod.GET)
    public ResponseResult info() {
        try {
            int userId= getSysUsers().getUserId();
            result = CommonUtil.setResult("0", "查询成功", dao.findById(userId));
        } catch (Exception ex) {
            result = CommonUtil.setResult("1", ex.getMessage(), null);
        }
        return result;
    }

    /**
     * 获取教师
     */
    @RequestMapping(value = "/teacherList", method = RequestMethod.GET)
    public ResponseResult teacherList() {
        try {
            result = CommonUtil.setResult("0", "查询成功", dao.findByUserStateAndAndUserRoleId("1",2));
        } catch (Exception ex) {
            result = CommonUtil.setResult("1", ex.getMessage(), null);
        }
        return result;
    }

    /**
     * userCode验证
     */
    @RequestMapping(value = "/yzUserCode/{userCode}", method = RequestMethod.GET)
    public ResponseResult yzUserCode(@PathVariable String userCode) {
        try {
            if(dao.yzuserCode(userCode)>0){
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
    @RequestMapping(value = "/yzRoleCode1/{userCode}/{userId}", method = RequestMethod.GET)
    public ResponseResult yzRoleCode(@PathVariable String userCode,@PathVariable int userId) {
        try {
            if(dao.yzuserCode1(userCode,userId)>0){
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
     * 用户信息（冻结/启用）
     */
    @RequestMapping(value = "/state/{ids}/{userState}", method = RequestMethod.GET)
    public ResponseResult delete(@PathVariable String ids,@PathVariable String userState) {
        try {
            String[] arr = ids.split(",");
            List<SysUsers> sysUsersList =new ArrayList<>();
            for(String userId:arr) {
                SysUsers sysUsers = dao.findById(Integer.parseInt(userId)).get();
                sysUsers.setUserState(userState);
                sysUsersList.add(sysUsers);
            }
            dao.saveAll(sysUsersList);
            result = CommonUtil.setResult("0", "状态更新成功", "");
        } catch (Exception ex) {
            result = CommonUtil.setResult("1", ex.getMessage(), null);
        }
        return result;
    }


    /**
     * 获取用户列表
     */
    @RequestMapping(value = "/userList", method = RequestMethod.POST)
    public ResponseResult userList(@RequestBody PageParameter pageParameter) {
        try {
            //sql
            String sqlStr ="SELECT u.*,r.role_name FROM sys_users u left join sys_role r on r.role_id=u.user_role_id where 1 = 1";
            List<Object> list = new ArrayList<>();
            //查询
            //用户名称
            if (!"".equals(pageParameter.getParameters().get("userName")) && null != pageParameter.getParameters().get("userName")) {
                sqlStr += "\tAND u.user_name LIKE ?\n";
                list.add("%" + pageParameter.getParameters().get("userName") + "%");
            }
            //模糊查询
            if (!"".equals(pageParameter.getParameters().get("word")) && null != pageParameter.getParameters().get("word")) {
                sqlStr += "\tAND (u.user_name LIKE ? or u.user_code LIKE ? ) \n";
                list.add("%" + pageParameter.getParameters().get("word") + "%");
                list.add("%" + pageParameter.getParameters().get("word") + "%");
            }
            //状态查询
            if (!"".equals(pageParameter.getParameters().get("userState")) && null != pageParameter.getParameters().get("userState")) {
                sqlStr += "\tAND u.user_state = ?\n";
                list.add(pageParameter.getParameters().get("userState"));
            }
            final Pages pages = dataBase.findSql(sqlStr, list.toArray(), pageParameter.getPage(), pageParameter.getSize(), pageParameter.getSort(), pageParameter.getDir());
            result = CommonUtil.setResult("0", "查询成功", pages);
        } catch (Exception ex) {
            result = CommonUtil.setResult("1", ex.getMessage(), null);
        }
        return result;
    }

    /**
     * 用户信息保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseResult save(SysUsers sysUsers) {
        try {
            if (sysUsers.getUserId() != null) {
                SysUsers searchSysUsers = dao.findById(sysUsers.getUserId()).get();
                result = CommonUtil.setResult("0", "修改成功", dao.save(CommonUtil.mergeObject(sysUsers, searchSysUsers)));
            } else {
                sysUsers.setUserState("1");
                sysUsers.setUserPassword("7l7ins3to6v3hcgcqri6iid10sfpq3ht");
                SysUsers data = dao.saveAndFlush(sysUsers);
                result = CommonUtil.setResult("0", "保存成功",data);
            }
        } catch (Exception ex) {
            result = CommonUtil.setResult("1", ex.getMessage(), null);
        }
        return result;
    }

    /**
     * 用户登录
     * userCode 用户名
     * password 密码
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseResult login(String userCode, String password) {
        try {
            SysUsers sysUsers=dao.findOneByUserCode(userCode);
            if(sysUsers!=null){
                if(sysUsers.getUserPassword().equals(CryptosUtil.sha(password))){
                    if("1".equals(sysUsers.getUserState())){
//                        SecurityUtils.getSubject().getSession().setAttribute("user",sysUsers);
                        setSysUsers(sysUsers);
                        //创建jwt令牌
                        String jwt = CryptosUtil.jwt(userCode);
                        result = CommonUtil.setResult("0", jwt , sysUsers);
                    }else{
                        result = CommonUtil.setResult("1", "该用户已被冻结",null );
                    }

                }else{
                    result = CommonUtil.setResult("1", "密码错误",null );
                }
            }else{
                result = CommonUtil.setResult("1", "该用户不存在",null );
            }
        } catch (Exception ex) {
            result = CommonUtil.setResult("1", ex.getMessage(), null);
        }

        return result;
    }
    /**
     * 密码修改
     */
    @RequestMapping(value = "/exitPassword", method = RequestMethod.POST)
    public ResponseResult exitPassword(String yPassword,String xPassword) {
        try {
            int userId= getSysUsers().getUserId();
            SysUsers sysUsers=dao.findById(userId).get();
            if(sysUsers.getUserPassword().equals(CryptosUtil.sha(yPassword))){
                sysUsers.setUserPassword(CryptosUtil.sha(xPassword));
                dao.save(sysUsers);
                result = CommonUtil.setResult("0", "修改成功","");
            }else{
                result = CommonUtil.setResult("1", "原密码错误","");
            }
        } catch (Exception ex) {
            result = CommonUtil.setResult("1", ex.getMessage(), null);
        }
        return result;
    }

    /**
     * 用户登出
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ResponseResult logout() {
        try {
            removeSysUsers();
            result = CommonUtil.setResult("0", "登出成功","");
        } catch (Exception ex) {
            result = CommonUtil.setResult("1", ex.getMessage(), null);
        }

        return result;
    }
}
