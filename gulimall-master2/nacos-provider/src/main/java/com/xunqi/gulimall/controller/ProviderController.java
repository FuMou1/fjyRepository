package com.xunqi.gulimall.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class ProviderController {

    @Value("${myName}")
    private String name;


    @Value("${app.version}")
    private String version;

    @GetMapping("/hello/{str}")
    public String hello(@PathVariable String str){
        return   "hello " + str + ", I am " + name + ". version:"+version;
    }
}
