package com.brad.web.webspringboot.oaSystem.controller;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.brad.web.webspringboot.common.annotation.ControllerEndpoint;
import com.brad.web.webspringboot.common.controller.BaseController;
import com.brad.web.webspringboot.common.entity.FebsResponse;
import com.brad.web.webspringboot.common.entity.QueryRequest;
import com.brad.web.webspringboot.common.exception.FebsException;
import com.brad.web.webspringboot.oaSystem.bean.NewsBean;
import com.brad.web.webspringboot.oaSystem.entity.NewsEntity;
import com.brad.web.webspringboot.oaSystem.service.NewsEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Map;

/**
 * 院内新闻Controller
 * */

@Slf4j
@Validated  //@validated来校验数据，如果数据异常则会统一抛出异常，方便异常中心统一处理
@RestController
@RequiredArgsConstructor
@RequestMapping("news")
public class NewsEntityController extends BaseController {

    private final NewsEntityService newsEntityService;


    @GetMapping("list")
    @RequiresPermissions("news:view")
    public FebsResponse userList(NewsBean newsBean, QueryRequest request) {
        Map<String, Object> dataTable = getDataTable(this.newsEntityService.getNewsEntityList(newsBean, request));
        return new FebsResponse().success().data(dataTable);
    }

    @PostMapping
    @RequiresPermissions("news:add")
    @ControllerEndpoint(operation = "新增院内新闻", exceptionMessage = "新增院内新闻失败")
    public FebsResponse addNews( NewsEntity newsEntity) {
        this.newsEntityService.createNewsEntity(newsEntity);
        return new FebsResponse().success();
    }

//    @GetMapping("delete/{userIds}")
//    @RequiresPermissions("news:delete")
//    @ControllerEndpoint(operation = "删除院内新闻", exceptionMessage = "删除院内新闻失败")
//    public FebsResponse deleteUsers(@NotBlank(message = "{required}") @PathVariable String userIds) {
//        String[] ids = userIds.split(StringPool.COMMA);
//        this.newsEntityService.deleteUsers(ids);
//        return new FebsResponse().success();
//    }

    @PostMapping("update")
    @RequiresPermissions("news:update")
    @ControllerEndpoint(operation = "修改院内新闻", exceptionMessage = "修改院内新闻失败")
    public FebsResponse updateUser(@Valid NewsEntity newsEntity) {
        if (newsEntity.getId() == null) {
            throw new FebsException("院内新闻ID为空");
        }
        this.newsEntityService.updateNewsEntity(newsEntity);
        return new FebsResponse().success();
    }





    @GetMapping("delete/{idS}")
    @RequiresPermissions("news:delete")
    @ControllerEndpoint(operation = "删除院内新闻", exceptionMessage = "删除院内新闻失败")
    public FebsResponse deleteUsers(@NotBlank(message = "{required}") @PathVariable String idS) {
        String[] ids = idS.split(StringPool.COMMA);
        this.newsEntityService.deleteNewsEntity(ids);
        return new FebsResponse().success();
    }

}
