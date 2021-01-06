package com.brad.web.webspringboot.oaSystem.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.brad.web.webspringboot.common.entity.FebsConstant;
import com.brad.web.webspringboot.common.entity.QueryRequest;
import com.brad.web.webspringboot.common.utils.GenerationUUID;
import com.brad.web.webspringboot.common.utils.SortUtil;
import com.brad.web.webspringboot.common.utils.TimeUtils;
import com.brad.web.webspringboot.oaSystem.bean.OnlineLearningBean;
import com.brad.web.webspringboot.oaSystem.entity.OnlineLearningEntity;
import com.brad.web.webspringboot.oaSystem.mapper.OnlineLearningEntityMapper;
import com.brad.web.webspringboot.oaSystem.service.OnlineLearningEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * @version 1.0
 * @author： 李逸野
 * @date： 2020-11-06
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class OnlineLearningEntityServiceImpl  extends ServiceImpl<OnlineLearningEntityMapper, OnlineLearningEntity> implements OnlineLearningEntityService {


    @Override
    public IPage<OnlineLearningBean> getOnlineLearningEntityList(OnlineLearningBean onlineLearningBean, QueryRequest request) {
        Page<OnlineLearningBean> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countOnlineLearningEntityDetail(onlineLearningBean));

        SortUtil.handlePageSort(request, page, "createTime", FebsConstant.ORDER_DESC, false);// 处理排序（分页情况下）

        return this.baseMapper.getOnlineLearningEntityList(page, onlineLearningBean);
    }

    @Override
    public List<OnlineLearningBean> getOnlineLearningEntityByList(OnlineLearningBean onlineLearningBean) {
        return this.baseMapper.getOnlineLearningEntityByList(onlineLearningBean);

    }

    @Override
    public void deleteOnlineLearningEntity(String[] Ids) {
        List<String> list = Arrays.asList(Ids);
        // 删除在线学习中心中文章
        this.removeByIds(list);
    }

    @Override
    public void createOnlineLearningEntity(OnlineLearningEntity entity) {
        String id = GenerationUUID.getUUID();
        entity.setId(id);


        if(entity.getOlReleaseTime() == null || "".equals(entity.getOlReleaseTime())){
            entity.setOlReleaseTime(TimeUtils.getNowSystemTime());//发布时间(不选择默认当前发布时间)
        }

        entity.setOlNumberVisits(0);//创建时间

        entity.setCreateTime(TimeUtils.getNowSystemTime());//创建时间

        save(entity);
    }

    @Override
    public void updateOnlineLearningEntity(OnlineLearningEntity entity) {
        updateById(entity);
    }
}
