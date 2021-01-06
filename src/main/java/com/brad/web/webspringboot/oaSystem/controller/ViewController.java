package com.brad.web.webspringboot.oaSystem.controller;

import com.brad.web.webspringboot.common.controller.BaseController;
import com.brad.web.webspringboot.common.entity.FebsConstant;
import com.brad.web.webspringboot.common.utils.FebsUtil;
import com.brad.web.webspringboot.oaSystem.bean.*;
import com.brad.web.webspringboot.oaSystem.entity.InsideMailEntity;
import com.brad.web.webspringboot.oaSystem.service.*;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author MrBird
 */
@Controller("osSystemView")
@RequiredArgsConstructor
public class ViewController extends BaseController {

    private final NewsEntityService newsEntityService;//院内新闻服务

    private final NoticeEntityService noticeEntityService; //通知公告服务

    private final VideoEntityService videoEntityService; //医院视频服务

    private final OnlineLearningEntityService onlineLearningEntityService;//在线学习中心服务

    private final PersonnelFilesEntityService personnelFilesEntityService;//人事档案服务

    private final InsideMailEntityService insideMailEntityService; //内部邮件服务

    private final GeneralApprovalEntityService generalApprovalEntityService;//通用审批

    //news 院内新闻开始
    @GetMapping(FebsConstant.VIEW_PREFIX + "oaPortalManagement/news")
    @RequiresPermissions("news:view")
    public String osSystemViewNews() {
        return FebsUtil.view("oaPortalManagement/news/news");
    }



    @GetMapping(FebsConstant.VIEW_PREFIX + "oaPortalManagement/news/add")
    @RequiresPermissions("news:add")
    public String osSystemNewsAdd() {
        return FebsUtil.view("oaPortalManagement/news/newsAdd");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "oaPortalManagement/news/update/{newsId}")
    @RequiresPermissions("news:update")
    public String osSystemNewsUpdate(@NotBlank(message = "{required}") @PathVariable String newsId, Model model) {
//        resolveUserModel(username, model, false);
        NewsBean newsBean = new NewsBean();
        newsBean.setId(newsId);

        List<NewsBean> newsEntityList = newsEntityService.getNewsEntityByList(newsBean);
        NewsBean newsBean1  = newsEntityList.get(0);
        model.addAttribute("newsBean", newsBean1);

        return FebsUtil.view("oaPortalManagement/news/newsUpdate");
    }
    //news 院内新闻结束


    //notice 院内新闻开始
    @GetMapping(FebsConstant.VIEW_PREFIX + "oaPortalManagement/notice")
    @RequiresPermissions("notice:view")
    public String osSystemViewNotice() {
        return FebsUtil.view("oaPortalManagement/notice/notice");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "oaPortalManagement/notice/add")
    @RequiresPermissions("notice:add")
    public String osSystemNoticeAdd() {
        return FebsUtil.view("oaPortalManagement/notice/noticeAdd");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "oaPortalManagement/notice/update/{noticeId}")
    @RequiresPermissions("notice:update")
    public String osSystemNewsNotice(@NotBlank(message = "{required}") @PathVariable String noticeId, Model model) {
//        resolveUserModel(username, model, false);
        NoticeBean noticeBean = new NoticeBean();
        noticeBean.setId(noticeId);

        List<NoticeBean> noticeEntityByList = noticeEntityService.getNoticeEntityByList(noticeBean);
        NoticeBean noticeBean1 = noticeEntityByList.get(0);
        model.addAttribute("noticeBean", noticeBean1);

        return FebsUtil.view("oaPortalManagement/notice/noticeUpdate");
    }
    //notice 院内新闻结束


    //video 医院视频开始
    @GetMapping(FebsConstant.VIEW_PREFIX + "oaPortalManagement/video")
    @RequiresPermissions("video:view")
    public String osSystemViewVideo() {
        return FebsUtil.view("oaPortalManagement/video/video");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "oaPortalManagement/video/add")
    @RequiresPermissions("video:add")
    public String osSystemVideoAdd() {
        return FebsUtil.view("oaPortalManagement/video/videoAdd");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "oaPortalManagement/video/update/{videoId}")
    @RequiresPermissions("video:update")
    public String osSystemNewsVideo(@NotBlank(message = "{required}") @PathVariable String videoId, Model model) {
//        resolveUserModel(username, model, false);
        VideoBean videoBean = new VideoBean();
        videoBean.setId(videoId);

        List<VideoBean> videoEntityByList = videoEntityService.getVideoEntityByList(videoBean);
        VideoBean videoBean1 = videoEntityByList.get(0);
        model.addAttribute("videoBean", videoBean1);

        return FebsUtil.view("oaPortalManagement/video/videoUpdate");
    }
    //video 医院视频结束

    //OnlineLearning在线学习中心开始
    @GetMapping(FebsConstant.VIEW_PREFIX + "oaPortalManagement/onlineLearning")
    @RequiresPermissions("onlineLearning:view")
    public String osSystemViewOnlineLearning() {
        return FebsUtil.view("oaPortalManagement/onlineLearning/onlineLearning");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "oaPortalManagement/onlineLearning/add")
    @RequiresPermissions("onlineLearning:add")
    public String osSystemOnlineLearningAdd() {
        return FebsUtil.view("oaPortalManagement/onlineLearning/onlineLearningAdd");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "oaPortalManagement/onlineLearning/update/{onlineLearningId}")
    @RequiresPermissions("onlineLearning:update")
    public String osSystemNewsOnlineLearning(@NotBlank(message = "{required}") @PathVariable String onlineLearningId, Model model) {
//        resolveUserModel(username, model, false);
        OnlineLearningBean onlineLearningBean = new OnlineLearningBean();
        onlineLearningBean.setId(onlineLearningId);

        List<OnlineLearningBean> onlineLearningEntityByList = onlineLearningEntityService.getOnlineLearningEntityByList(onlineLearningBean);
        OnlineLearningBean onlineLearningBean1 = onlineLearningEntityByList.get(0);
        model.addAttribute("onlineLearningBean", onlineLearningBean1);

        return FebsUtil.view("oaPortalManagement/onlineLearning/onlineLearningUpdate");
    }


    @GetMapping(FebsConstant.VIEW_PREFIX + "oaPortalManagement/onlineLearningPublic")
    @RequiresPermissions("onlineLearningPublic:view")
    public String osSystemViewonlineLearningPublic() {
        return FebsUtil.view("oaPortalManagement/onlineLearning/onlineLearningPublic");
    }
    @GetMapping(FebsConstant.VIEW_PREFIX + "oaPortalManagement/onlineLearningPublic/see/{onlineLearningId}")
    @RequiresPermissions("onlineLearningPublic:see")
    public String osSystemViewonlineLearningPublicSee(@NotBlank(message = "{required}") @PathVariable String onlineLearningId, Model model) {
        OnlineLearningBean onlineLearningBean = new OnlineLearningBean();
        onlineLearningBean.setId(onlineLearningId);

        List<OnlineLearningBean> onlineLearningEntityByList = onlineLearningEntityService.getOnlineLearningEntityByList(onlineLearningBean);
        OnlineLearningBean onlineLearningBean1 = onlineLearningEntityByList.get(0);
        model.addAttribute("onlineLearningBean", onlineLearningBean1);

        return FebsUtil.view("oaPortalManagement/onlineLearning/onlineLearningPublicSee");
    }
    //OnlineLearning在线学习中心结束



    //personnelFiles 人事档案开始
    @GetMapping(FebsConstant.VIEW_PREFIX + "oaPortalManagement/personnelFiles")
    @RequiresPermissions("personnelFiles:view")
    public String osSystemViewPersonnelFiles() {
        return FebsUtil.view("oaPortalManagement/personnelFiles/personnelFiles");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "oaPortalManagement/personnelFiles/add")
    @RequiresPermissions("personnelFiles:add")
    public String osSystemPersonnelFilesAdd() {
        return FebsUtil.view("oaPortalManagement/personnelFiles/personnelFilesAdd");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "oaPortalManagement/personnelFiles/update/{personnelFilesId}")
    @RequiresPermissions("personnelFiles:update")
    public String osSystemNewsPersonnelFiles(@NotBlank(message = "{required}") @PathVariable String personnelFilesId, Model model) {
//        resolveUserModel(username, model, false);
        PersonnelFilesBean personnelFilesBean = new PersonnelFilesBean();
        personnelFilesBean.setId(personnelFilesId);

        List<PersonnelFilesBean> personnelFilesEntityByList = personnelFilesEntityService.getPersonnelFilesEntityByList(personnelFilesBean);
        PersonnelFilesBean personnelFilesBean1 = personnelFilesEntityByList.get(0);
        model.addAttribute("personnelFilesBean", personnelFilesBean1);

        return FebsUtil.view("oaPortalManagement/personnelFiles/personnelFilesUpdate");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "oaPortalManagement/personnelFiles/print/{personnelFilesId}")
    @RequiresPermissions("personnelFiles:print")
    public String osSystemNewsPersonnelFilesPrint(@NotBlank(message = "{required}") @PathVariable String personnelFilesId, Model model) {
//        resolveUserModel(username, model, false);
        PersonnelFilesBean personnelFilesBean = new PersonnelFilesBean();
        personnelFilesBean.setId(personnelFilesId);

        List<PersonnelFilesBean> personnelFilesEntityByList = personnelFilesEntityService.getPersonnelFilesEntityByList(personnelFilesBean);
        PersonnelFilesBean personnelFilesBean1 = personnelFilesEntityByList.get(0);
        model.addAttribute("personnelFilesBean", personnelFilesBean1);

        return FebsUtil.view("oaPortalManagement/personnelFiles/printPersonnelFiles");
    }


    //personnelFiles 人事档案结束


    //insideMail 内部邮件开始
    @GetMapping(FebsConstant.VIEW_PREFIX + "oaPortalManagement/insideMail")
    @RequiresPermissions("insideMail:view")
    public String osSystemViewInsideMail() {
        return FebsUtil.view("oaPortalManagement/insideMail/insideMail");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "oaPortalManagement/insideMail/add")
    @RequiresPermissions("insideMail:add")
    public String osSystemInsideMailAdd() {
        return FebsUtil.view("oaPortalManagement/insideMail/insideMailAdd");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "oaPortalManagement/insideMail/update/{insideMailId}")
    @RequiresPermissions("insideMail:update")
    public String osSystemInsideMail(@NotBlank(message = "{required}") @PathVariable String insideMailId, Model model) {
//        resolveUserModel(username, model, false);
        InsideMailBean insideMailBean = new InsideMailBean();
        insideMailBean.setId(insideMailId);

        List<InsideMailBean> insideMailEntityByList = insideMailEntityService.getInsideMailEntityByList(insideMailBean);
        InsideMailBean insideMailBean1 = insideMailEntityByList.get(0);
        model.addAttribute("insideMailBean", insideMailBean1);

        return FebsUtil.view("oaPortalManagement/insideMail/insideMailUpdate");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "oaPortalManagement/insideMail/see/{insideMailId}")
    @RequiresPermissions("insideMail:see")
    public String osSystemViewInsideMailSee(@NotBlank(message = "{required}") @PathVariable String insideMailId, Model model) {
        InsideMailBean insideMailBean = new InsideMailBean();
        insideMailBean.setId(insideMailId);

        List<InsideMailBean> insideMailEntityByList = insideMailEntityService.getInsideMailEntityByList(insideMailBean);
        InsideMailBean insideMailBean1 = insideMailEntityByList.get(0);
        model.addAttribute("insideMailBean", insideMailBean1);

        //标记已读
        if("0".equals(insideMailEntityByList.get(0).getInsideMailToRead())){
            InsideMailEntity insideMailEntity = new InsideMailEntity();
            insideMailEntity.setId(insideMailEntityByList.get(0).getId());
            insideMailEntity.setInsideMailToRead("1");
            insideMailEntityService.updateInsideMailEntity(insideMailEntity);
        }


        return FebsUtil.view("oaPortalManagement/insideMail/insideMailSee");
    }

    //insideMail 内部邮件结束


    //generalApproval 通用审批开始
    @GetMapping(FebsConstant.VIEW_PREFIX + "oaPortalManagement/generalApproval")
    @RequiresPermissions("generalApproval:view")
    public String osSystemViewGeneralApproval() {
        return FebsUtil.view("oaPortalManagement/generalApproval/generalApproval");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "oaPortalManagement/generalApproval/add")
    @RequiresPermissions("generalApproval:add")
    public String osSystemGeneralApprovalAdd() {
        return FebsUtil.view("oaPortalManagement/generalApproval/generalApprovalAdd");
    }


    @GetMapping(FebsConstant.VIEW_PREFIX + "oaPortalManagement/generalApproval/see/{generalApprovalId}")
    @RequiresPermissions("generalApprovalSee:see")
    public String osSystemGeneralApprovalSee(@NotBlank(message = "{required}") @PathVariable String generalApprovalId, Model model) {
//        resolveUserModel(username, model, false);
        GeneralApprovalBean generalApprovalBean = new GeneralApprovalBean();
        generalApprovalBean.setId(generalApprovalId);

        List<GeneralApprovalBean> generalApprovalEntityByList = generalApprovalEntityService.getGeneralApprovalEntityByList(generalApprovalBean);
        GeneralApprovalBean generalApprovalBean1 = generalApprovalEntityByList.get(0);
        model.addAttribute("generalApprovalBean", generalApprovalBean1);

        return FebsUtil.view("oaPortalManagement/generalApproval/generalApprovalSee");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "oaPortalManagement/generalApprovalWaitingForMe/see/{generalApprovalId}")
    @RequiresPermissions("generalApprovalWaitingForMe:see")
    public String osSystemGeneralApprovalWaitingForMe(@NotBlank(message = "{required}") @PathVariable String generalApprovalId, Model model) {
//        resolveUserModel(username, model, false);
        GeneralApprovalBean generalApprovalBean = new GeneralApprovalBean();
        generalApprovalBean.setId(generalApprovalId);

        List<GeneralApprovalBean> generalApprovalEntityByList = generalApprovalEntityService.getGeneralApprovalEntityByList(generalApprovalBean);
        GeneralApprovalBean generalApprovalBean1 = generalApprovalEntityByList.get(0);
        model.addAttribute("generalApprovalBean", generalApprovalBean1);

        return FebsUtil.view("oaPortalManagement/generalApproval/generalApprovalWaitingForMeSee");
    }


    @GetMapping(FebsConstant.VIEW_PREFIX + "oaPortalManagement/generalApprovalWaitingForMeUpdate/update/{generalApprovalId}")
    @RequiresPermissions("generalApprovalWaitingForMe:update")
    public String osSystemGeneralApprovalWaitingForMeUpdate(@NotBlank(message = "{required}") @PathVariable String generalApprovalId, Model model) {
//        resolveUserModel(username, model, false);
        GeneralApprovalBean generalApprovalBean = new GeneralApprovalBean();
        generalApprovalBean.setId(generalApprovalId);

        List<GeneralApprovalBean> generalApprovalEntityByList = generalApprovalEntityService.getGeneralApprovalEntityByList(generalApprovalBean);
        GeneralApprovalBean generalApprovalBean1 = generalApprovalEntityByList.get(0);
        model.addAttribute("generalApprovalBean", generalApprovalBean1);

        return FebsUtil.view("oaPortalManagement/generalApproval/generalApprovalWaitingForMeUpdate");
    }

    //待我审批
    @GetMapping(FebsConstant.VIEW_PREFIX + "oaPortalManagement/generalApprovalWaitingForMe")
    @RequiresPermissions("generalApprovalWaitingForMe:view")
    public String osSystemViewGeneralApprovalWaitingForMe() {
        return FebsUtil.view("oaPortalManagement/generalApproval/generalApprovalWaitingForMe");
    }

    //审批记录
    @GetMapping(FebsConstant.VIEW_PREFIX + "oaPortalManagement/generalApprovalRecord")
    @RequiresPermissions("generalApprovalRecord:view")
    public String osSystemViewGeneralApprovalRecord() {
        return FebsUtil.view("oaPortalManagement/generalApproval/generalApprovalRecord");
    }

    //generalApproval 通用审批结束









}
