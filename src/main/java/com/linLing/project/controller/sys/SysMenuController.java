package com.linLing.project.controller.sys;

import com.linLing.project.dao.sys.SysMenuDao;
import com.linLing.project.dao.sys.SysRolePermissionDao;
import com.linLing.project.po.ResponseResult;
import com.linLing.project.po.SysMenu;
import com.linLing.project.po.SysRolePermission;
import com.linLing.project.utils.CommonUtil;
import com.linLing.project.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sysMenu")
@Transactional
public class SysMenuController extends SessionUtil {
    /**
     * 返回结果
     */
    private ResponseResult result = new ResponseResult();

    @Autowired
    private SysMenuDao dao;
    @Autowired
    private SysRolePermissionDao sysRolePermissionDao;

    /**
     * 获取整体菜单
     */
    @RequestMapping(value = "/menuList", method = RequestMethod.GET)
    public ResponseResult menuList() {
        try {
            result = CommonUtil.setResult("0", "查询成功", dao.findByMenuState("1"));
        } catch (Exception ex) {
            result = CommonUtil.setResult("1", ex.getMessage(), null);
        }
        return result;
    }
    /**
     * 当前菜单的角色
     */
    @RequestMapping(value = "/nowMenuRole/{menuId}", method = RequestMethod.GET)
    public ResponseResult nowMenuRole(@PathVariable int menuId) {
        try {
            result = CommonUtil.setResult("0", "状态更新成功", sysRolePermissionDao.findByPowerIdAndPowerType(menuId,"menu"));
        } catch (Exception ex) {
            result = CommonUtil.setResult("1", ex.getMessage(), null);
        }
        return result;
    }

        /**
         * 菜单权限付与
         */
    @RequestMapping(value = "/menuLimits/{roleIds}/{menuId}", method = RequestMethod.GET)
    public ResponseResult delete(@PathVariable String roleIds, @PathVariable int menuId) {
        try {
            sysRolePermissionDao.deleteByMenuId(menuId);
            String[] arr = roleIds.split(",");
            List<SysRolePermission> sysRolePermissionList =new ArrayList<>();
            for(String roleId:arr) {
                SysRolePermission sysRolePermission = new SysRolePermission();
                sysRolePermission.setPowerId(menuId);
                sysRolePermission.setPowerType("menu");
                sysRolePermission.setRoleId(Integer.parseInt(roleId));
                sysRolePermissionList.add(sysRolePermission);
            }
            sysRolePermissionDao.saveAll(sysRolePermissionList);
            result = CommonUtil.setResult("0", "状态更新成功", "");
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
            SysMenu sysMenu = dao.findById(id).get();
            if ("0".equals(sysMenu.getMenuState())){
                sysMenu.setMenuState("1");
            }else{
                sysMenu.setMenuState("0");
            }
            dao.save(sysMenu);
            result = CommonUtil.setResult("0", "删除成功", "");
        } catch (Exception ex) {
            result = CommonUtil.setResult("1", ex.getMessage(), null);
        }
        return result;
    }

    /**
     * 当前登录人菜单权限
     */
    @RequestMapping(value = "/menuPermissions", method = RequestMethod.GET)
    public ResponseResult menuPermissions() {
        try {
            result = CommonUtil.setResult("0", "获取成功", dao.menuPermissions(getSysUsers().getUserId()));
        } catch (Exception ex) {
            result = CommonUtil.setResult("1", ex.getMessage(), null);
        }
        return result;
    }
    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseResult save(SysMenu sysMenu) {
        try {
            if (sysMenu.getId() != null) {
                SysMenu searchSysRole = dao.findById(sysMenu.getId()).get();
                result = CommonUtil.setResult("0", "修改成功", dao.save(CommonUtil.mergeObject(sysMenu, searchSysRole)));
            } else {
                sysMenu.setMenuState("1");
                SysMenu data = dao.saveAndFlush(sysMenu);
                result = CommonUtil.setResult("0", "保存成功",data);
            }
        } catch (Exception ex) {
            result = CommonUtil.setResult("1", ex.getMessage(), null);
        }
        return result;
    }
}
