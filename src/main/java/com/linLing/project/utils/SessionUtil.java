package com.linLing.project.utils;

import com.linLing.project.po.SysUsers;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

public abstract class SessionUtil {

    /**
     * 当前账号常量
     */
    private static final String ACCOUNT = "sysUsers";


    private HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    public SysUsers getSysUsers() {
        HttpSession session = getRequest().getSession();
        return (SysUsers) session.getAttribute(ACCOUNT);
    }

    public void setSysUsers(SysUsers sysUsers) {
        HttpSession session = getRequest().getSession();
        if (sysUsers != null) {
            session.setAttribute(ACCOUNT, sysUsers);
            //session过期时间设置，以秒为单位，即在没有活动30分钟后，session将失效
            session.setMaxInactiveInterval(30 * 60);
        }
    }
}