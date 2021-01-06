package com.brad.web.webspringboot.oaSystem.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.brad.web.webspringboot.common.entity.FebsConstant;
import com.brad.web.webspringboot.common.entity.QueryRequest;
import com.brad.web.webspringboot.common.utils.GenerationUUID;
import com.brad.web.webspringboot.common.utils.SortUtil;
import com.brad.web.webspringboot.common.utils.TimeUtils;
import com.brad.web.webspringboot.oaSystem.bean.InsideMailBean;
import com.brad.web.webspringboot.oaSystem.entity.InsideMailEntity;
import com.brad.web.webspringboot.oaSystem.mapper.InsideMailEntityMapper;
import com.brad.web.webspringboot.oaSystem.service.InsideMailEntityService;
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
public class InsideMailEntityServiceImpl extends ServiceImpl<InsideMailEntityMapper, InsideMailEntity> implements InsideMailEntityService {
    private final Logger logger = LoggerFactory.getLogger(InsideMailEntityServiceImpl.class);


    @Override
    public IPage<InsideMailBean> getInsideMailEntityList(InsideMailBean insideMailBean, QueryRequest request) {
        Page<InsideMailBean> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countNewsEntityDetail(insideMailBean));

        SortUtil.handlePageSort(request, page, "createTime", FebsConstant.ORDER_DESC, false);// 处理排序（分页情况下）

        return this.baseMapper.getInsideMailEntityList(page, insideMailBean);
    }

    @Override
    public List<InsideMailBean> getInsideMailEntityByList(InsideMailBean insideMailBean) {
        return this.getInsideMailEntityList(insideMailBean,new QueryRequest()).getRecords();
    }

    @Override
    public void deleteInsideMailEntity(String[] Ids) {
        List<String> list = Arrays.asList(Ids);
        // 删除内部邮件列表
        this.removeByIds(list);
    }

    @Override
    public void createInsideMailEntity(InsideMailBean insideMailBean) {

        String[] insideMaiAddresseelUserIdS = insideMailBean.getInsideMaiAddresseelUserIdS().split(StringPool.COMMA);
        for (int i = 0; i < insideMaiAddresseelUserIdS.length; i++) {
            InsideMailEntity entity = new InsideMailEntity();
            String id = GenerationUUID.getUUID();
            entity.setId(id);
            entity.setInsideMaiAddresseelUserId(insideMaiAddresseelUserIdS[i]);//收件人ID
            entity.setInsideMaiFromUserId(insideMailBean.getInsideMaiFromUserId());
            entity.setInsideMailToRead("0");
            entity.setInsideMailToReply("0");
            entity.setInsideMailContent(insideMailBean.getInsideMailContent());
            entity.setInsideMailTheme(insideMailBean.getInsideMailTheme());
            entity.setInsideMailEnclosureUrl(insideMailBean.getInsideMailEnclosureUrl());
            entity.setCreateTime(TimeUtils.getNowSystemTime());//创建时间

            save(entity);
        }


    }

    @Override
    public void updateInsideMailEntity(InsideMailEntity entity) {


        updateById(entity);
    }
}
