package com.brad.web.webspringboot.others.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.brad.web.webspringboot.common.entity.QueryRequest;
import com.brad.web.webspringboot.others.entity.DataPermissionTest;
import com.brad.web.webspringboot.others.mapper.DataPermissionTestMapper;
import com.brad.web.webspringboot.others.service.IDataPermissionTestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author MrBird
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class DataPermissionTestServiceImpl extends ServiceImpl<DataPermissionTestMapper, DataPermissionTest> implements IDataPermissionTestService {

    @Override
    public IPage<DataPermissionTest> findDataPermissionTests(QueryRequest request, DataPermissionTest dataPermissionTest) {
        LambdaQueryWrapper<DataPermissionTest> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(DataPermissionTest::getCreateTime);
        Page<DataPermissionTest> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }
}
