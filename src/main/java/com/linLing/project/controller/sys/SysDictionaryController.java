package com.linLing.project.controller.sys;

import com.linLing.project.dao.sys.SysDictionaryDao;
import com.linLing.project.po.ResponseResult;
import com.linLing.project.po.SysDictionary;
import com.linLing.project.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sysdictionary")
@Transactional
public class SysDictionaryController {
    /**
     * 返回结果
     */
    private ResponseResult result = new ResponseResult();

    @Autowired
    private SysDictionaryDao dao;

    /**
     * 获取整体字典表
     */
    @RequestMapping(value = "/dictionaryList", method = RequestMethod.GET)
    public ResponseResult dictionaryList() {
        try {
            result = CommonUtil.setResult("0", "查询成功", dao.findAllByDicState("1"));
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
            SysDictionary sysDictionary = dao.findById(id).get();
            if ("0".equals(sysDictionary.getDicState())){
                sysDictionary.setDicState("1");
            }else{
                sysDictionary.setDicState("0");
            }
            dao.save(sysDictionary);
            result = CommonUtil.setResult("0", "删除成功", "");
        } catch (Exception ex) {
            result = CommonUtil.setResult("1", ex.getMessage(), null);
        }
        return result;
    }

    /**
     * 通过dicGroup分组查询
     */
    @RequestMapping(value = "/groupQuery/{dicGroup}", method = RequestMethod.GET)
    public ResponseResult groupQuery(@PathVariable String dicGroup) {
        try {
            result = CommonUtil.setResult("0", "查询成功", dao.findAllByDicGroup(dicGroup));
        } catch (Exception ex) {
            result = CommonUtil.setResult("1", ex.getMessage(), null);
        }
        return result;
    }


    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseResult save(SysDictionary sysDictionary) {
        try {
            if (sysDictionary.getId() != null) {
                SysDictionary searchSysRole = dao.findById(sysDictionary.getId()).get();
                result = CommonUtil.setResult("0", "修改成功", dao.save(CommonUtil.mergeObject(sysDictionary, searchSysRole)));
            } else {
                sysDictionary.setDicState("1");
                SysDictionary data = dao.saveAndFlush(sysDictionary);
                result = CommonUtil.setResult("0", "保存成功",data);
            }
        } catch (Exception ex) {
            result = CommonUtil.setResult("1", ex.getMessage(), null);
        }
        return result;
    }

}
