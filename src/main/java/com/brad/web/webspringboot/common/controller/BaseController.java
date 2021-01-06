package com.brad.web.webspringboot.common.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.brad.web.webspringboot.system.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author MrBird
 */
public class BaseController {

    private Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    protected User getCurrentUser() {
        return (User) getSubject().getPrincipal();
    }

    protected Session getSession() {
        return getSubject().getSession();
    }

    protected Session getSession(Boolean flag) {
        return getSubject().getSession(flag);
    }

    protected void login(AuthenticationToken token) {
        getSubject().login(token);
    }

    protected Map<String, Object> getDataTable(IPage<?> pageInfo) {
        return getDataTable(pageInfo, 2);
    }

    protected Map<String, Object> getDataTable(IPage<?> pageInfo, int dataMapInitialCapacity) {
        Map<String, Object> data = new HashMap<>(dataMapInitialCapacity,1);
        data.put("rows", pageInfo.getRecords());
        data.put("total", pageInfo.getTotal());
//        long totalPages = (numFound+pageSize-1)/pageSize;
//        long totalPages = (pageInfo.getTotal() / 2 + (pageInfo.getTotal() % 2 > 0 ? 1 : 0));

        long totalPages = (pageInfo.getTotal() / 10 + (pageInfo.getTotal() % 10 > 0 ? 1 : 0));
        data.put("totalPages",totalPages);
        data.put("curPage",pageInfo.getCurrent());

        return data;
    }

}
