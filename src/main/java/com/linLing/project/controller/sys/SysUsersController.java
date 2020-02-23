package com.linLing.project.controller.sys;


import com.linLing.project.dao.sys.SysUsersDao;
import com.linLing.project.po.PageParameter;
import com.linLing.project.po.ResponseResult;
import com.linLing.project.po.SysUsers;
import com.linLing.project.utils.CommonUtil;
import com.linLing.project.utils.CryptosUtil;
import com.linLing.project.utils.SessionUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
     * 获取用户列表
     */
    @RequestMapping(value = "/userList", method = RequestMethod.POST)
    public ResponseResult userList(@RequestBody PageParameter pageParameter) {
        try {
            result = CommonUtil.setResult("0", "查询成功", dao.userList(pageParameter));
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
