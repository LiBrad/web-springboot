package com.brad.web.webspringboot.oaSystem.service;

import com.brad.web.webspringboot.oaSystem.bean.ImageVideoBean;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @version 1.0
 * @author： 李逸野
 * @date： 2020-11-05
 */
public interface UploadService {

    /**
     * 视频文件上传
     * @param request
     * @return
     */
    public List<ImageVideoBean> uploadVideo(MultipartFile file, HttpServletRequest request) throws Exception;

    /**
     * 图片文件上传
     * @param request
     * @return
     */
    public List<ImageVideoBean> uploadImage(MultipartFile file, HttpServletRequest request) throws Exception;


    /**
     * 附件文件上传
     * @param request
     * @return
     */
    public List<ImageVideoBean> uploadEnclosure(MultipartFile file, HttpServletRequest request) throws Exception;
}
