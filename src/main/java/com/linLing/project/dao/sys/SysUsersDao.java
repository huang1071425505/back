package com.linLing.project.dao.sys;

import com.cloud.base.dao.DBDao;
import com.cloud.base.dao.ParamsBuilder;
import com.linLing.project.po.PageParameter;
import com.linLing.project.po.SysUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.ArrayList;
import java.util.List;

public interface SysUsersDao extends JpaRepository<SysUsers, Integer>, JpaSpecificationExecutor<SysUsers>, SysUsersDaoDaoCustom {
    SysUsers findOneByUserCode(String userCode);
}

interface SysUsersDaoDaoCustom {
    /**
     * 用户兼职信息分页查询接口
     *
     * @param pageParameter 查询条件参数据及分页参数
     * @return
     */
    Page userList(PageParameter pageParameter);
}

class SysUsersDaoDaoImpl implements SysUsersDaoDaoCustom {
    @Autowired
    private DBDao dbDao;

    @Override
    public Page userList(PageParameter pageParameter) {
        //sql
        String sqlStr ="SELECT * FROM sys_users";
        List<ParamsBuilder> list = new ArrayList<>();
        //查询
        //状态
        list.add(new ParamsBuilder("where", "user_state", "=","1"));
        //用户名称
        if (!"".equals(pageParameter.getParameters().get("userName")) && null != pageParameter.getParameters().get("userName")) {
            list.add(new ParamsBuilder("and", "user_name", "=", pageParameter.getParameters().get("userName").toString()));
        }
        return dbDao.runPageSql(sqlStr, list, pageParameter.getPage(), pageParameter.getSize(), pageParameter.getDir().equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, pageParameter.getSort());
    }
}