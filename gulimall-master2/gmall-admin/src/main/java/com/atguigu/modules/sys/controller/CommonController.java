package com.atguigu.modules.sys.controller;

import com.atguigu.common.utils.R;
import com.atguigu.common.utils.file.FileUploadUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class CommonController extends AbstractController {


    @PostMapping("/sys/common/upload")
    public R uploadFile(MultipartFile file) throws Exception {
        try {
            Map<String, String> respMap = new LinkedHashMap<String, String>();
            // 上传文件路径
            String filePath = FileUploadUtils.getDefaultBaseDir();
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(filePath, file);
            String url = "http://gulimall.com/" + fileName;
            System.out.println("\n\n\n"+fileName+"\n\n\n"+url+"\n\n\n");
            respMap.put("fileName", fileName);
            respMap.put("url", url);
            return R.ok().put("data",respMap);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }
}
