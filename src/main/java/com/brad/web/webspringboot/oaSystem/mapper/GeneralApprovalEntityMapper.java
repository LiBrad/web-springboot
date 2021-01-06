package com.brad.web.webspringboot.oaSystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.brad.web.webspringboot.oaSystem.bean.GeneralApprovalBean;
import com.brad.web.webspringboot.oaSystem.entity.GeneralApprovalEntity;
import org.apache.ibatis.annotations.Param;

public interface GeneralApprovalEntityMapper extends BaseMapper<GeneralApprovalEntity> {



    /**
     * 查询通用审批列表
     *
     * @param page 分页对象
     * @param generalApprovalBean 对象，用于传递查询条件
     * @return Ipage
     */
    <T> IPage<GeneralApprovalBean> getGeneralApprovalEntityList(Page<T> page, @Param("generalApprovalBean")GeneralApprovalBean generalApprovalBean);

    long countGeneralApprovalEntityDetail(@Param("generalApprovalBean")GeneralApprovalBean generalApprovalBean);
}