package com.brad.web.webspringboot.oaSystem.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.brad.web.webspringboot.common.entity.QueryRequest;
import com.brad.web.webspringboot.oaSystem.bean.NewsBean;
import com.brad.web.webspringboot.oaSystem.entity.NewsEntity;

import java.util.List;


/**
 * @version 1.0
 * @author： 李逸野
 * @date： 2020-11-04
 */
public interface NewsEntityService {
    /**
     * 查询院内新闻表
     * @return
     */
    IPage<NewsBean>  getNewsEntityList(NewsBean newsBean, QueryRequest request);

    List<NewsBean> getNewsEntityByList( NewsBean newsBean);


    IPage<NewsBean>  getNewsEntityListPage(NewsBean newsBean, QueryRequest request);

    IPage<NewsBean>  getNewsEntityListNewsRecommendedType(NewsBean newsBean, QueryRequest request);



//    /**
//     * 查询院内新闻（AO门户信息公共区域）
//     * @return
//     */
//    IPage<NewsBean> getNewsEntityOACommonList(NewsBean newsBean, QueryRequest request);
//
//
//    /**
//     * 查询院内新闻（AO门户信息公共区域）查询图片新闻
//     * @return
//     */
//    IPage<User> getNewsEntityOACommonNewsRecommendedImageTypeList(NewsBean newsBean);
//

    void deleteNewsEntity(String[] Ids);

    void createNewsEntity(NewsEntity entity);

    void updateNewsEntity(NewsEntity entity);

    void updateNewsEntityFlag(NewsEntity entity);
//    /**
//     * 查询总条数
//     * @return
//     */
//    int getCount(Map<String, Object> paramap);
}
