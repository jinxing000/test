package com.ef.golf.vedio;

import org.springframework.stereotype.Repository;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * com.ef.golf.util
 * Administrator
 * 2018/5/21 13:50
 */
public class VideoInfo {
    /**
     * 获取视频的信息
     * FFMPEG homepage http://ffmpeg.org/about.html
     */
        //视频路径
        private String ffmpegApp;
        //视频时
        private int hours;
        //视频分
        private int minutes;
        //视频秒
        private float seconds;
        //视频width
        private int width;
        //视频height
        private int heigt;
        private String time;


        public VideoInfo() {}

        public VideoInfo(String ffmpegApp)
        {
            this.ffmpegApp = ffmpegApp;
        }

        public String toString()
        {
            return "time: " + hours + ":" + minutes + ":" + seconds + ", width = " + width + ", height= " + heigt;
        }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFfmpegApp()
    {
        return ffmpegApp;
    }

    public void setFfmpegApp(String ffmpegApp)
    {
        this.ffmpegApp = ffmpegApp;
    }

    public int getHours()
    {
        return hours;
    }

    public void setHours(int hours)
    {
        this.hours = hours;
    }

    public int getMinutes()
    {
        return minutes;
    }

    public void setMinutes(int minutes)
    {
        this.minutes = minutes;
    }

    public float getSeconds()
    {
        return seconds;
    }

    public void setSeconds(float seconds)
    {
        this.seconds = seconds;
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public int getHeigt()
    {
        return heigt;
    }

    public void setHeigt(int heigt)
    {
        this.heigt = heigt;
    }

    /** ============================================================================= */
        //视频信息
        public void getInfo(String videoFilename) throws IOException,
                InterruptedException
        {
            String tmpFile = videoFilename + ".tmp.png";
            //String tmpFile = videoFilename;
            ProcessBuilder processBuilder = new ProcessBuilder(ffmpegApp, "-y",
                    "-i", videoFilename, "-vframes", "1", "-ss", "0:0:0", "-an",
                    "-vcodec", "png", "-f", "rawvideo", "-s", "100*100", tmpFile);

            Process process = processBuilder.start();

            InputStream stderr = process.getErrorStream();
            InputStreamReader isr = new InputStreamReader(stderr);
            BufferedReader br = new BufferedReader(isr);
            String line;
            //打印 sb，获取更多信息。 如 bitrate、width、heigt
            StringBuffer sb = new StringBuffer();
            while ((line = br.readLine()) != null)
            {
                sb.append(line);
            }

            new File(tmpFile).delete();

            System.out.println("video info:\n" + sb);
            Pattern pattern = Pattern.compile("Duration: (.*?),");
            Matcher matcher = pattern.matcher(sb);

            if (matcher.find())
            {
                time = matcher.group(1);
                calcTime(time);
            }

            pattern = Pattern.compile("w:\\d+ h:\\d+");
            matcher = pattern.matcher(sb);
            if (matcher.find())
            {
                String wh = matcher.group();
                //w:100 h:100
                String[] strs = wh.split("\\s+");
                if(strs != null && strs.length == 2)
                {
                    width = Integer.parseInt(strs[0].split(":")[1]);
                    heigt = Integer.parseInt(strs[1].split(":")[1]);
                }
            }

            process.waitFor();
            if(br != null)
                br.close();
            if(isr != null)
                isr.close();
            if(stderr != null)
                stderr.close();
        }
    /** ============================================================================= */
        //视频首图
        @SuppressWarnings("unused")
        /****
         * 获取指定时间内的图片
         * @param videoFilename:视频路径
         * @param thumbFilename:图片保存路径
         * @param width:图片长
         * @param height:图片宽
         * @param hour:指定时
         * @param min:指定分
         * @param sec:指定秒
         * @throws IOException
         * @throws InterruptedException
         */
        public void getThumb(String videoFilename, String thumbFilename, int width,
                             int height, int hour, int min, double sec) throws IOException,
                InterruptedException
        {

            ProcessBuilder processBuilder = new ProcessBuilder(ffmpegApp, "-y",
                    "-i", videoFilename, "-vframes", "1", "-ss", hour + ":" + min
                    + ":" + sec, "-f", "mjpeg", "-s", width + "*" + height,
                    "-an", thumbFilename);

            Process process = processBuilder.start();

            InputStream stderr = process.getErrorStream();
            InputStreamReader isr = new InputStreamReader(stderr);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null)
                ;
            process.waitFor();

            if(br != null)
                br.close();
            if(isr != null)
                isr.close();
            if(stderr != null)
                stderr.close();
        }

        private void calcTime(String timeStr)
        {
            String[] parts = timeStr.split(":");
            hours = Integer.parseInt(parts[0]);
            minutes = Integer.parseInt(parts[1]);
            seconds = Float.parseFloat(parts[2]);
        }

        /*public static void main(String[] args)
        {
            VideoInfo videoInfo = new VideoInfo("D:\\ffmpeg\\bin\\ffmpeg.exe");
            try
            {
                //videoInfo.getInfo("https://quanzi-vedios.oss-cn-beijing.aliyuncs.com/1515116360419.mp4");
               //videoInfo.getInfo("https://course-imgs.oss-cn-beijing.aliyuncs.com/1524631522847.jpg");
                videoInfo.getThumb("https://quanzi-vedios.oss-cn-beijing.aliyuncs.com/1526451471369.mp4", "E:\\quanziImgs\\quanziImgs.jpg",    800, 600, 0, 0, 0);
                System.out.println(videoInfo.getFfmpegApp());
                File file = new File("E:\\quanziImgs\\quanziImgs.jpg");
                System.out.println(file.getName()+"1");
                //分割文件名称
                String[] names = file.getName().split("\\.");
                //获取文件后缀
                String filesName=names[names.length-1];
                //为当前文件重新命名
                String suoluetuUrl = System.currentTimeMillis() + "." + names[names.length-1];
                file.renameTo(new File("System.currentTimeMillis() + \".\" + names[names.length-1]"));
                System.out.println(file.getName()+"2");
               *//* BufferedImage sourceImg=ImageIO.read(new FileInputStream(new File("E:\\quanziImgs\\quanziImgs.jpg")));
                System.out.println(sourceImg);*//*
                *//** 获取图片宽高 *//*
                *//*InputStream murl = new URL("https://course-imgs.oss-cn-beijing.aliyuncs.com/1524631522847.jpg").openStream();
                BufferedImage sourceImg =ImageIO.read(murl);
                System.out.println(sourceImg.getWidth());   // 源图宽度
                System.out.println(sourceImg.getHeight());   // 源图高度*//*

            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }*/
}
