package com.brad.web.webspringboot.aop;

import com.brad.web.webspringboot.common.utils.IpUtil;
import com.brad.web.webspringboot.common.utils.RedisUtil;
import com.brad.web.webspringboot.oaSystem.bean.NewsBean;
import com.brad.web.webspringboot.oaSystem.bean.NoticeBean;
import com.brad.web.webspringboot.oaSystem.bean.OnlineLearningBean;
import com.brad.web.webspringboot.oaSystem.bean.VideoBean;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @version 1.0
 * @author： 李逸野
 * @date： 2020-11-10
 */
@Component
@Aspect
public class UvAspect {

    @Autowired
    private RedisUtil redisUtil;

    // 拿到@UVlog注解注释的方法，这里就是切点
    @Pointcut("@annotation(com.brad.web.webspringboot.aop.UVlog)")
    private void weblog(){

    }

    // 调用方法后都会进行统计操作，写入redis
    @After("weblog()")
    public void afterMethod(JoinPoint joinpoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        Object[] args = joinpoint.getArgs();

        String value = IpUtil.getIpAddr();//获得IP
        String servletPath = request.getServletPath();//映射的路径  例如：/request
        Object arg = args[2];


        if(arg instanceof  NewsBean){
            NewsBean id =(NewsBean) args[2];//院内新闻id值
            String key = servletPath+":"+id.getId();
//        System.out.println(servletPath);
            redisUtil.add(key,value);
        }

        if(arg instanceof NoticeBean){
            NoticeBean id =(NoticeBean) args[2];//通知公告id值
            String key = servletPath+":"+id.getId();
//        System.out.println(servletPath);
            redisUtil.add(key,value);
        }

        if(arg instanceof VideoBean){
            VideoBean id =(VideoBean) args[2];//医院视频id值
            String key = servletPath+":"+id.getId();
//        System.out.println(servletPath);
            redisUtil.add(key,value);
        }

        if(arg instanceof OnlineLearningBean){
            OnlineLearningBean id =(OnlineLearningBean) args[2];//在线学习中心id值
            String key = servletPath+":"+id.getId();
//        System.out.println(servletPath);
            redisUtil.add(key,value);
        }


//        if (RedisToMySQL.prefixDate == null){
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        String key = "BLOG:"+args[0]+":"+ RedisToMySQL.prefixDate;
//        log.info("生成key = "+key);
//        redisService.add(key,value);


    }
}