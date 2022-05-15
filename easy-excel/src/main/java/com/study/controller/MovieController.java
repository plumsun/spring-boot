package com.study.controller;

import com.study.entity.JsonResult;
import com.study.entity.MultipartFileParam;
import com.study.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.nio.channels.FileChannel;

/**
 * @description:
 * @date: 2022/4/19 20:03
 * @author: LiHaoHan
 * @program: com.study.controller
 */
@RestController
@RequestMapping("/")
public class MovieController {

    @Autowired
    private MovieService movieService;

    /**
     * 视频播放和下载
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/play")
    public String play(HttpServletRequest request,
                         HttpServletResponse response,@RequestParam("fileName")String fileName) throws UnsupportedEncodingException {
        //实现断点续传的请求头
        String range = request.getHeader("Range");
        return this.movieService.playAndDownload(range,request,response,fileName);
    }

    /**
     * @Title: 判断文件是否上传过，是否存在分片，断点续传
     * @MethodName:  checkBigFile
     * @param filName
     * @Return com.lovecyy.file.up.example3.vo.JsonResult
     * @Exception
     * @Description:
     *  文件已存在，下标为-1
     *  文件没有上传过，下标为零
     *  文件上传中断过，返回当前已经上传到的下标
     * @author: 王延飞
     * @date:  2021/3/2 16:52
     */
    @GetMapping("/check")
    public Integer checkBigFile(String filNameMD5,String fileName) {
        return this.movieService.check(filNameMD5,fileName);
    }
    /**
     * 上传文件
     *
     * @param param
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String filewebUpload(MultipartFileParam param, HttpServletRequest request,HttpServletResponse response) {
       return this.movieService.upload(request,response,param);
    }

    /**
     * 分片上传成功之后，合并文件
     * @param request
     * @return
     */
    @RequestMapping(value = "/merge", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult filewebMerge(HttpServletRequest request) {
        FileChannel outChannel = null;
        this.movieService.fileMerge(request);
        return  null;
    }
}
