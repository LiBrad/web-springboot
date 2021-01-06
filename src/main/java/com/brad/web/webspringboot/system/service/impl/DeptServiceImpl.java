package com.brad.web.webspringboot.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.brad.web.webspringboot.common.entity.DeptTree;
import com.brad.web.webspringboot.common.entity.FebsConstant;
import com.brad.web.webspringboot.common.entity.QueryRequest;
import com.brad.web.webspringboot.common.utils.SortUtil;
import com.brad.web.webspringboot.common.utils.TreeUtil;
import com.brad.web.webspringboot.system.entity.Dept;
import com.brad.web.webspringboot.system.mapper.DeptMapper;
import com.brad.web.webspringboot.system.service.IDeptService;
import com.brad.web.webspringboot.system.service.IUserDataPermissionService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author MrBird
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {

    private final IUserDataPermissionService userDataPermissionService;

    @Override
    public List<DeptTree<Dept>> findDept() {
        List<Dept> deptList = this.baseMapper.selectList(new QueryWrapper<>());
        List<DeptTree<Dept>> trees = this.convertDept(deptList);
        return TreeUtil.buildDeptTree(trees);
    }

    @Override
    public List<DeptTree<Dept>> findDept(Dept dept) {
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();

        if (StringUtils.isNotBlank(dept.getDeptName())) {
            queryWrapper.lambda().eq(Dept::getDeptName, dept.getDeptName());
        }
        queryWrapper.lambda().orderByAsc(Dept::getOrderNum);

        List<Dept> deptList = this.baseMapper.selectList(queryWrapper);
        List<DeptTree<Dept>> trees = this.convertDept(deptList);
        return TreeUtil.buildDeptTree(trees);
    }

    @Override
    public List<Dept> findDept(Dept dept, QueryRequest request) {
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();

        if (StringUtils.isNotBlank(dept.getDeptName())) {
            queryWrapper.lambda().eq(Dept::getDeptName, dept.getDeptName());
        }
        SortUtil.handleWrapperSort(request, queryWrapper, "orderNum", FebsConstant.ORDER_ASC, true);
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createDept(Dept dept) {
        Long parentId = dept.getParentId();
        if (parentId == null) {
            dept.setParentId(Dept.TOP_NODE);
        }
        dept.setCreateTime(new Date());
        this.save(dept);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDept(Dept dept) {
        dept.setModifyTime(new Date());
        this.baseMapper.updateById(dept);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDept(String[] deptIds) {
        this.delete(Arrays.asList(deptIds));
    }

    private List<DeptTree<Dept>> convertDept(List<Dept> Dept) {
        List<DeptTree<Dept>> trees = new ArrayList<>();
        Dept.forEach(dept -> {
            DeptTree<Dept> tree = new DeptTree<>();
            tree.setId(String.valueOf(dept.getDeptId()));
            tree.setParentId(String.valueOf(dept.getParentId()));
            tree.setName(dept.getDeptName());
            tree.setData(dept);
            trees.add(tree);
        });
        return trees;
    }

    private void delete(List<String> deptIds) {
        removeByIds(deptIds);
        userDataPermissionService.deleteByDeptIds(deptIds);

        LambdaQueryWrapper<Dept> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Dept::getParentId, deptIds);
        List<Dept> Dept = baseMapper.selectList(queryWrapper);
        if (CollectionUtils.isNotEmpty(Dept)) {
            List<String> deptIdList = new ArrayList<>();
            Dept.forEach(d -> deptIdList.add(String.valueOf(d.getDeptId())));
            this.delete(deptIdList);
        }
    }
}
