package com.brad.web.webspringboot.oaSystem.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.brad.web.webspringboot.common.entity.QueryRequest;
import com.brad.web.webspringboot.oaSystem.bean.NoticeBean;
import com.brad.web.webspringboot.oaSystem.entity.NoticeEntity;

import java.util.List;

/**
 * @version 1.0
 * @author： 李逸野
 * @date： 2020-11-04
 */
public interface NoticeEntityService {
    /**
     * 查询通知公告表
     * @return
     */
    IPage<NoticeBean> getNoticeEntityList(NoticeBean noticeBean, QueryRequest request);

    List<NoticeBean> getNoticeEntityByList(NoticeBean noticeBean);

//    /**
//     * 查询通知公告表
//     * @return
//     */
//    List<NoticeBean> getNoticeEntityOACommonList(Map<String, Object> paramap, String pageNum, String pageSize);

    void deleteNoticeEntity(String[] Ids);

    void createNoticeEntity(NoticeEntity entity);

    void updateNoticeEntity(NoticeEntity entity);


}
