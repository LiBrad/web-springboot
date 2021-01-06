package com.brad.web.webspringboot.oaSystem.controller;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.brad.web.webspringboot.common.annotation.ControllerEndpoint;
import com.brad.web.webspringboot.common.controller.BaseController;
import com.brad.web.webspringboot.common.entity.FebsResponse;
import com.brad.web.webspringboot.common.entity.QueryRequest;
import com.brad.web.webspringboot.common.exception.FebsException;
import com.brad.web.webspringboot.oaSystem.bean.OnlineLearningBean;
import com.brad.web.webspringboot.oaSystem.entity.OnlineLearningEntity;
import com.brad.web.webspringboot.oaSystem.service.OnlineLearningEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Map;

/**
 * @version 1.0
 * @author： 李逸野
 * @date： 2020-11-06
 *
 * 在线学习中心Controller
 */

@Slf4j
@Validated  //@validated来校验数据，如果数据异常则会统一抛出异常，方便异常中心统一处理
@RestController
@RequiredArgsConstructor
@RequestMapping("onlineLearning")
public class OnlineLearningEntityController  extends BaseController {


    private final OnlineLearningEntityService onlineLearningEntityService;

    @GetMapping("list")
    @RequiresPermissions("onlineLearning:view")
    public FebsResponse onlineLearningList(OnlineLearningBean onlineLearningBean, QueryRequest request ,@RequestParam(value = "userId", required = false) String userId) {
        onlineLearningBean.setOlUserId(userId);
        Map<String, Object> dataTable = getDataTable(this.onlineLearningEntityService.getOnlineLearningEntityList(onlineLearningBean, request));
        return new FebsResponse().success().data(dataTable);
    }

    @PostMapping
    @RequiresPermissions("onlineLearning:add")
    @ControllerEndpoint(operation = "新增在线学习中心", exceptionMessage = "新增在线学习中心")
    public FebsResponse addOnlineLearning(@Valid OnlineLearningEntity entity) {
        this.onlineLearningEntityService.createOnlineLearningEntity(entity);
        return new FebsResponse().success();
    }

//    @GetMapping("delete/{userIds}")
//    @RequiresPermissions("Video:delete")
//    @ControllerEndpoint(operation = "删除院内新闻", exceptionMessage = "删除院内新闻失败")
//    public FebsResponse deleteUsers(@NotBlank(message = "{required}") @PathVariable String userIds) {
//        String[] ids = userIds.split(StringPool.COMMA);
//        this.VideoEntityService.deleteUsers(ids);
//        return new FebsResponse().success();
//    }

    @PostMapping("update")
    @RequiresPermissions("onlineLearning:update")
    @ControllerEndpoint(operation = "修改在线学习中心", exceptionMessage = "修改在线学习中心失败")
    public FebsResponse updateOnlineLearning(@Valid OnlineLearningEntity entity) {
        if (entity.getId() == null) {
            throw new FebsException("在线学习中心ID为空");
        }
        this.onlineLearningEntityService.updateOnlineLearningEntity(entity);
        return new FebsResponse().success();
    }

    @GetMapping("delete/{idS}")
    @RequiresPermissions("onlineLearning:delete")
    @ControllerEndpoint(operation = "删除在线学习中心", exceptionMessage = "删除在线学习中心失败")
    public FebsResponse deleteOnlineLearning(@NotBlank(message = "{required}") @PathVariable String idS) {
        String[] ids = idS.split(StringPool.COMMA);
        this.onlineLearningEntityService.deleteOnlineLearningEntity(ids);
        return new FebsResponse().success();
    }


}
