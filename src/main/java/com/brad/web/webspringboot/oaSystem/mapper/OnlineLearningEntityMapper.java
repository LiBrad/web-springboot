package com.brad.web.webspringboot.oaSystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.brad.web.webspringboot.oaSystem.bean.OnlineLearningBean;
import com.brad.web.webspringboot.oaSystem.entity.OnlineLearningEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OnlineLearningEntityMapper extends BaseMapper<OnlineLearningEntity> {



    /**
     * 查询在线学习中心
     * @return
     */
    <T> IPage<OnlineLearningBean> getOnlineLearningEntityList(Page<T> page, @Param("onlineLearningBean") OnlineLearningBean onlineLearningBean);

    /**
     * 查询在线学习中心(列表，可根据id或其他条件查询详情)
     *
     * @return Ipage
     */
    List<OnlineLearningBean> getOnlineLearningEntityByList(@Param("onlineLearningBean") OnlineLearningBean onlineLearningBean);


//    /**
//     * 查询在线学习中心（AO门户信息公共区域）
//     * @return
//     */
//    List<OnlineLearningBean> getOnlineLearningOACommonEntityList(Map<String, Object> paramap);


    /**
     * 查询总条数
     * @return
     */
    long countOnlineLearningEntityDetail(@Param("onlineLearningBean") OnlineLearningBean onlineLearningBean);
}