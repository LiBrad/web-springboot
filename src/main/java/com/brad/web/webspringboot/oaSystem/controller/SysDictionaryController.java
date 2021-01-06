package com.brad.web.webspringboot.oaSystem.controller;

import com.brad.web.webspringboot.common.sourceDataBean.DataSource;
import com.brad.web.webspringboot.common.sourceDataBean.RestCode;
import com.brad.web.webspringboot.common.sourceDataBean.SourceDataBean;
import com.brad.web.webspringboot.oaSystem.bean.SysDictionaryBean;
import com.brad.web.webspringboot.oaSystem.service.SysDicionaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 字典表Controller
 * @author liyiye
 *
 */
@RestController
@RequestMapping("/sysDictionaryController")
public class SysDictionaryController {
    private final Logger logger = LoggerFactory.getLogger(SysDictionaryController.class);

    @Autowired
    private SysDicionaryService sysDicionaryService;

    @RequestMapping(value = "/getSysDictionaryList", method = RequestMethod.GET)
    public SourceDataBean<SysDictionaryBean> getSysDictionaryList(
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "page", required = false) Integer pageNum,
            @RequestParam(value = "limit", required = false) Integer pageSize
    ) {
        logger.info("SysDictionaryController==>getSysDictionaryList()");
        SourceDataBean<SysDictionaryBean> sdb = new SourceDataBean<SysDictionaryBean>();
        Map<String,Object> paramap=new HashMap<String,Object>();
        paramap.put("type", type);
        List<SysDictionaryBean> res = sysDicionaryService.getSysDictionaryList(paramap, "0", "0");
        if(res != null && res.size() > 0){
            sdb.setDataSource(new DataSource<SysDictionaryBean>(res,res.size(),0,0));
            sdb.setMessage(RestCode.SUCCESS.code,"ok");
        }else{
            sdb.setMessage(RestCode.NOT_FOUNT.code, "没有数据");
        }
        return sdb;
    }


    @RequestMapping(value = "/getSysDictionaryParentList", method = RequestMethod.GET)
    public SourceDataBean<SysDictionaryBean> getSysDictionaryParentList(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "page", required = false) Integer pageNum,
            @RequestParam(value = "limit", required = false) Integer pageSize
    ) {
        logger.info("SysDictionaryController==>getSysDictionaryParentList()");
        SourceDataBean<SysDictionaryBean> sdb = new SourceDataBean<SysDictionaryBean>();
        Map<String,Object> paramap=new HashMap<String,Object>();
        paramap.put("name", name);
        paramap.put("id", id);
        paramap.put("type", type);
        List<SysDictionaryBean> res = sysDicionaryService.getSysDictionaryParentList(paramap, "0", "0");
        if(res != null && res.size() > 0){
            int total = sysDicionaryService.getOptionParentCount(paramap);
            sdb.setDataSource(new DataSource<SysDictionaryBean>(res,total,0,0));
            sdb.setMessage(RestCode.SUCCESS.code,"ok");
        }else{
            sdb.setMessage(RestCode.NOT_FOUNT.code, "没有数据");
        }
        return sdb;
    }

}
