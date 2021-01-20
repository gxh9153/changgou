package com.changgou.controller;

import com.changgou.api.CommonResult;
import com.changgou.file.FastDFSFile;
import com.changgou.util.FastDFSClient;
import org.csource.fastdfs.FileInfo;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@RestController
@CrossOrigin
@RequestMapping("/file")
public class FileController{
    /**
     * 文件上传
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) throws Exception{
        //封装一个FastDFSFile
        FastDFSFile fastDFSFile = new FastDFSFile(file.getOriginalFilename(),
                                                  file.getBytes(),
                                                  StringUtils.getFilenameExtension(file.getOriginalFilename()));
        //文件上传
        String[] upload = FastDFSClient.upload(fastDFSFile);
        //组装文件上传的地址
        return FastDFSClient.getTrackerUrl()+"/"+upload[0]+"/"+upload[1];
    }

    /**
     * 获取文件信息
     * @param groupName
     * @param remoteFileName
     * @return
     */
    @GetMapping("/fileInfo")
    public CommonResult<FileInfo> getFileInfo(@RequestParam("groupName")String groupName,
                                              @RequestParam("remoteFileName")String remoteFileName){

        FileInfo file = FastDFSClient.getFile(groupName, remoteFileName);
        return CommonResult.success(file);
    }
}
