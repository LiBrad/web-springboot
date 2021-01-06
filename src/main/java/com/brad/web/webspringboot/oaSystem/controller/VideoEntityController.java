package com.brad.web.webspringboot.oaSystem.controller;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.brad.web.webspringboot.common.annotation.ControllerEndpoint;
import com.brad.web.webspringboot.common.controller.BaseController;
import com.brad.web.webspringboot.common.entity.FebsResponse;
import com.brad.web.webspringboot.common.entity.QueryRequest;
import com.brad.web.webspringboot.common.exception.FebsException;
import com.brad.web.webspringboot.oaSystem.bean.VideoBean;
import com.brad.web.webspringboot.oaSystem.entity.VideoEntity;
import com.brad.web.webspringboot.oaSystem.service.VideoEntityService;
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
 * 医院视频Contorller
 */
@Slf4j
@Validated  //@validated来校验数据，如果数据异常则会统一抛出异常，方便异常中心统一处理
@RestController
@RequiredArgsConstructor
@RequestMapping("video")
public class VideoEntityController   extends BaseController {

    private final VideoEntityService videoEntityService;



    @GetMapping("list")
    @RequiresPermissions("video:view")
    public FebsResponse videoList(VideoBean videoBean, QueryRequest request) {
        Map<String, Object> dataTable = getDataTable(this.videoEntityService.getVideoEntityList(videoBean, request));
        return new FebsResponse().success().data(dataTable);
    }

    @PostMapping
    @RequiresPermissions("video:add")
    @ControllerEndpoint(operation = "新增医院视频", exceptionMessage = "新增医院视频")
    public FebsResponse addVideo(@Valid VideoEntity entity) {
        this.videoEntityService.createVideoEntity(entity);
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
    @RequiresPermissions("video:update")
    @ControllerEndpoint(operation = "修改医院视频", exceptionMessage = "修改医院视频失败")
    public FebsResponse updateVideo(@Valid VideoEntity entity) {
        if (entity.getId() == null) {
            throw new FebsException("医院视频ID为空");
        }
        this.videoEntityService.updateVideoEntity(entity);
        return new FebsResponse().success();
    }

    @GetMapping("delete/{idS}")
    @RequiresPermissions("video:delete")
    @ControllerEndpoint(operation = "删除医院视频", exceptionMessage = "删除医院视频失败")
    public FebsResponse deleteVideo(@NotBlank(message = "{required}") @PathVariable String idS) {
        String[] ids = idS.split(StringPool.COMMA);
        this.videoEntityService.deleteVideoEntity(ids);
        return new FebsResponse().success();
    }
    

}
