package com.brad.web.webspringboot.oaSystem.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.brad.web.webspringboot.common.entity.QueryRequest;
import com.brad.web.webspringboot.oaSystem.bean.OnlineLearningBean;
import com.brad.web.webspringboot.oaSystem.entity.OnlineLearningEntity;

import java.util.List;

/**
 * @version 1.0
 * @author： 李逸野
 * @date： 2020-11-06
 */
public interface OnlineLearningEntityService {


    /**
     * 查询在线学习中心
     * @return
     */
    IPage<OnlineLearningBean> getOnlineLearningEntityList(OnlineLearningBean onlineLearningBean, QueryRequest request);

    List<OnlineLearningBean> getOnlineLearningEntityByList(OnlineLearningBean onlineLearningBean);


//    /**
//     * 查询在线学习中心（AO门户信息公共区域）
//     * @return
//     */
//    List<OnlineLearningBean> getOnlineLearningOACommonEntityList(Map<String, Object> paramap, String pageNum, String pageSize);

    void deleteOnlineLearningEntity(String[] Ids);

    void createOnlineLearningEntity(OnlineLearningEntity entity);

    void updateOnlineLearningEntity(OnlineLearningEntity entity);

}
