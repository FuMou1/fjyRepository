/**
 * Copyright (c) 2016-2019 华克斯开源 All rights reserved.
 *
 * https://www.guli.cloud
 *
 * 版权所有，侵权必究！
 */

package com.atguigu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class GmallAdminMainApplication {

	public static void main(String[] args) {
		SpringApplication.run(GmallAdminMainApplication.class, args);
	}

}