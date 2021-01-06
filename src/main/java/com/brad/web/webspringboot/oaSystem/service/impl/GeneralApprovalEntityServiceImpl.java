package com.brad.web.webspringboot.oaSystem.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.brad.web.webspringboot.common.entity.FebsConstant;
import com.brad.web.webspringboot.common.entity.QueryRequest;
import com.brad.web.webspringboot.common.utils.GenerationUUID;
import com.brad.web.webspringboot.common.utils.SortUtil;
import com.brad.web.webspringboot.common.utils.TimeUtils;
import com.brad.web.webspringboot.oaSystem.bean.GeneralApprovalBean;
import com.brad.web.webspringboot.oaSystem.bean.InsideMailBean;
import com.brad.web.webspringboot.oaSystem.entity.GeneralApprovalEntity;
import com.brad.web.webspringboot.oaSystem.mapper.GeneralApprovalEntityMapper;
import com.brad.web.webspringboot.oaSystem.service.GeneralApprovalEntityService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * @version 1.0
 * @author： 李逸野
 * @date： 2020-11-13
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class GeneralApprovalEntityServiceImpl extends ServiceImpl<GeneralApprovalEntityMapper, GeneralApprovalEntity> implements GeneralApprovalEntityService {
    private final Logger logger = LoggerFactory.getLogger(GeneralApprovalEntityServiceImpl.class);


    @Override
    public IPage<GeneralApprovalBean> getGeneralApprovalEntityList(GeneralApprovalBean generalApprovalBean, QueryRequest request) {
        Page<InsideMailBean> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countGeneralApprovalEntityDetail(generalApprovalBean));

        SortUtil.handlePageSort(request, page, "createTime", FebsConstant.ORDER_DESC, false);// 处理排序（分页情况下）

        return this.baseMapper.getGeneralApprovalEntityList(page, generalApprovalBean);
    }

    @Override
    public List<GeneralApprovalBean> getGeneralApprovalEntityByList(GeneralApprovalBean generalApprovalBean) {
        return this.getGeneralApprovalEntityList(generalApprovalBean,new QueryRequest()).getRecords();
    }

    @Override
    public void deleteGeneralApprovalEntity(String[] Ids) {
        List<String> list = Arrays.asList(Ids);
        // 删除通用审批申请
        this.removeByIds(list);
    }

    @Override
    public void createGeneralApprovalEntity(GeneralApprovalEntity entity) {
        String id = GenerationUUID.getUUID();
        entity.setId(id);

        entity.setCreateTime(TimeUtils.getNowSystemTime());//创建时间
        entity.setApprovalResults("2");
        save(entity);
    }

    @Override
    public void updateGeneralApprovalEntity(GeneralApprovalEntity entity) {
        entity.setApprovalTime(TimeUtils.getNowSystemTime());//审批时间

        updateById(entity);
    }
}
