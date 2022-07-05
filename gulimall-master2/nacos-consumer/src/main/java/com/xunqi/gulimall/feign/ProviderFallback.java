package com.xunqi.gulimall.feign;

import com.xunqi.gulimall.ProviderFeign;
import org.springframework.stereotype.Component;

@Component
public class ProviderFallback implements ProviderFeign {

    public String hello() {
        return "现在服务器忙，请稍后再试！";
    }
}
