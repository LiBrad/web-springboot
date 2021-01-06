package com.brad.web.webspringboot.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.brad.web.webspringboot.aop.UVlog;
import com.brad.web.webspringboot.common.controller.BaseController;
import com.brad.web.webspringboot.common.entity.FebsConstant;
import com.brad.web.webspringboot.common.entity.QueryRequest;
import com.brad.web.webspringboot.common.utils.DateUtil;
import com.brad.web.webspringboot.common.utils.FebsUtil;
import com.brad.web.webspringboot.common.utils.RedisUtil;
import com.brad.web.webspringboot.oaSystem.bean.NewsBean;
import com.brad.web.webspringboot.oaSystem.bean.NoticeBean;
import com.brad.web.webspringboot.oaSystem.bean.OnlineLearningBean;
import com.brad.web.webspringboot.oaSystem.bean.VideoBean;
import com.brad.web.webspringboot.oaSystem.service.NewsEntityService;
import com.brad.web.webspringboot.oaSystem.service.NoticeEntityService;
import com.brad.web.webspringboot.oaSystem.service.OnlineLearningEntityService;
import com.brad.web.webspringboot.oaSystem.service.VideoEntityService;
import com.brad.web.webspringboot.system.entity.Role;
import com.brad.web.webspringboot.system.entity.User;
import com.brad.web.webspringboot.system.service.IRoleService;
import com.brad.web.webspringboot.system.service.IUserDataPermissionService;
import com.brad.web.webspringboot.system.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.ExpiredSessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

/**
 * @author MrBird
 */
@Controller("systemView")
@RequiredArgsConstructor
public class ViewController extends BaseController {

    private final IUserService userService; //用户服务
    private final IRoleService roleService; //角色服务
    private final IUserDataPermissionService userDataPermissionService;
    @Autowired
    private RedisUtil redisUtil;

    private final NewsEntityService newsEntityService;//院内新闻服务
    private final NoticeEntityService noticeEntityService; //通知公告服务
    private final VideoEntityService videoEntityService; //医院视频服务
    private final OnlineLearningEntityService onlineLearningEntityService;//在线学习中心服务


    public static final String GET_NEW_ENTITY_OA_COMMON_LIST =  "/newsshow";// 查询院内新闻（AO门户信息公共区域）(详情)
    public static final String GET_NOTICE_ENTITY_OA_COMMON_LIST = "/noticeshow";// 查询通知公告表（AO门户信息公共区域）(详情)
    public static final String GET_ONLINE_LEARNING_OA_COMMON_ENTITY_LIST = "/onlineLearningshow";// 查询在线学习中心（AO门户信息公共区域）
    public static final String GET_VIDEO_ENTITY_OA_COMMON_LIST = "/videolistshow";// 查询医院视频（AO门户信息公共区域）(详情)

    @GetMapping("login")
    @ResponseBody
    public Object login(HttpServletRequest request) {
        if (FebsUtil.isAjaxRequest(request)) {
            throw new ExpiredSessionException();
        } else {
            ModelAndView mav = new ModelAndView();
            mav.setViewName(FebsUtil.view("login"));
            return mav;
        }
    }

    @GetMapping("indexOA")
    @ResponseBody
    public Object indexOA(HttpServletRequest request, Model model) {
        String flag = request.getParameter("flag");

        ModelAndView mav = new ModelAndView();

        NewsBean newsBean1 = new NewsBean();
        newsBean1.setNewsDisplayStatus("1");    //显示状态(1显示、0不显示)

        IPage<NewsBean> newsEntityList = this.newsEntityService.getNewsEntityListNewsRecommendedType(newsBean1, new QueryRequest());//院内新闻服务
        List<NewsBean> newsBean = newsEntityList.getRecords();



        newsBean1.setNewsRecommendedImageType("1"); //推荐类型-图片新闻（1.开启，0关闭）
        newsBean1.setNewsRecommendedType("0");

        IPage<NewsBean> newsEntityListImage = this.newsEntityService.getNewsEntityListPage(newsBean1, new QueryRequest());//院内新闻服务-推荐类型-图片新闻
        List<NewsBean> newsBeanImage = newsEntityListImage.getRecords();


        IPage<NoticeBean> noticeEntityList = this.noticeEntityService.getNoticeEntityList(new NoticeBean(), new QueryRequest());//通知公告服务
        List<NoticeBean> noticeBean = noticeEntityList.getRecords();


        IPage<VideoBean> videoEntityList = this.videoEntityService.getVideoEntityList(new VideoBean(), new QueryRequest());//医院视频服务
        List<VideoBean> videoBean = videoEntityList.getRecords();

        IPage<OnlineLearningBean> onlineLearningEntityList = this.onlineLearningEntityService.getOnlineLearningEntityList(new OnlineLearningBean(), new QueryRequest());//在线学习中心服务
        List<OnlineLearningBean> onlineLearningBean = onlineLearningEntityList.getRecords();





        model.addAttribute("newsBean", newsBean);
        model.addAttribute("noticeBean", noticeBean);
        model.addAttribute("videoBean", videoBean);
        model.addAttribute("onlineLearningBean", onlineLearningBean);
        model.addAttribute("newsBeanImage", newsBeanImage);
        model.addAttribute("flag",flag);
        mav.setViewName(FebsUtil.view("indexOA"));


        return mav;
//        if (FebsUtil.isAjaxRequest(request)) {
//            throw new ExpiredSessionException();
//        } else {
//        }
    }




    //门户页面跳转
//    @GetMapping(FebsConstant.VIEW_PREFIX + "indexOA")
//    @ResponseBody
//    public Object indexOAL(HttpServletRequest request) {
//        ModelAndView mav = new ModelAndView();
//        mav.setViewName(FebsUtil.view("indexOA"));
//        return mav;
//    }

    @GetMapping("newslist")
    @ResponseBody
    public Object newslist(Model model,NewsBean newsBean, QueryRequest request) {
        ModelAndView mav = new ModelAndView();
        newsBean.setNewsDisplayStatus("1");    //显示状态(1显示、0不显示)

        Map<String, Object> dataTable = getDataTable(this.newsEntityService.getNewsEntityListNewsRecommendedType(newsBean, request));


        model.addAttribute("dataTable", dataTable);


        mav.setViewName(FebsUtil.view("newslist"));
        return mav;
    }


    @GetMapping("noticelist")
    @ResponseBody
    public Object noticelist(Model model,NoticeBean noticeBean, QueryRequest request) {
        ModelAndView mav = new ModelAndView();

        Map<String, Object> dataTable = getDataTable(this.noticeEntityService.getNoticeEntityList(noticeBean, request));
        model.addAttribute("dataTable", dataTable);

        mav.setViewName(FebsUtil.view("noticelist"));
        return mav;
    }

    @GetMapping("onlineLearninglist")
    @ResponseBody
    public Object onlineLearninglist(Model model,OnlineLearningBean onlineLearningBean, QueryRequest request ,@RequestParam(value = "userId", required = false) String userId) {
        ModelAndView mav = new ModelAndView();
        onlineLearningBean.setOlUserId(userId);
        Map<String, Object> dataTable = getDataTable(this.onlineLearningEntityService.getOnlineLearningEntityList(onlineLearningBean, request));
        model.addAttribute("dataTable", dataTable);

        mav.setViewName(FebsUtil.view("onlineLearninglist"));
        return mav;
    }

    @GetMapping("videolist")
    @ResponseBody
    public Object videolist(Model model,VideoBean videoBean, QueryRequest request) {
        ModelAndView mav = new ModelAndView();
        Map<String, Object> dataTable = getDataTable(this.videoEntityService.getVideoEntityList(videoBean, request));
        model.addAttribute("dataTable", dataTable);
        mav.setViewName(FebsUtil.view("videolist"));
        return mav;
    }















    @UVlog//记录点击次数
    @GetMapping("newsshow")
    @ResponseBody
    public  Object newsshow(HttpServletRequest request,Model model,NewsBean newsBean) {
        ModelAndView mav = new ModelAndView();
        IPage<NewsBean> newsEntityList = this.newsEntityService.getNewsEntityList(newsBean, new QueryRequest());
        List<NewsBean> records = newsEntityList.getRecords();
        model.addAttribute("newsBeanShow", records.get(0));



//        //将点击数写入数据库
//        NewsEntity newsEntity = new NewsEntity();
//        newsEntity.setId(records.get(0).getId());
//        newsEntity.setNewsNumberVisits((int) (views+records.get(0).getNewsNumberVisits()));
//        newsEntity.setNewsRecommendedType("1".equals(records.get(0).getNewsRecommendedType())?"on":"");
//        newsEntity.setNewsRecommendedImageType("1".equals(records.get(0).getNewsRecommendedImageType())?"on":"");
//        newsEntityService.updateNewsEntity(newsEntity);
////        redisUtil.del(key);

        mav.setViewName(FebsUtil.view("newsshow"));



        return mav;
    }


    @UVlog//记录点击次数
    @GetMapping("noticeshow")
    @ResponseBody
    public Object noticeshow(HttpServletRequest request,Model model,NoticeBean noticeBean) {
        ModelAndView mav = new ModelAndView();
        IPage<NoticeBean> noticeEntityList = this.noticeEntityService.getNoticeEntityList(noticeBean, new QueryRequest());
        List<NoticeBean> records = noticeEntityList.getRecords();
        model.addAttribute("noticeBeanShow", records.get(0));
        mav.setViewName(FebsUtil.view("noticeshow"));

        return mav;
    }

    @UVlog//记录点击次数
    @GetMapping("onlineLearningshow")
    @ResponseBody
    public Object onlineLearningshow(HttpServletRequest request,Model model,OnlineLearningBean onlineLearningBean) {
        ModelAndView mav = new ModelAndView();
        IPage<OnlineLearningBean> onlineLearningEntityList = this.onlineLearningEntityService.getOnlineLearningEntityList(onlineLearningBean, new QueryRequest());//在线学习中心服务
        List<OnlineLearningBean> onlineLearningBeanShow = onlineLearningEntityList.getRecords();
        model.addAttribute("onlineLearningBeanShow", onlineLearningBeanShow.get(0));
        mav.setViewName(FebsUtil.view("onlineLearningshow"));



        return mav;
    }


    @UVlog//记录点击次数
    @GetMapping("videolistshow")
    @ResponseBody
    public Object videolistshow(HttpServletRequest request,Model model,VideoBean videoBean) {
        ModelAndView mav = new ModelAndView();
        IPage<VideoBean> videoEntityList = this.videoEntityService.getVideoEntityList(videoBean, new QueryRequest());
        List<VideoBean> records = videoEntityList.getRecords();
        model.addAttribute("videoBeanShow", records.get(0));
        mav.setViewName(FebsUtil.view("videolistshow"));




        return mav;
    }



    @GetMapping("unauthorized")
    public String unauthorized() {
        return FebsUtil.view("error/403");
    }


    @GetMapping("/")
    public String redirectIndex() {
        return "redirect:/index";
    }

    @GetMapping("index")
    public String index(Model model) {
        User principal = userService.findByName(getCurrentUser().getUsername());
        userService.doGetUserAuthorizationInfo(principal);
        principal.setPassword("It's a secret");
        model.addAttribute("user", principal);
        model.addAttribute("permissions", principal.getStringPermissions());
        model.addAttribute("roles", principal.getRoles());
        return "index";
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "layout")
    public String layout() {
        return FebsUtil.view("layout");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "password/update")
    public String passwordUpdate() {
        return FebsUtil.view("system/user/passwordUpdate");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "user/profile")
    public String userProfile() {
        return FebsUtil.view("system/user/userProfile");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "user/avatar")
    public String userAvatar() {
        return FebsUtil.view("system/user/avatar");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "user/profile/update")
    public String profileUpdate() {
        return FebsUtil.view("system/user/profileUpdate");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/user")
    @RequiresPermissions("user:view")
    public String systemUser() {
        return FebsUtil.view("system/user/user");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/user/add")
    @RequiresPermissions("user:add")
    public String systemUserAdd() {
        return FebsUtil.view("system/user/userAdd");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/user/detail/{username}")
    @RequiresPermissions("user:view")
    public String systemUserDetail(@PathVariable String username, Model model) {
        resolveUserModel(username, model, true);
        return FebsUtil.view("system/user/userDetail");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/user/update/{username}")
    @RequiresPermissions("user:update")
    public String systemUserUpdate(@PathVariable String username, Model model) {
        resolveUserModel(username, model, false);
        return FebsUtil.view("system/user/userUpdate");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/role")
    @RequiresPermissions("role:view")
    public String systemRole() {
        return FebsUtil.view("system/role/role");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/role/add")
    @RequiresPermissions("role:add")
    public String systemRoleAdd() {
        return FebsUtil.view("system/role/roleAdd");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/role/update/{roleId}")
    @RequiresPermissions("role:update")
    public String systemRoleUpdate(@NotBlank(message = "{required}") @PathVariable Long roleId, Model model) {
        Role r = new Role();
        r.setRoleId(roleId);
        List<Role> roleList = roleService.getRoleList(r);

        Role role = roleList.get(0);
        model.addAttribute("role", role);

//        roleService.findRoles()
//        resolveUserModel(username, model, false);
        return FebsUtil.view("system/role/roleUpdate");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/menu")
    @RequiresPermissions("menu:view")
    public String systemMenu() {
        return FebsUtil.view("system/menu/menu");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/dept")
    @RequiresPermissions("dept:view")
    public String systemDept() {
        return FebsUtil.view("system/dept/dept");
    }

    @RequestMapping(FebsConstant.VIEW_PREFIX + "index")
    public String pageIndex() {
        return FebsUtil.view("index");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "404")
    public String error404() {
        return FebsUtil.view("error/404");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "403")
    public String error403() {
        return FebsUtil.view("error/403");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "500")
    public String error500() {
        return FebsUtil.view("error/500");
    }

    private void resolveUserModel(String username, Model model, Boolean transform) {
        User user = userService.findByName(username);
        String deptIds = userDataPermissionService.findByUserId(String.valueOf(user.getUserId()));
        user.setDeptIds(deptIds);
        model.addAttribute("user", user);
        if (transform) {
            String sex = user.getSex();
            if (User.SEX_MALE.equals(sex)) {
                user.setSex("男");
            } else if (User.SEX_FEMALE.equals(sex)) {
                user.setSex("女");
            } else {
                user.setSex("保密");
            }
        }
        if (user.getLastLoginTime() != null) {
            model.addAttribute("lastLoginTime", DateUtil.getDateFormat(user.getLastLoginTime(), DateUtil.FULL_TIME_SPLIT_PATTERN));
        }
    }




}
