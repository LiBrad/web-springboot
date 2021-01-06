package com.brad.web.webspringboot.task;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.brad.web.webspringboot.common.entity.QueryRequest;
import com.brad.web.webspringboot.common.utils.RedisUtil;
import com.brad.web.webspringboot.oaSystem.bean.NewsBean;
import com.brad.web.webspringboot.oaSystem.bean.NoticeBean;
import com.brad.web.webspringboot.oaSystem.bean.OnlineLearningBean;
import com.brad.web.webspringboot.oaSystem.bean.VideoBean;
import com.brad.web.webspringboot.oaSystem.entity.NewsEntity;
import com.brad.web.webspringboot.oaSystem.entity.NoticeEntity;
import com.brad.web.webspringboot.oaSystem.entity.OnlineLearningEntity;
import com.brad.web.webspringboot.oaSystem.entity.VideoEntity;
import com.brad.web.webspringboot.oaSystem.service.NewsEntityService;
import com.brad.web.webspringboot.oaSystem.service.NoticeEntityService;
import com.brad.web.webspringboot.oaSystem.service.OnlineLearningEntityService;
import com.brad.web.webspringboot.oaSystem.service.VideoEntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * @version 1.0
 * @author： 李逸野
 * @date： 2020-11-10
 */
@Component
public class RedisToVisitsMySQL {
    private final Logger logger = LoggerFactory.getLogger(RedisToVisitsMySQL.class);


    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private NewsEntityService newsEntityService;

    @Autowired
    private NoticeEntityService noticeEntityService;

    @Autowired
    private OnlineLearningEntityService onlineLearningEntityService;

    @Autowired
    private VideoEntityService videoEntityService;

    public static final String GET_NEW_ENTITY_OA_COMMON_LIST =  "/newsshow";// 查询院内新闻（AO门户信息公共区域）(详情)


    public static final String GET_NOTICE_ENTITY_OA_COMMON_LIST = "/noticeshow";// 查询通知公告表（AO门户信息公共区域）(详情)

    public static final String GET_ONLINE_LEARNING_OA_COMMON_ENTITY_LIST = "/onlineLearningshow";// 查询在线学习中心（AO门户信息公共区域）

    public static final String GET_VIDEO_ENTITY_OA_COMMON_LIST = "/videolistshow";// 查询医院视频（AO门户信息公共区域）(详情)


//    @Scheduled(cron = "0 0 1 * * ?") //每天凌晨一点执行文章访问量入库
//    @Scheduled(cron = "0 0/1 * * * ?")  //每5分钟执行一次的写法
    @Scheduled(cron = "0 0 0/12 * * ?")      //0 0 0/12 * * ? 每十二个小时执行一次，分别在，0点和12点执行
    public void createHyperLog() {
        logger.info("计数落库开始");


//        QueryRequest queryRequest = new QueryRequest();
//        queryRequest.setPageNum(0);
//        queryRequest.setPageSize(0);

        Set<String> keys = redisUtil.listAllKeys(GET_NEW_ENTITY_OA_COMMON_LIST+":*");
        if (!keys.isEmpty()){
            // 读取key，然后取出文章id，进行数据库操作更新views
            for (String key:keys){
                String flag = key.split(":")[0];

                String blogId = key.split(":")[1];

                if(GET_NEW_ENTITY_OA_COMMON_LIST.equals(flag)){
                    // 查询院内新闻（AO门户信息公共区域）(详情)
                    NewsBean newsBean = new NewsBean();
                    newsBean.setId(blogId);
                    IPage<NewsBean> newsEntityList1 = newsEntityService.getNewsEntityList(newsBean, new QueryRequest());
                    List<NewsBean> newsEntityList =  newsEntityList1.getRecords();
                    if(newsEntityList.size() != 0){
                        NewsBean newsBean1 = newsEntityList.get(0);

                        Long views = redisUtil.size(key);//查找

                        if(views>0){

                            NewsEntity newsEntity = new NewsEntity();
                            newsEntity.setId(blogId);
                            newsEntity.setNewsNumberVisits((int) (views+newsBean1.getNewsNumberVisits()));
                            newsEntity.setNewsRecommendedType("1".equals(newsBean1.getNewsRecommendedType())?"on":"");
                            newsEntity.setNewsRecommendedImageType("1".equals(newsBean1.getNewsRecommendedImageType())?"on":"");
                            newsEntityService.updateNewsEntity(newsEntity);
                            //删除key
                            redisUtil.del(key);
                        }
                    }

                }


                logger.info("院内新闻共计访问文章:"+keys.size()+"篇");
            }
        }

        Set<String> noticeSet = redisUtil.listAllKeys(GET_NOTICE_ENTITY_OA_COMMON_LIST+":*");
        if (!noticeSet.isEmpty()) {

            for (String key:noticeSet) {
                String flag = key.split(":")[0];

                String blogId = key.split(":")[1];
                if(GET_NOTICE_ENTITY_OA_COMMON_LIST.equals(flag)){
                    // 查询通知公告表（AO门户信息公共区域）
                    NoticeBean noticeBean = new NoticeBean();
                    noticeBean.setId(blogId);
                    IPage<NoticeBean> noticeEntityList1 = noticeEntityService.getNoticeEntityList(noticeBean,  new QueryRequest());
                    List<NoticeBean> noticeEntityList = noticeEntityList1.getRecords();
                    if(noticeEntityList.size() != 0){
                        NoticeBean noticeBean1 = noticeEntityList.get(0);

                        Long views = redisUtil.size(key);//查找

                        if(views>0){
                            NoticeEntity noticeEntity = new NoticeEntity();
                            noticeEntity.setId(blogId);
                            noticeEntity.setNoticeNumberVisits((int) (views+noticeBean1.getNoticeNumberVisits()));
                            noticeEntityService.updateNoticeEntity(noticeEntity);
                            //删除key
                            redisUtil.del(key);
                        }
                    }

                }
            }
            logger.info("通知公告共计访问文章:"+noticeSet.size()+"篇");
        }

        Set<String> learningSet = redisUtil.listAllKeys(GET_ONLINE_LEARNING_OA_COMMON_ENTITY_LIST+":*");
        if (!learningSet.isEmpty()) {
            for (String key:learningSet) {
                String flag = key.split(":")[0];

                String blogId = key.split(":")[1];

                if(GET_ONLINE_LEARNING_OA_COMMON_ENTITY_LIST.equals(flag)){
                    // 查询在线学习中心（AO门户信息公共区域）
                    OnlineLearningBean onlineLearningBean = new OnlineLearningBean();
                    onlineLearningBean.setId(blogId);

                    IPage<OnlineLearningBean> onlineLearningEntityList1 = onlineLearningEntityService.getOnlineLearningEntityList(onlineLearningBean,  new QueryRequest());
                    List<OnlineLearningBean> onlineLearningEntityList = onlineLearningEntityList1.getRecords();
                    if(onlineLearningEntityList.size() != 0){
                        OnlineLearningBean onlineLearningBean1 = onlineLearningEntityList.get(0);

                        Long views = redisUtil.size(key);//查找

                        if(views>0){

                            OnlineLearningEntity onlineLearningEntity = new OnlineLearningEntity();
                            onlineLearningEntity.setId(blogId);
                            onlineLearningEntity.setOlNumberVisits((int) (views+onlineLearningBean1.getOlNumberVisits()));
                            onlineLearningEntityService.updateOnlineLearningEntity(onlineLearningEntity);
                            //删除key
                            redisUtil.del(key);
                        }
                    }

                }


            }
            logger.info("在线学习中心共计访问文章:"+learningSet.size()+"篇");
        }

        Set<String> videoSet = redisUtil.listAllKeys(GET_VIDEO_ENTITY_OA_COMMON_LIST+":*");
        if (!videoSet.isEmpty()) {
            for (String key:videoSet) {
                String flag = key.split(":")[0];

                String blogId = key.split(":")[1];

                if(GET_VIDEO_ENTITY_OA_COMMON_LIST.equals(flag)){
                    // 查询医院视频（AO门户信息公共区域）
                    VideoBean videoBean = new VideoBean();
                    videoBean.setId(blogId);
                    IPage<VideoBean> videoEntityList1 = videoEntityService.getVideoEntityList(videoBean, new QueryRequest()  );
                    List<VideoBean> videoEntityList = videoEntityList1.getRecords();
                    if(videoEntityList.size() != 0){
                        VideoBean videoBean1 = videoEntityList.get(0);

                        Long views = redisUtil.size(key);//查找

                        if(views>0){

                            VideoEntity videoEntity = new VideoEntity();
                            videoEntity.setId(blogId);
                            videoEntity.setVideoNumberVisits((int) (views+videoBean1.getVideoNumberVisits()));
                            videoEntityService.updateVideoEntity(videoEntity);
                            //删除key
                            redisUtil.del(key);
                        }
                    }

                }


            }
            logger.info("医院视频共计访问:"+videoSet.size()+"篇");
        }


            // 查询通知公告表（AO门户信息公共区域）
//        IPage<NoticeBean> noticeEntityList1 = noticeEntityService.getNoticeEntityList(new NoticeBean(), queryRequest);
//        List<NoticeBean> noticeEntityList = noticeEntityList1.getRecords();
//        for (int i = 0; i < noticeEntityList.size(); i++) {
//            NoticeBean noticeBean = noticeEntityList.get(i);
//
//            String id = noticeBean.getId();
//            String key = GET_NOTICE_ENTITY_OA_COMMON_LIST+id;//拼接ID
//
//            Long views = redisUtil.size(key);//查找
//
//            if(views>0){
//
//                NoticeEntity noticeEntity = new NoticeEntity();
//                noticeEntity.setId(id);
//                noticeEntity.setNoticeNumberVisits((int) (views+noticeBean.getNoticeNumberVisits()));
//                noticeEntityService.updateNoticeEntity(noticeEntity);
//                redisUtil.del(key);
//            }
//        }
//

//        // 查询在线学习中心（AO门户信息公共区域）
//        IPage<OnlineLearningBean> onlineLearningEntityList1 = onlineLearningEntityService.getOnlineLearningEntityList(new OnlineLearningBean(), queryRequest);
//        List<OnlineLearningBean> onlineLearningEntityList = onlineLearningEntityList1.getRecords();
//        for (int i = 0; i < onlineLearningEntityList.size(); i++) {
//            OnlineLearningBean onlineLearningBean = onlineLearningEntityList.get(i);
//
//            String id = onlineLearningBean.getId();
//            String key = GET_ONLINE_LEARNING_OA_COMMON_ENTITY_LIST+id;//拼接ID
//
//            Long views = redisUtil.size(key);//查找
//
//            if(views>0){
//
//                OnlineLearningEntity onlineLearningEntity = new OnlineLearningEntity();
//                onlineLearningEntity.setId(id);
//                onlineLearningEntity.setOlNumberVisits((int) (views+onlineLearningBean.getOlNumberVisits()));
//                onlineLearningEntityService.updateOnlineLearningEntity(onlineLearningEntity);
//                redisUtil.del(key);
//            }
//        }




//        // 查询医院视频（AO门户信息公共区域）
//        IPage<VideoBean> videoEntityList1 = videoEntityService.getVideoEntityList(new VideoBean(),queryRequest  );
//        List<VideoBean> videoEntityList = videoEntityList1.getRecords();
//        for (int i = 0; i < videoEntityList.size(); i++) {
//            VideoBean videoBean = videoEntityList.get(i);
//
//            String id = videoBean.getId();
//            String key = GET_VIDEO_ENTITY_OA_COMMON_LIST+id;//拼接ID
//
//            Long views = redisUtil.size(key);//查找
//
//            if(views>0){
//
//                VideoEntity videoEntity = new VideoEntity();
//                videoEntity.setId(id);
//                videoEntity.setVideoNumberVisits((int) (views+videoBean.getVideoNumberVisits()));
//                videoEntityService.updateVideoEntity(videoEntity);
//                redisUtil.del(key);
//            }
//        }
        logger.info("计数落库结束");
    }

}
