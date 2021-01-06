package com.brad.web.webspringboot.oaSystem.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.brad.web.webspringboot.common.entity.FebsConstant;
import com.brad.web.webspringboot.common.entity.QueryRequest;
import com.brad.web.webspringboot.common.utils.GenerationUUID;
import com.brad.web.webspringboot.common.utils.SortUtil;
import com.brad.web.webspringboot.common.utils.TimeUtils;
import com.brad.web.webspringboot.oaSystem.bean.PersonnelFilesBean;
import com.brad.web.webspringboot.oaSystem.entity.PersonnelFilesEntity;
import com.brad.web.webspringboot.oaSystem.mapper.PersonnelFilesEntityMapper;
import com.brad.web.webspringboot.oaSystem.service.PersonnelFilesEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * @version 1.0
 * @author： 李逸野
 * @date： 2020-11-11
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PersonnelFilesEntityServiceImpl   extends ServiceImpl<PersonnelFilesEntityMapper, PersonnelFilesEntity> implements PersonnelFilesEntityService {



    @Override
    public IPage<PersonnelFilesBean> getPersonnelFilesEntityList(PersonnelFilesBean personnelFilesBean, QueryRequest request) {
        Page<PersonnelFilesBean> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countPersonnelFilesDetail(personnelFilesBean));

        SortUtil.handlePageSort(request, page, "createTime", FebsConstant.ORDER_DESC, false);// 处理排序（分页情况下）

        return this.baseMapper.getPersonnelFilesEntityList(page, personnelFilesBean);
    }

    @Override
    public List<PersonnelFilesBean> getPersonnelFilesEntityByList(PersonnelFilesBean personnelFilesBean) {
        return this.baseMapper.getPersonnelFilesEntityByList(personnelFilesBean);
    }

    @Override
    public void deletePersonnelFilesEntity(String[] Ids) {
        List<String> list = Arrays.asList(Ids);
        // 删除人事档案信息
        this.removeByIds(list);
    }

    @Override
    public void createPersonnelFilesEntity(PersonnelFilesEntity entity) {
        String id = GenerationUUID.getUUID();
        entity.setId(id);
        entity.setCreateTime(TimeUtils.getNowSystemTime());//创建时间

        save(entity);

    }

    @Override
    public void updatePersonnelFilesEntity(PersonnelFilesEntity entity) {

        updateById(entity);

    }

}
