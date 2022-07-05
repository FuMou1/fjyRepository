package com.atguigu.common.exception.file;

import com.atguigu.common.utils.file.BaseException;

/**
 * 文件名大小限制异常类
 * 
 * @author ruoyi
 */
public class FileSizeLimitExceededException extends BaseException {
    private static final long serialVersionUID = 1L;

    public FileSizeLimitExceededException(long defaultMaxSize)
    {
        super("upload.exceed.maxSize", new Object[] { defaultMaxSize });
    }
}
