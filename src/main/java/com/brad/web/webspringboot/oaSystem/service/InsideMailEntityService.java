package com.brad.web.webspringboot.oaSystem.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.brad.web.webspringboot.common.entity.QueryRequest;
import com.brad.web.webspringboot.oaSystem.bean.InsideMailBean;
import com.brad.web.webspringboot.oaSystem.entity.InsideMailEntity;

import java.util.List;

/**
 * @version 1.0
 * @author： 李逸野
 * @date： 2020-11-13
 */
public interface InsideMailEntityService {

    /**
     * 查询内部邮件列表
     * @return
     */
    IPage<InsideMailBean> getInsideMailEntityList(InsideMailBean insideMailBean, QueryRequest request);

    List<InsideMailBean> getInsideMailEntityByList(InsideMailBean insideMailBean);

    void deleteInsideMailEntity(String[] Ids);

    void createInsideMailEntity(InsideMailBean insideMailBean);

    void updateInsideMailEntity(InsideMailEntity entity);
}
