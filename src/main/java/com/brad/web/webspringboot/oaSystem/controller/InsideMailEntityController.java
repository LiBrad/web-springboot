package com.brad.web.webspringboot.oaSystem.controller;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.brad.web.webspringboot.common.annotation.ControllerEndpoint;
import com.brad.web.webspringboot.common.controller.BaseController;
import com.brad.web.webspringboot.common.entity.FebsResponse;
import com.brad.web.webspringboot.common.entity.QueryRequest;
import com.brad.web.webspringboot.common.exception.FebsException;
import com.brad.web.webspringboot.oaSystem.bean.InsideMailBean;
import com.brad.web.webspringboot.oaSystem.entity.InsideMailEntity;
import com.brad.web.webspringboot.oaSystem.service.InsideMailEntityService;
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
 * @date： 2020-11-13
 * 内部邮件Controller
 */
@Slf4j
@Validated  //@validated来校验数据，如果数据异常则会统一抛出异常，方便异常中心统一处理
@RestController
@RequiredArgsConstructor
@RequestMapping("insideMail")
public class InsideMailEntityController extends BaseController {


    private final InsideMailEntityService insideMailEntityService;



    @GetMapping("list")
    @RequiresPermissions("insideMail:view")
    public FebsResponse userList(InsideMailBean insideMailBean, QueryRequest request) {
        Map<String, Object> dataTable = getDataTable(this.insideMailEntityService.getInsideMailEntityList(insideMailBean, request));
        return new FebsResponse().success().data(dataTable);
    }

    @PostMapping
    @RequiresPermissions("insideMail:add")
    @ControllerEndpoint(operation = "发送内部邮件", exceptionMessage = "发送内部邮件失败")
    public FebsResponse addinsideMail(InsideMailBean insideMailBean) {
        this.insideMailEntityService.createInsideMailEntity(insideMailBean);
        return new FebsResponse().success();
    }


    @PostMapping("update")
    @RequiresPermissions("insideMail:update")
    @ControllerEndpoint(operation = "回复内部邮件", exceptionMessage = "回复内部邮件失败")
    public FebsResponse updateUser(@Valid InsideMailEntity insideMailEntity) {
        if (insideMailEntity.getId() == null) {
            throw new FebsException("内部邮件ID为空");
        }
        this.insideMailEntityService.updateInsideMailEntity(insideMailEntity);
        return new FebsResponse().success();
    }





    @GetMapping("delete/{idS}")
    @RequiresPermissions("insideMail:delete")
    @ControllerEndpoint(operation = "删除内部邮件", exceptionMessage = "删除内部邮件失败")
    public FebsResponse deleteUsers(@NotBlank(message = "{required}") @PathVariable String idS) {
        String[] ids = idS.split(StringPool.COMMA);
        this.insideMailEntityService.deleteInsideMailEntity(ids);
        return new FebsResponse().success();
    }


}
