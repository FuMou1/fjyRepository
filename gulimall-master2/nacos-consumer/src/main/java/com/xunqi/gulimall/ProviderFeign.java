package com.xunqi.gulimall;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("nacos-provider")
public interface ProviderFeign {
    @RequestMapping("hello")
    public String hello();
}
