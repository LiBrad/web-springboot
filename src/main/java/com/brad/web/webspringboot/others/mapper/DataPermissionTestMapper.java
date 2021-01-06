package com.brad.web.webspringboot.others.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.brad.web.webspringboot.common.annotation.DataPermission;
import com.brad.web.webspringboot.others.entity.DataPermissionTest;

/**
 * @author MrBird
 */
@DataPermission(methods = {"selectPage"})
public interface DataPermissionTestMapper extends BaseMapper<DataPermissionTest> {

}
