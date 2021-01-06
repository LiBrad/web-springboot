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
import com.brad.web.webspringboot.oaSystem.entity.NewsEntity;
import com.brad.web.webspringboot.oaSystem.mapper.NewsEntityMapper;
import com.brad.web.webspringboot.oaSystem.service.NewsEntityService;
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
public class NewsEntityServiceImpl extends ServiceImpl<NewsEntityMapper, NewsEntity> implements NewsEntityService {


    @Override
    public IPage<NewsBean> getNewsEntityList(NewsBean newsBean, QueryRequest request) {
        Page<NewsBean> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countNewsEntityDetail(newsBean));

        SortUtil.handlePageSort(request, page, "createTime", FebsConstant.ORDER_DESC, false);// 处理排序（分页情况下）

        return this.baseMapper.getNewsEntityList(page, newsBean);
    }


    @Override
    public IPage<NewsBean> getNewsEntityListPage(NewsBean newsBean, QueryRequest request) {
        Page<NewsBean> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countNewsEntityDetail(newsBean));

        SortUtil.handlePageSort(request, page, "newsReleaseTime", FebsConstant.ORDER_DESC, false);// 处理排序（分页情况下）

        return this.baseMapper.getNewsEntityList(page, newsBean);
    }

    @Override
    public IPage<NewsBean> getNewsEntityListNewsRecommendedType(NewsBean newsBean, QueryRequest request) {
        Page<NewsBean> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countNewsEntityDetail(newsBean));

        SortUtil.handlePageSort(request, page, "newsRecommendedType", FebsConstant.ORDER_DESC, false);// 处理排序（分页情况下）

        return this.baseMapper.getNewsEntityList(page, newsBean);
    }

    @Override
    public List<NewsBean> getNewsEntityByList(NewsBean newsBean) {
        return this.baseMapper.getNewsEntityByList(newsBean);
    }




//    @Override
//    public IPage<NewsBean> getNewsEntityOACommonList(NewsBean newsBean, QueryRequest request) {
//        return null;
//    }
//
//    @Override
//    public IPage<User> getNewsEntityOACommonNewsRecommendedImageTypeList(NewsBean newsBean) {
//        return null;
//    }

    @Override
    public void deleteNewsEntity(String[] Ids) {
        List<String> list = Arrays.asList(Ids);
        // 删除院内新闻
        this.removeByIds(list);
    }

    @Override
    public void createNewsEntity(NewsEntity entity) {
        String id = GenerationUUID.getUUID();
        entity.setId(id);

        if(entity.getNewsReleaseTime() == null || "".equals(entity.getNewsReleaseTime())){
            entity.setNewsReleaseTime(TimeUtils.getNowSystemTime());//发布时间(不选择默认当前发布时间)
        }

        if(entity.getNewsNumberVisits() == null || "".equals(entity.getNewsNumberVisits()) ){
            entity.setNewsNumberVisits(0);//浏览次数(点击浏览该信息自动+1)
        }

        String newsRecommendedType = entity.getNewsRecommendedType();
        if("on".equals(newsRecommendedType)){
            entity.setNewsRecommendedType("1");//推荐类型-置顶（1.开启，0关闭）
        }

        String newsRecommendedImageType = entity.getNewsRecommendedImageType();
        if("on".equals(newsRecommendedImageType)){
            entity.setNewsRecommendedImageType("1");//推荐类型-图片新闻（1.开启，0关闭）
        }

        entity.setCreateTime(TimeUtils.getNowSystemTime());//创建时间

        save(entity);

    }

    @Override
    public void updateNewsEntity(NewsEntity entity) {

        String newsRecommendedType = entity.getNewsRecommendedType();
        if("on".equals(newsRecommendedType)){
            entity.setNewsRecommendedType("1");//推荐类型-置顶（1.开启，0关闭）
        }else{
            entity.setNewsRecommendedType("0");//推荐类型-置顶（1.开启，0关闭）

        }

        String newsRecommendedImageType = entity.getNewsRecommendedImageType();
        if("on".equals(newsRecommendedImageType)){
            entity.setNewsRecommendedImageType("1");//推荐类型-图片新闻（1.开启，0关闭）
        }else{
            entity.setNewsRecommendedImageType("0");//推荐类型-图片新闻（1.开启，0关闭）

        }
        updateById(entity);
    }

    @Override
    public void updateNewsEntityFlag(NewsEntity entity) {
        if(entity.getNewsDisplayStatus() == "1" || entity.getNewsDisplayStatus().equals("1")) {
            entity.setNewsDisplayStatus("0");
        }else if(entity.getNewsDisplayStatus() =="0"||entity.getNewsDisplayStatus().equals("0")) {
            entity.setNewsDisplayStatus("1");
        }
        else {
            entity.setNewsDisplayStatus("1");
        }

        updateById(entity);
    }

}
