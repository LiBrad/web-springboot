package com.brad.web.webspringboot.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.brad.web.webspringboot.common.entity.FebsConstant;
import com.brad.web.webspringboot.common.entity.QueryRequest;
import com.brad.web.webspringboot.common.event.UserAuthenticationUpdatedEventPublisher;
import com.brad.web.webspringboot.common.utils.SortUtil;
import com.brad.web.webspringboot.system.entity.Role;
import com.brad.web.webspringboot.system.entity.RoleMenu;
import com.brad.web.webspringboot.system.mapper.RoleMapper;
import com.brad.web.webspringboot.system.service.IRoleMenuService;
import com.brad.web.webspringboot.system.service.IRoleService;
import com.brad.web.webspringboot.system.service.IUserRoleService;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author MrBird
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    private final IRoleMenuService roleMenuService;
    private final IUserRoleService userRoleService;
    private final UserAuthenticationUpdatedEventPublisher publisher;

    @Override
    public List<Role> findUserRole(String username) {
        return this.baseMapper.findUserRole(username);
    }

    @Override
    public List<Role> findRoles(Role role) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(role.getRoleName())) {
            queryWrapper.lambda().like(Role::getRoleName, role.getRoleName());
        }
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<Role> findRoles(Role role, QueryRequest request) {
        Page<Role> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countRole(role));
        SortUtil.handlePageSort(request, page, "createTime", FebsConstant.ORDER_DESC, false);
        return this.baseMapper.findRolePage(page, role);
    }



    @Override
    public Role findByName(String roleName) {
        return this.baseMapper.selectOne(new QueryWrapper<Role>().lambda().eq(Role::getRoleName, roleName));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createRole(Role role) {
        role.setCreateTime(new Date());
        this.baseMapper.insert(role);
        this.saveRoleMenus(role);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRole(Role role) {
        role.setModifyTime(new Date());
        this.updateById(role);
        List<String> roleIdList = Lists.newArrayList(String.valueOf(role.getRoleId()));
        this.roleMenuService.deleteRoleMenusByRoleId(roleIdList);
        saveRoleMenus(role);
        Set<Long> userIds = this.userRoleService.findUserIdByRoleId(role.getRoleId());
        if (CollectionUtils.isNotEmpty(userIds)) {
            publisher.publishEvent(userIds);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRoles(String roleIds) {
        List<String> list = Arrays.asList(roleIds.split(StringPool.COMMA));
        this.baseMapper.delete(new QueryWrapper<Role>().lambda().in(Role::getRoleId, list));

        this.roleMenuService.deleteRoleMenusByRoleId(list);
        this.userRoleService.deleteUserRolesByRoleId(list);
        Set<Long> userIds = this.userRoleService.findUserIdByRoleIds(list);
        if (CollectionUtils.isNotEmpty(userIds)) {
            publisher.publishEvent(userIds);
        }
    }

    @Override
    public List<Role> getRoleList(Role role) {
        return this.baseMapper.getRoleList(role);
    }


    private void saveRoleMenus(Role role) {
        if (StringUtils.isNotBlank(role.getMenuIds())) {
            String[] menuIds = role.getMenuIds().split(StringPool.COMMA);
            List<RoleMenu> roleMenus = Lists.newArrayList();
            Arrays.stream(menuIds).forEach(menuId -> {
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setMenuId(Long.valueOf(menuId));
                roleMenu.setRoleId(role.getRoleId());
                roleMenus.add(roleMenu);
            });
            roleMenuService.saveBatch(roleMenus);
        }
    }
}
