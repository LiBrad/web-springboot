package com.brad.web.webspringboot.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.brad.web.webspringboot.common.entity.DeptTree;
import com.brad.web.webspringboot.common.entity.QueryRequest;
import com.brad.web.webspringboot.system.entity.Dept;

import java.util.List;

/**
 * @author MrBird
 */
public interface IDeptService extends IService<Dept> {

    /**
     * 获取部门树（下拉选使用）
     *
     * @return 部门树集合
     */
    List<DeptTree<Dept>> findDept();

    /**
     * 获取部门列表（树形列表）
     *
     * @param dept 部门对象（传递查询参数）
     * @return 部门树
     */
    List<DeptTree<Dept>> findDept(Dept dept);

    /**
     * 获取部门树（供Excel导出）
     *
     * @param dept    部门对象（传递查询参数）
     * @param request QueryRequest
     * @return List<Dept>
     */
    List<Dept> findDept(Dept dept, QueryRequest request);

    /**
     * 新增部门
     *
     * @param dept 部门对象
     */
    void createDept(Dept dept);

    /**
     * 修改部门
     *
     * @param dept 部门对象
     */
    void updateDept(Dept dept);

    /**
     * 删除部门
     *
     * @param deptIds 部门 ID集合
     */
    void deleteDept(String[] deptIds);
}
