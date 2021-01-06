package com.brad.web.webspringboot.common.utils;

import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @version 1.0
 * @author： 李逸野
 * @date： 2020-11-05
 */
public class FrameGrabberKit {


    /**
     * 获取视频图片
     * @param videofile  源视频文件路径
     * @param framefile  截取帧的图片存放路径
     * @param imgfile 视频封面图保存路径
     * @return
     */
    public static String getVedioImg(String videofile, String framefile,String imgfile){
        String ImgUrl="";
        //截取封面图
        try {
            fetchFrame(videofile, framefile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 完整的ImgUrl
        ImgUrl = imgfile;//视频封面图保存路径
        return ImgUrl;
    }
    /**
     * 获取指定视频的帧并保存为图片至指定目录
     * @param videofile  源视频文件路径
     * @param framefile  截取帧的图片存放路径 例：F:\hfkjrecorder\target\4.jpg
     * @throws Exception
     */
    public static void fetchFrame(String videofile, String framefile) throws Exception {
        //long start = System.currentTimeMillis();
        File targetFile = new File(framefile);
        FFmpegFrameGrabber ff = new FFmpegFrameGrabber(videofile);
        ff.start();
        int lenght = ff.getLengthInFrames();
        int i = 0;
        int interceptionFrames = 30;//截取第几帧
        //默认截取第50帧，如果第50帧大于视频总帧数的8成直接取长度lenght * 0.3
        if(interceptionFrames >= lenght * 0.8) {
            interceptionFrames = (int)(lenght * 0.3);
        }
        Frame f = null;
        while (i < lenght) {
            // 过滤 前  interceptionFrames 帧，避免出现全黑的图片，依自己情况而定
            f = ff.grabFrame();
            if ((i > interceptionFrames) && (f.image != null)) {
                break;
            }
            i++;
        }
        opencv_core.IplImage img = f.image;
        int owidth = img.width();
        int oheight = img.height();
        // 对截取的帧进行等比例缩放 宽350、高160
//        if(owidth > oheight) {//宽大于高
//
//        }else {//高大于宽
//
//        }
        int width = 800;
        int height = (int) (((double) width / owidth) * oheight);
        /**
         width - 所创建图像的宽度
         height - 所创建图像的高度
         imageType - 所创建图像的类型
         TYPE_3BYTE_BGR - 表示一个具有 8 位 RGB 颜色分量的图像，对应于 Windows 风格的 BGR 颜色模型，具有用 3 字节存储的 Blue、Green 和 Red 三种颜色。
         */
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        //此方法返回 Graphics2D，但此处是出于向后兼容性的考虑。
        bi.getGraphics().drawImage(f.image.getBufferedImage().getScaledInstance(width, height, Image.SCALE_SMOOTH),
                0, 0, null);
        ImageIO.write(bi, "jpg", targetFile);
        //ff.flush();
        ff.stop();
        //  System.out.println(System.currentTimeMillis() - start);
    }
}
