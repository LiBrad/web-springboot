package com.brad.web.webspringboot.oaSystem.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.brad.web.webspringboot.common.entity.QueryRequest;
import com.brad.web.webspringboot.oaSystem.bean.GeneralApprovalBean;
import com.brad.web.webspringboot.oaSystem.entity.GeneralApprovalEntity;

import java.util.List;

/**
 * @version 1.0
 * @author： 李逸野
 * @date： 2020-11-13
 */
public interface GeneralApprovalEntityService {

    /**
     * 查询通用审批列表
     * @return
     */
    IPage<GeneralApprovalBean> getGeneralApprovalEntityList(GeneralApprovalBean generalApprovalBean, QueryRequest request);

    List<GeneralApprovalBean> getGeneralApprovalEntityByList(GeneralApprovalBean generalApprovalBean);

    void deleteGeneralApprovalEntity(String[] Ids);

    void createGeneralApprovalEntity(GeneralApprovalEntity entity);

    void updateGeneralApprovalEntity(GeneralApprovalEntity entity);
}
