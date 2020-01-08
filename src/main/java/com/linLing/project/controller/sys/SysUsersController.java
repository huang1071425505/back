package com.linLing.project.controller.sys;


import com.linLing.project.dao.sys.SysUsersDao;
import com.linLing.project.po.ResponseResult;
import com.linLing.project.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/SysUsers")
@Transactional
public class SysUsersController {
    /**
     * 返回结果
     */
    private ResponseResult result = new ResponseResult();

    @Autowired
    private SysUsersDao dao;


    @RequestMapping(value = "/myInfo", method = RequestMethod.GET)
    public ResponseResult info() {
        try {
            result = CommonUtil.setResult("0", "查询成功", dao.findAll());
        } catch (Exception ex) {
            result = CommonUtil.setResult("1", ex.getMessage(), null);
        }

        return result;
    }
}
