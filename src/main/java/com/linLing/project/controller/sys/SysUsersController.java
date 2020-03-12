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
     * userCode验证
     */
    @RequestMapping(value = "/yzUserCode/{userCode}", method = RequestMethod.GET)
    public ResponseResult save(@PathVariable String userCode) {
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
     * 用户信息（冻结/启用）
     */
    @RequestMapping(value = "/state/{id}", method = RequestMethod.GET)
    public ResponseResult delete(@PathVariable int id) {
        try {
            SysUsers sysUsers = dao.findById(id).get();
            if ("0".equals(sysUsers.getUserState())){
                sysUsers.setUserState("1");
            }else{
                sysUsers.setUserState("0");
            }
            dao.save(sysUsers);
            result = CommonUtil.setResult("0", "删除成功", "");
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
            String sqlStr ="SELECT * FROM sys_users where user_state = 1";
            List<Object> list = new ArrayList<>();
            //查询
            //用户名称
            if (!"".equals(pageParameter.getParameters().get("userName")) && null != pageParameter.getParameters().get("userName")) {
                sqlStr += "\tAND user_name LIKE ?\n";
                list.add("%" + pageParameter.getParameters().get("userName") + "%");
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
                sysUsers.setUserPassword("82ug05b311fs6kb56afa3vqsbr5tnfnf");
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
