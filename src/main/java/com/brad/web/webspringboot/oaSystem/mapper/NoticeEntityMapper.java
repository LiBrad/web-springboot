package com.brad.web.webspringboot.oaSystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.brad.web.webspringboot.oaSystem.bean.NoticeBean;
import com.brad.web.webspringboot.oaSystem.entity.NoticeEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NoticeEntityMapper  extends BaseMapper<NoticeEntity> {


    /**
     * 查询通知公告表
     * @return
     */
    <T> IPage<NoticeBean> getNoticeEntityList(Page<T> page, @Param("noticeBean") NoticeBean noticeBean);

    /**
     * 查询通知公告表(列表，可根据id或其他条件查询详情)
     *
     * @return Ipage
     */
    List<NoticeBean> getNoticeEntityByList(@Param("noticeBean")NoticeBean noticeBean);

//    /**
//     * 查询通知公告表（AO门户信息公共区域）
//     * @return
//     */
//    List<NoticeBean> getNoticeEntityOACommonList(Map<String, Object> paramap);


    /**
     * 查询总条数
     * @return
     */
    long countNoticeEntityDetail(@Param("noticeBean") NoticeBean noticeBean);


}