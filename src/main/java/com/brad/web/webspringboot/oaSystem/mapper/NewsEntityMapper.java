package com.brad.web.webspringboot.oaSystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.brad.web.webspringboot.oaSystem.bean.NewsBean;
import com.brad.web.webspringboot.oaSystem.entity.NewsEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NewsEntityMapper extends BaseMapper<NewsEntity> {



    /**
     * 查询院内新闻表
     *
     * @param page 分页对象
     * @param newsBean 用户对象，用于传递查询条件
     * @return Ipage
     */
    <T> IPage<NewsBean> getNewsEntityList(Page<T> page, @Param("newsBean") NewsBean newsBean);

    /**
     * 查询院内新闻表(列表，可根据id或其他条件查询详情)
     *
     * @return Ipage
     */
    List<NewsBean> getNewsEntityByList(@Param("newsBean") NewsBean newsBean);

//    /**
//     * 查询院内新闻（AO门户信息公共区域）
//     * @return
//     */
//    <T> IPage<NewsBean> getNewsEntityOACommonList(Page<T> page, @Param("newsBean") NewsBean newsBean);
//
//
//
//
//    /**
//     * 查询院内新闻（AO门户信息公共区域）查询图片新闻
//     * @return
//     */
//    List<NewsBean> getNewsEntityOACommonNewsRecommendedImageTypeList(@Param("newsBean") NewsBean newsBean);

    long countNewsEntityDetail(@Param("newsBean") NewsBean newsBean);



}