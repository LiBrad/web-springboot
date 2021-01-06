package com.brad.web.webspringboot.oaSystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.brad.web.webspringboot.oaSystem.bean.PersonnelFilesBean;
import com.brad.web.webspringboot.oaSystem.entity.PersonnelFilesEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PersonnelFilesEntityMapper extends BaseMapper<PersonnelFilesEntity> {


    /**
     * 查询人事档案
     * @return
     */
    <T> IPage<PersonnelFilesBean> getPersonnelFilesEntityList(Page<T> page, @Param("personnelFilesBean") PersonnelFilesBean personnelFilesBean);

    /**
     * 查询人事档案(列表，可根据id或其他条件查询详情)
     *
     * @return Ipage
     */
    List<PersonnelFilesBean> getPersonnelFilesEntityByList(  @Param("personnelFilesBean") PersonnelFilesBean personnelFilesBean);


    /**
     * 查询总条数
     * @return
     */
    long countPersonnelFilesDetail(@Param("personnelFilesBean") PersonnelFilesBean personnelFilesBean);

}