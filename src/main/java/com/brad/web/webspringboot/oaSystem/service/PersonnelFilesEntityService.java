package com.brad.web.webspringboot.oaSystem.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.brad.web.webspringboot.common.entity.QueryRequest;
import com.brad.web.webspringboot.oaSystem.bean.PersonnelFilesBean;
import com.brad.web.webspringboot.oaSystem.entity.PersonnelFilesEntity;

import java.util.List;

/**
 * @version 1.0
 * @author： 李逸野
 * @date： 2020-11-11
 */
public interface PersonnelFilesEntityService {




    /**
     * 查询人事档案
     * @return
     */
    IPage<PersonnelFilesBean> getPersonnelFilesEntityList(PersonnelFilesBean personnelFilesBean, QueryRequest request);

    List<PersonnelFilesBean> getPersonnelFilesEntityByList(PersonnelFilesBean personnelFilesBean);

    void deletePersonnelFilesEntity(String[] Ids);

    void createPersonnelFilesEntity(PersonnelFilesEntity entity);

    void updatePersonnelFilesEntity(PersonnelFilesEntity entity);
//    /**
//     * 导入人事档案
//     * @return
//     */
//    int importPersonnelFiles(List<PersonnelFilesEntity> entity);

}
