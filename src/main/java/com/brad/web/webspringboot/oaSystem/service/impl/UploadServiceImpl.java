package com.brad.web.webspringboot.oaSystem.service.impl;

import com.brad.web.webspringboot.common.properties.NginxProperties;
import com.brad.web.webspringboot.common.utils.FrameGrabberKit;
import com.brad.web.webspringboot.oaSystem.bean.ImageVideoBean;
import com.brad.web.webspringboot.oaSystem.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @version 1.0
 * @author： 李逸野
 * @date： 2020-11-05
 */
@Transactional
@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    private NginxProperties nginxProperties;//引入配置文件

    /**
     * 视频文件上传
     */
    @Override
    public List<ImageVideoBean> uploadVideo(MultipartFile file, HttpServletRequest request) throws Exception {
        List<ImageVideoBean> resultList = new ArrayList<ImageVideoBean>();

        String basePath = nginxProperties.getNginxUploadUrl()+"/video/";

        Long time = new Date().getTime();

        String fileName = file.getOriginalFilename();//文件原始名称
        String suffixName = fileName.substring(fileName.lastIndexOf("."));//从最后一个.开始截取。截取fileName的后缀名
        String newFileName = time+suffixName; //文件新名称
        //设置文件存储路径，可以存放在你想要指定的路径里面
        String rootPath=nginxProperties.getNginxUploadPath()+File.separator+"upload/video/"; //上传视频存放位置

        String filePath = rootPath+newFileName;
        File newFile = new File(filePath);
        //判断目标文件所在目录是否存在
        if(!newFile.getParentFile().exists()){
            //如果目标文件所在的目录不存在，则创建父目录
            newFile.getParentFile().mkdirs();
        }

        //将内存中的数据写入磁盘
        file.transferTo(newFile);
        //视频上传保存url
        String videoUrl = basePath + newFileName;

        //视频封面图处理
        String newImgName = time+".jpg";
        String framefile = rootPath + newImgName;
        String imgUrlSave = basePath+newImgName;//图片最终位置路径
        //视频截取封面图
        String imgUrl= FrameGrabberKit.getVedioImg(videoUrl, framefile, imgUrlSave);


        ImageVideoBean imageVideoBean = new ImageVideoBean();

        imageVideoBean.setImgUrl(imgUrl);
        imageVideoBean.setVideoUrl(videoUrl);
        resultList.add(imageVideoBean);

//        resultMap.put(ErrorCode.STATE, ErrorCode.SUCCESS);
        //System.out.println("上传的文件名为："+fileName+",后缀名为："+newFileName);
        return resultList;
    }

    /**
     * 图片文件上传
     */
    @Override
    public List<ImageVideoBean> uploadImage(MultipartFile file, HttpServletRequest request) throws Exception {
        List<ImageVideoBean> resultList = new ArrayList<ImageVideoBean>();


        String basePath = nginxProperties.getNginxUploadUrl()+"/images/";

        Long time = new Date().getTime();

        String fileName = file.getOriginalFilename();//文件原始名称
        String suffixName = fileName.substring(fileName.lastIndexOf("."));//从最后一个.开始截取。截取fileName的后缀名
        String newFileName = time+suffixName; //文件新名称
        //设置文件存储路径，可以存放在你想要指定的路径里面
        String rootPath=nginxProperties.getNginxUploadPath()+ File.separator+"upload/images/"; //上传图片存放位置

        String filePath = rootPath+newFileName;
        File newFile = new File(filePath);
        //判断目标文件所在目录是否存在
        if(!newFile.getParentFile().exists()){
            //如果目标文件所在的目录不存在，则创建父目录
            newFile.getParentFile().mkdirs();
        }

        //将内存中的数据写入磁盘
        file.transferTo(newFile);
        //图片上传保存url
        String imgUrl = basePath + newFileName;

        ImageVideoBean imageVideoBean = new ImageVideoBean();

        imageVideoBean.setImgUrl(imgUrl);
        resultList.add(imageVideoBean);
//        resultMap.put(ErrorCode.STATE, ErrorCode.SUCCESS);
        return resultList;
    }

    @Override
    public List<ImageVideoBean> uploadEnclosure(MultipartFile file, HttpServletRequest request) throws Exception {
        List<ImageVideoBean> resultList = new ArrayList<ImageVideoBean>();


        String basePath = nginxProperties.getNginxUploadUrl()+"/enclosure/";

        Long time = new Date().getTime();

        String fileName = file.getOriginalFilename();//文件原始名称
        String suffixName = fileName.substring(fileName.lastIndexOf("."));//从最后一个.开始截取。截取fileName的后缀名
        String newFileName = time+suffixName; //文件新名称
        //设置文件存储路径，可以存放在你想要指定的路径里面
        String rootPath=nginxProperties.getNginxUploadPath()+ File.separator+"upload/enclosure/"; //上传附件存放位置

        String filePath = rootPath+newFileName;
        File newFile = new File(filePath);
        //判断目标文件所在目录是否存在
        if(!newFile.getParentFile().exists()){
            //如果目标文件所在的目录不存在，则创建父目录
            newFile.getParentFile().mkdirs();
        }

        //将内存中的数据写入磁盘
        file.transferTo(newFile);
        //附件上传保存url
        String enclosureUrl = basePath + newFileName;

        ImageVideoBean imageVideoBean = new ImageVideoBean();

        imageVideoBean.setEnclosureUrl(enclosureUrl);
        resultList.add(imageVideoBean);
//        resultMap.put(ErrorCode.STATE, ErrorCode.SUCCESS);
        return resultList;
    }
}
