package com.brad.web.webspringboot.oaSystem.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.brad.web.webspringboot.common.entity.QueryRequest;
import com.brad.web.webspringboot.oaSystem.bean.VideoBean;
import com.brad.web.webspringboot.oaSystem.entity.VideoEntity;

import java.util.List;

/**
 * @version 1.0
 * @author： 李逸野
 * @date： 2020-11-04
 */
public interface VideoEntityService {


    /**
     * 查询医院视频表
     * @return
     */
    IPage<VideoBean> getVideoEntityList(VideoBean videoBean, QueryRequest request);

    List<VideoBean> getVideoEntityByList(VideoBean videoBean);

    void deleteVideoEntity(String[] Ids);

    void createVideoEntity(VideoEntity entity);

    void updateVideoEntity(VideoEntity entity);


}
