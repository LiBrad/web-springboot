package com.brad.web.webspringboot.oaSystem.controller;

import com.brad.web.webspringboot.common.sourceDataBean.DataSource;
import com.brad.web.webspringboot.common.sourceDataBean.RestCode;
import com.brad.web.webspringboot.common.sourceDataBean.SourceDataBean;
import com.brad.web.webspringboot.oaSystem.bean.ImageVideoBean;
import com.brad.web.webspringboot.oaSystem.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * @version 1.0
 * @author： 李逸野
 * @date： 2020-11-05
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("upload")
public class UploadController {
    private final Logger logger = LoggerFactory.getLogger(UploadController.class);

    @Autowired
    private UploadService uploadService;


    /**
     * 视频文件上传
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value ="/uploadVideo",method= RequestMethod.POST)
    public SourceDataBean<ImageVideoBean> uploadVideo(MultipartFile file, HttpServletRequest request) throws Exception{
        logger.info("UploadController==>uploadVideo()");
        SourceDataBean<ImageVideoBean> sdb = new SourceDataBean<ImageVideoBean>();
        List<ImageVideoBean> res = uploadService.uploadVideo(file, request);

        if(res != null && res.size() > 0){

            sdb.setDataSource(new DataSource<ImageVideoBean>(res,0,null,null));
            sdb.setMessage(RestCode.SUCCESS.code,"ok");

        }else{
            sdb.setMessage(RestCode.NOT_FOUNT.code, "没有数据");
        }
        return sdb;
    }

    /**
     * 图片文件上传
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value ="/uploadImage",method= RequestMethod.POST)
    public SourceDataBean<ImageVideoBean> uploadImage(MultipartFile file, HttpServletRequest request) throws Exception{


        logger.info("UploadController==>uploadImage()");
        SourceDataBean<ImageVideoBean> sdb = new SourceDataBean<ImageVideoBean>();
        List<ImageVideoBean> res = uploadService.uploadImage(file, request);

        if(res != null && res.size() > 0){

            sdb.setDataSource(new DataSource<ImageVideoBean>(res,0,null,null));
            sdb.setMessage(RestCode.SUCCESS.code,"ok");

        }else{
            sdb.setMessage(RestCode.NOT_FOUNT.code, "没有数据");
        }
        return sdb;
    }


    /**
     * 附件文件上传
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value ="/uploadEnclosure",method= RequestMethod.POST)
    public SourceDataBean<ImageVideoBean> uploadEnclosure(MultipartFile file, HttpServletRequest request) throws Exception{


        logger.info("UploadController==>uploadEnclosure()");
        SourceDataBean<ImageVideoBean> sdb = new SourceDataBean<ImageVideoBean>();
        List<ImageVideoBean> res = uploadService.uploadEnclosure(file, request);

        if(res != null && res.size() > 0){

            sdb.setDataSource(new DataSource<ImageVideoBean>(res,0,null,null));
            sdb.setMessage(RestCode.SUCCESS.code,"ok");

        }else{
            sdb.setMessage(RestCode.NOT_FOUNT.code, "没有数据");
        }
        return sdb;
    }
}
