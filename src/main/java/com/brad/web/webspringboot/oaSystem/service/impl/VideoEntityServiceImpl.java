package com.brad.web.webspringboot.oaSystem.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.brad.web.webspringboot.common.entity.FebsConstant;
import com.brad.web.webspringboot.common.entity.QueryRequest;
import com.brad.web.webspringboot.common.utils.GenerationUUID;
import com.brad.web.webspringboot.common.utils.SortUtil;
import com.brad.web.webspringboot.common.utils.TimeUtils;
import com.brad.web.webspringboot.oaSystem.bean.VideoBean;
import com.brad.web.webspringboot.oaSystem.entity.VideoEntity;
import com.brad.web.webspringboot.oaSystem.mapper.VideoEntityMapper;
import com.brad.web.webspringboot.oaSystem.service.VideoEntityService;
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
public class VideoEntityServiceImpl  extends ServiceImpl<VideoEntityMapper, VideoEntity> implements VideoEntityService {


    @Override
    public IPage<VideoBean> getVideoEntityList(VideoBean videoBean, QueryRequest request) {
        Page<VideoBean> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countVideoEntityDetail(videoBean));

        SortUtil.handlePageSort(request, page, "createTime", FebsConstant.ORDER_DESC, false);// 处理排序（分页情况下）

        return this.baseMapper.getVideoEntityList(page, videoBean);
    }

    @Override
    public List<VideoBean> getVideoEntityByList(VideoBean videoBean) {
        return this.baseMapper.getVideoEntityByList(videoBean);
    }

    @Override
    public void deleteVideoEntity(String[] Ids) {
        List<String> list = Arrays.asList(Ids);
        // 删除医院视频
        this.removeByIds(list);
    }

    @Override
    public void createVideoEntity(VideoEntity entity) {
        String id = GenerationUUID.getUUID();
        entity.setId(id);

        if(entity.getVideoReleaseTime() == null || "".equals(entity.getVideoReleaseTime())){
            entity.setVideoReleaseTime(TimeUtils.getNowSystemTime());//发布时间(不选择默认当前发布时间)
        }

        entity.setCreateTime(TimeUtils.getNowSystemTime());//创建时间

        save(entity);
    }

    @Override
    public void updateVideoEntity(VideoEntity entity) {
        updateById(entity);

    }


}
