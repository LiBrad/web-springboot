package com.brad.web.webspringboot.oaSystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.brad.web.webspringboot.oaSystem.bean.VideoBean;
import com.brad.web.webspringboot.oaSystem.entity.VideoEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideoEntityMapper  extends BaseMapper<VideoEntity> {





    /**
     * 查询医院视频表
     * @return
     */
    <T> IPage<VideoBean> getVideoEntityList(Page<T> page, @Param("videoBean") VideoBean videoBean);

    /**
     * 查询医院视频表(列表，可根据id或其他条件查询详情)
     *
     * @return Ipage
     */
    List<VideoBean> getVideoEntityByList( @Param("videoBean") VideoBean videoBean);


    /**
     * 查询总条数
     * @return
     */
    long countVideoEntityDetail(@Param("videoBean") VideoBean videoBean);

}