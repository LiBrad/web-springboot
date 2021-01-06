package com.brad.web.webspringboot.oaSystem.service.impl;

import com.brad.web.webspringboot.oaSystem.bean.SysDictionaryBean;
import com.brad.web.webspringboot.oaSystem.mapper.SysDictionaryMapper;
import com.brad.web.webspringboot.oaSystem.service.SysDicionaryService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class SysDicionaryServiceImpl implements SysDicionaryService {
    private final Logger logger = LoggerFactory.getLogger(SysDicionaryServiceImpl.class);

    @Autowired
    private SysDictionaryMapper sysDictionaryMapper;


    @Override
    public List<SysDictionaryBean> getSysDictionaryList(Map<String, Object> paramap, String pageNum, String pageSize) {
        logger.info("SysDicionaryServiceImpl{}==>getSysDictionaryList()");

        //分页
        int pageNumInt=0;
        int pageSizeInt=0;
        if (StringUtils.isNumeric(pageNum)) {
            pageNumInt = Integer.parseInt(pageNum);
        }
        if (StringUtils.isNumeric(pageSize)) {
            pageSizeInt = Integer.parseInt(pageSize);
        }

        return sysDictionaryMapper.getSysDictionaryList(paramap);
    }

    @Override
    public List<SysDictionaryBean> getSysDictionaryParentList(Map<String, Object> paramap, String pageNum, String pageSize) {
        logger.info("SysDicionaryServiceImpl{}==>getSysDictionaryList()");

        //分页
        int pageNumInt=0;
        int pageSizeInt=0;
        if (StringUtils.isNumeric(pageNum)) {
            pageNumInt = Integer.parseInt(pageNum);
        }
        if (StringUtils.isNumeric(pageSize)) {
            pageSizeInt = Integer.parseInt(pageSize);
        }

        return sysDictionaryMapper.getSysDictionaryParentList(paramap);
    }

    @Override
    public int getOptionParentCount(Map<String, Object> paramap) {
        logger.info("SysDicionaryServiceImpl{}==>getOptionParentCount()");
        return sysDictionaryMapper.getOptionParentCount(paramap);
    }
}
