package com.xunqi.gulimall.controller;

import com.xunqi.gulimall.ProviderFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

    @Value("${spring.application.name}")
    private String appName;

    @Value("${myName}")
    private String name;

    private RestTemplate restTemplate = new RestTemplate();
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @RequestMapping(value = "/hello/{str}", method = RequestMethod.GET)
    public String nacos(@PathVariable String str) {//

        System.out.println("request str:" + str);
        // 使用 LoadBalanceClient 和 RestTemolate 结合的方式来访问
        ServiceInstance serviceInstance = loadBalancerClient.choose("nacos-provider");
        String url = String.format("http://%s:%s/hello/%s", serviceInstance.getHost(), serviceInstance.getPort(),
                appName);
        System.out.println("request url:" + url);
        return restTemplate.getForObject(url, String.class);
    }
}
