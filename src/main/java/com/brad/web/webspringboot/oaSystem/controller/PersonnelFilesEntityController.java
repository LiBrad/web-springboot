package com.brad.web.webspringboot.oaSystem.controller;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.brad.web.webspringboot.common.annotation.ControllerEndpoint;
import com.brad.web.webspringboot.common.controller.BaseController;
import com.brad.web.webspringboot.common.entity.FebsResponse;
import com.brad.web.webspringboot.common.entity.QueryRequest;
import com.brad.web.webspringboot.common.exception.FebsException;
import com.brad.web.webspringboot.oaSystem.bean.PersonnelFilesBean;
import com.brad.web.webspringboot.oaSystem.entity.PersonnelFilesEntity;
import com.brad.web.webspringboot.oaSystem.service.PersonnelFilesEntityService;
import com.wuwenze.poi.ExcelKit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @author： 李逸野
 * @date： 2020-11-11
 *
 * 人事档案Controller
 */
@Slf4j
@Validated  //@validated来校验数据，如果数据异常则会统一抛出异常，方便异常中心统一处理
@RestController
@RequiredArgsConstructor
@RequestMapping("personnelFiles")
public class PersonnelFilesEntityController  extends BaseController {


    private final PersonnelFilesEntityService personnelFilesEntityService;

    @GetMapping("list")
    @RequiresPermissions("personnelFiles:view")
    public FebsResponse personnelFilesList(PersonnelFilesBean personnelFilesBean, QueryRequest request ) {
        Map<String, Object> dataTable = getDataTable(this.personnelFilesEntityService.getPersonnelFilesEntityList(personnelFilesBean, request));
        return new FebsResponse().success().data(dataTable);
    }

    @PostMapping
    @RequiresPermissions("personnelFiles:add")
    @ControllerEndpoint(operation = "新增人事档案", exceptionMessage = "新增人事档案")
    public FebsResponse addpersonnelFiles(@Valid PersonnelFilesEntity entity) {
        this.personnelFilesEntityService.createPersonnelFilesEntity(entity);
        return new FebsResponse().success();
    }

    @PostMapping("update")
    @RequiresPermissions("personnelFiles:update")
    @ControllerEndpoint(operation = "修改人事档案", exceptionMessage = "修改人事档案失败")
    public FebsResponse updatepersonnelFiles(@Valid PersonnelFilesEntity entity) {
        if (entity.getId() == null) {
            throw new FebsException("人事档案ID为空");
        }
        this.personnelFilesEntityService.updatePersonnelFilesEntity(entity);
        return new FebsResponse().success();
    }

    @GetMapping("delete/{idS}")
    @RequiresPermissions("personnelFiles:delete")
    @ControllerEndpoint(operation = "删除人事档案", exceptionMessage = "删除人事档案失败")
    public FebsResponse deletepersonnelFiles(@NotBlank(message = "{required}") @PathVariable String idS) {
        String[] ids = idS.split(StringPool.COMMA);
        this.personnelFilesEntityService.deletePersonnelFilesEntity(ids);
        return new FebsResponse().success();
    }

    @GetMapping("excel")
    @RequiresPermissions("personnelFiles:export")
    @ControllerEndpoint(exceptionMessage = "导出Excel失败")
    public void export(QueryRequest queryRequest, PersonnelFilesBean personnelFilesBean, HttpServletResponse response) {
        List<PersonnelFilesBean> personnelFilesBeans = this.personnelFilesEntityService.getPersonnelFilesEntityList(personnelFilesBean, queryRequest).getRecords();
        ExcelKit.$Export(PersonnelFilesBean.class, response).downXlsx(personnelFilesBeans, false);
    }

}
