package com.linLing.project.utils;

import com.linLing.project.po.SysUsers;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;

/**
 * 用户信息
 */
@Slf4j
@Component
public class ShiroUtil {

    /**
     *
     * 获取当前登陆人信息
     */
    public static SysUsers getUserInfo() {
        Subject subject = SecurityUtils.getSubject();
        SysUsers sysUsers;
        try {
//            if (subject.getSession().getAttribute("user") != null) {}
                sysUsers = (SysUsers) subject.getSession().getAttribute("user");

        } catch (Exception ex) {
            sysUsers = null;
            log.error("Shiro异常！获取当前登录人信息异常！");
        }

        return sysUsers;
    }

}
