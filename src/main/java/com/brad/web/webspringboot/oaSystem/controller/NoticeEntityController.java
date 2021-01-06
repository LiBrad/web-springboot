package com.brad.web.webspringboot.oaSystem.controller;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.brad.web.webspringboot.common.annotation.ControllerEndpoint;
import com.brad.web.webspringboot.common.controller.BaseController;
import com.brad.web.webspringboot.common.entity.FebsResponse;
import com.brad.web.webspringboot.common.entity.QueryRequest;
import com.brad.web.webspringboot.common.exception.FebsException;
import com.brad.web.webspringboot.oaSystem.bean.NoticeBean;
import com.brad.web.webspringboot.oaSystem.entity.NoticeEntity;
import com.brad.web.webspringboot.oaSystem.service.NoticeEntityService;
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
 * @date： 2020-11-04
 * 通知公告Controller
 */
@Slf4j
@Validated  //@validated来校验数据，如果数据异常则会统一抛出异常，方便异常中心统一处理
@RestController
@RequiredArgsConstructor
@RequestMapping("notice")
public class NoticeEntityController  extends BaseController {

    private final NoticeEntityService noticeEntityService;



    @GetMapping("list")
    @RequiresPermissions("notice:view")
    public FebsResponse noticeList(NoticeBean noticeBean, QueryRequest request) {
        Map<String, Object> dataTable = getDataTable(this.noticeEntityService.getNoticeEntityList(noticeBean, request));
        return new FebsResponse().success().data(dataTable);
    }

    @PostMapping
    @RequiresPermissions("notice:add")
    @ControllerEndpoint(operation = "新增通知公告", exceptionMessage = "新增通知公告")
    public FebsResponse addNews(@Valid NoticeEntity entity) {
        this.noticeEntityService.createNoticeEntity(entity);
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
    @RequiresPermissions("notice:update")
    @ControllerEndpoint(operation = "修改通知公告", exceptionMessage = "修改通知公告失败")
    public FebsResponse updateUser(@Valid NoticeEntity entity) {
        if (entity.getId() == null) {
            throw new FebsException("通知公告ID为空");
        }
        this.noticeEntityService.updateNoticeEntity(entity);
        return new FebsResponse().success();
    }

    @GetMapping("delete/{idS}")
    @RequiresPermissions("notice:delete")
    @ControllerEndpoint(operation = "删除通知公告", exceptionMessage = "删除通知公告失败")
    public FebsResponse deleteUsers(@NotBlank(message = "{required}") @PathVariable String idS) {
        String[] ids = idS.split(StringPool.COMMA);
        this.noticeEntityService.deleteNoticeEntity(ids);
        return new FebsResponse().success();
    }

}
