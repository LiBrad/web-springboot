package com.brad.web.webspringboot.oaSystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.brad.web.webspringboot.oaSystem.bean.InsideMailBean;
import com.brad.web.webspringboot.oaSystem.entity.InsideMailEntity;
import org.apache.ibatis.annotations.Param;

public interface InsideMailEntityMapper extends BaseMapper<InsideMailEntity> {



    /**
     * 查询内部邮件列表
     *
     * @param page 分页对象
     * @param insideMailBean 对象，用于传递查询条件
     * @return Ipage
     */
    <T> IPage<InsideMailBean> getInsideMailEntityList(Page<T> page, @Param("insideMailBean")InsideMailBean insideMailBean);

    long countNewsEntityDetail(@Param("insideMailBean")InsideMailBean insideMailBean);

}