package com.brad.web.webspringboot.oaSystem.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.brad.web.webspringboot.common.entity.FebsConstant;
import com.brad.web.webspringboot.common.entity.QueryRequest;
import com.brad.web.webspringboot.common.utils.GenerationUUID;
import com.brad.web.webspringboot.common.utils.SortUtil;
import com.brad.web.webspringboot.common.utils.TimeUtils;
import com.brad.web.webspringboot.oaSystem.bean.NewsBean;
import com.brad.web.webspringboot.oaSystem.bean.NoticeBean;
import com.brad.web.webspringboot.oaSystem.entity.NoticeEntity;
import com.brad.web.webspringboot.oaSystem.mapper.NoticeEntityMapper;
import com.brad.web.webspringboot.oaSystem.service.NoticeEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * @version 1.0
 * @author： 李逸野
 * @date： 2020-11-04
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class NoticeEntityServiceImpl extends ServiceImpl<NoticeEntityMapper, NoticeEntity> implements NoticeEntityService {


    @Override
    public IPage<NoticeBean> getNoticeEntityList(NoticeBean noticeBean, QueryRequest request) {
        Page<NewsBean> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countNoticeEntityDetail(noticeBean));

        SortUtil.handlePageSort(request, page, "createTime", FebsConstant.ORDER_DESC, false);// 处理排序（分页情况下）

        return this.baseMapper.getNoticeEntityList(page, noticeBean);
    }

    @Override
    public List<NoticeBean> getNoticeEntityByList(NoticeBean noticeBean) {
        return this.baseMapper.getNoticeEntityByList(noticeBean);
    }

    @Override
    public void deleteNoticeEntity(String[] Ids) {
        List<String> list = Arrays.asList(Ids);
        // 删除通知公告
        this.removeByIds(list);
    }

    @Override
    public void createNoticeEntity(NoticeEntity entity) {
        String id = GenerationUUID.getUUID();
        entity.setId(id);

        if(entity.getNoticeReleaseTime() == null || "".equals(entity.getNoticeReleaseTime())){
            entity.setNoticeReleaseTime(TimeUtils.getNowSystemTime());//发布时间(不选择默认当前发布时间)
        }
        entity.setCreateTime(TimeUtils.getNowSystemTime());//创建时间

        save(entity);
    }

    @Override
    public void updateNoticeEntity(NoticeEntity entity) {

        updateById(entity);
    }

}
