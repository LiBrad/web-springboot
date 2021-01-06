package com.brad.web.webspringboot.oaSystem.controller;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.brad.web.webspringboot.common.annotation.ControllerEndpoint;
import com.brad.web.webspringboot.common.controller.BaseController;
import com.brad.web.webspringboot.common.entity.FebsResponse;
import com.brad.web.webspringboot.common.entity.QueryRequest;
import com.brad.web.webspringboot.common.exception.FebsException;
import com.brad.web.webspringboot.oaSystem.bean.GeneralApprovalBean;
import com.brad.web.webspringboot.oaSystem.entity.GeneralApprovalEntity;
import com.brad.web.webspringboot.oaSystem.service.GeneralApprovalEntityService;
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
 * 通用审批Controller
 */
@Slf4j
@Validated  //@validated来校验数据，如果数据异常则会统一抛出异常，方便异常中心统一处理
@RestController
@RequiredArgsConstructor
@RequestMapping("generalApproval")
public class GeneralApprovalEntityController extends BaseController {


    private final GeneralApprovalEntityService generalApprovalEntityService;


    @GetMapping("list")
    @RequiresPermissions("generalApproval:view")
    public FebsResponse userList(GeneralApprovalBean generalApprovalBean, QueryRequest request) {
        Map<String, Object> dataTable = getDataTable(this.generalApprovalEntityService.getGeneralApprovalEntityList(generalApprovalBean, request));
        return new FebsResponse().success().data(dataTable);
    }

    @PostMapping
    @RequiresPermissions("generalApproval:add")
    @ControllerEndpoint(operation = "发送通用审批申请", exceptionMessage = "发送通用审批申请失败")
    public FebsResponse addgeneralApproval(GeneralApprovalEntity generalApprovalEntity) {
        this.generalApprovalEntityService.createGeneralApprovalEntity(generalApprovalEntity);
        return new FebsResponse().success();
    }


    @PostMapping("update")
    @RequiresPermissions("generalApprovalWaitingForMe:update")
    @ControllerEndpoint(operation = "审批-通用审批申请", exceptionMessage = "审批-通用审批申请")
    public FebsResponse updateUser(@Valid GeneralApprovalEntity generalApprovalEntity) {
        if (generalApprovalEntity.getId() == null) {
            throw new FebsException("通用审批ID为空");
        }
        this.generalApprovalEntityService.updateGeneralApprovalEntity(generalApprovalEntity);
        return new FebsResponse().success();
    }





    @GetMapping("delete/{idS}")
    @RequiresPermissions("generalApproval:delete")
    @ControllerEndpoint(operation = "删除通用审批", exceptionMessage = "删除通用审批失败")
    public FebsResponse deleteUsers(@NotBlank(message = "{required}") @PathVariable String idS) {
        String[] ids = idS.split(StringPool.COMMA);
        this.generalApprovalEntityService.deleteGeneralApprovalEntity(ids);
        return new FebsResponse().success();
    }


}
