package com.brad.web.webspringboot.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.brad.web.webspringboot.common.entity.FebsConstant;
import com.brad.web.webspringboot.common.entity.QueryRequest;
import com.brad.web.webspringboot.common.event.UserAuthenticationUpdatedEventPublisher;
import com.brad.web.webspringboot.common.exception.FebsException;
import com.brad.web.webspringboot.common.utils.FebsUtil;
import com.brad.web.webspringboot.common.utils.Md5Util;
import com.brad.web.webspringboot.common.utils.SortUtil;
import com.brad.web.webspringboot.system.entity.*;
import com.brad.web.webspringboot.system.mapper.UserMapper;
import com.brad.web.webspringboot.system.service.*;
import com.google.common.collect.Sets;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author MrBird
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private final IMenuService menuService;
    private final IRoleService roleService;
    private final IUserRoleService userRoleService;
    private final UserAuthenticationUpdatedEventPublisher publisher;
    private final IUserDataPermissionService userDataPermissionService;


    @Override
    public User findByName(String username) {
        return this.baseMapper.findByName(username);
    }

    @Override
    public IPage<User> findUserDetailList(User user, QueryRequest request) {
        if (StringUtils.isNotBlank(user.getCreateTimeFrom()) &&
                StringUtils.equals(user.getCreateTimeFrom(), user.getCreateTimeTo())) {
            user.setCreateTimeFrom(user.getCreateTimeFrom() + " 00:00:00");
            user.setCreateTimeTo(user.getCreateTimeTo() + " 23:59:59");
        }
        Page<User> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countUserDetail(user));
        SortUtil.handlePageSort(request, page, "userId", FebsConstant.ORDER_ASC, false);
        return this.baseMapper.findUserDetailPage(page, user);
    }

    @Override
    public User findUserDetailList(String username) {
        User param = new User();
        param.setUsername(username);
        List<User> users = this.baseMapper.findUserDetail(param);
        return CollectionUtils.isNotEmpty(users) ? users.get(0) : null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateLoginTime(String username) {
        User user = new User();
        user.setLastLoginTime(new Date());
        this.baseMapper.update(user, new LambdaQueryWrapper<User>().eq(User::getUsername, username));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createUser(User user) {
        user.setCreateTime(new Date());
        user.setStatus(User.STATUS_VALID);
        user.setAvatar(User.DEFAULT_AVATAR);
        user.setTheme(User.THEME_BLACK);
        user.setIsTab(User.TAB_OPEN);
        user.setPassword(Md5Util.encrypt(user.getUsername(), User.DEFAULT_PASSWORD));
        save(user);
        // 保存用户角色
        String[] roles = user.getRoleId().split(StringPool.COMMA);
        setUserRoles(user, roles);
        // 保存用户数据权限关联关系
        String[] deptIds = StringUtils.splitByWholeSeparatorPreserveAllTokens(user.getDeptIds(), StringPool.COMMA);
        if (ArrayUtils.isNotEmpty(deptIds)) {
            setUserDataPermissions(user, deptIds);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteUsers(String[] userIds) {
        List<String> list = Arrays.asList(userIds);
        // 删除用户
        this.removeByIds(list);
        // 删除关联角色
        this.userRoleService.deleteUserRolesByUserId(list);
        // 删除关联数据权限
        this.userDataPermissionService.deleteByUserIds(userIds);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUser(User user) {
        // 更新用户
        user.setPassword(null);
        user.setUsername(null);
        user.setModifyTime(new Date());
        updateById(user);

        String[] userId = {String.valueOf(user.getUserId())};
        this.userRoleService.deleteUserRolesByUserId(Arrays.asList(userId));
        String[] roles = StringUtils.splitByWholeSeparatorPreserveAllTokens(user.getRoleId(), StringPool.COMMA);
        setUserRoles(user, roles);

        userDataPermissionService.deleteByUserIds(userId);
        String[] deptIds = StringUtils.splitByWholeSeparatorPreserveAllTokens(user.getDeptIds(), StringPool.COMMA);
        if (ArrayUtils.isNotEmpty(deptIds)) {
            setUserDataPermissions(user, deptIds);
        }
        publisher.publishEvent(Sets.newHashSet(user.getUserId()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void resetPassword(String[] usernames) {
        Arrays.stream(usernames).forEach(username -> {
            User user = new User();
            user.setPassword(Md5Util.encrypt(username, User.DEFAULT_PASSWORD));
            this.baseMapper.update(user, new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(String username, String password) {
        User user = new User();
        user.setPassword(Md5Util.encrypt(username, password));
        user.setUsername(username);
        user.setCreateTime(new Date());
        user.setStatus(User.STATUS_VALID);
        user.setSex(User.SEX_UNKNOW);
        user.setAvatar(User.DEFAULT_AVATAR);
        user.setTheme(User.THEME_BLACK);
        user.setIsTab(User.TAB_OPEN);
        user.setDescription("注册用户");
        this.save(user);

        UserRole ur = new UserRole();
        ur.setUserId(user.getUserId());
        ur.setRoleId(FebsConstant.REGISTER_ROLE_ID);
        this.userRoleService.save(ur);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePassword(String username, String password) {
        User user = new User();
        user.setPassword(Md5Util.encrypt(username, password));
        user.setModifyTime(new Date());
        this.baseMapper.update(user, new LambdaQueryWrapper<User>().eq(User::getUsername, username));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateAvatar(String username, String avatar) {
        User user = new User();
        user.setAvatar(avatar);
        user.setModifyTime(new Date());
        this.baseMapper.update(user, new LambdaQueryWrapper<User>().eq(User::getUsername, username));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateTheme(String username, String theme, String isTab) {
        User user = new User();
        user.setTheme(theme);
        user.setIsTab(isTab);
        user.setModifyTime(new Date());
        this.baseMapper.update(user, new LambdaQueryWrapper<User>().eq(User::getUsername, username));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateProfile(User user) {
        user.setUsername(null);
        user.setRoleId(null);
        user.setPassword(null);
        if (isCurrentUser(user.getUserId())) {
            updateById(user);
        } else {
            throw new FebsException("您无权修改别人的账号信息！");
        }
    }

    @Override
    public void doGetUserAuthorizationInfo(User user) {
        // 获取用户角色集
        List<Role> roleList = this.roleService.findUserRole(user.getUsername());
        Set<String> roleSet = roleList.stream().map(Role::getRoleName).collect(Collectors.toSet());
        user.setRoles(roleSet);

        // 获取用户权限集
        List<Menu> permissionList = this.menuService.findUserPermissions(user.getUsername());
        Set<String> permissionSet = permissionList.stream().map(Menu::getPerms).collect(Collectors.toSet());
        user.setStringPermissions(permissionSet);
    }

    private void setUserRoles(User user, String[] roles) {
        List<UserRole> userRoles = new ArrayList<>();
        Arrays.stream(roles).forEach(roleId -> {
            UserRole userRole = new UserRole();
            userRole.setUserId(user.getUserId());
            userRole.setRoleId(Long.valueOf(roleId));
            userRoles.add(userRole);
        });
        userRoleService.saveBatch(userRoles);
    }

    private void setUserDataPermissions(User user, String[] deptIds) {
        List<UserDataPermission> userDataPermissions = new ArrayList<>();
        Arrays.stream(deptIds).forEach(deptId -> {
            UserDataPermission permission = new UserDataPermission();
            permission.setDeptId(Long.valueOf(deptId));
            permission.setUserId(user.getUserId());
            userDataPermissions.add(permission);
        });
        userDataPermissionService.saveBatch(userDataPermissions);
    }

    private boolean isCurrentUser(Long id) {
        User currentUser = FebsUtil.getCurrentUser();
        return currentUser.getUserId().equals(id);
    }
}
