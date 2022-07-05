package com.xunqi.gulimall.search.controller;

import com.xunqi.common.utils.R;
import com.xunqi.gulimall.search.service.MallSearchService;
import com.xunqi.gulimall.search.vo.SearchParam;
import com.xunqi.gulimall.search.vo.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @Created: with IntelliJ IDEA.
 * @author: fjy
 * @createTime: 2022-06-12 18:07
 **/


@RestController
public class SearchApiController {

    @Autowired
    private MallSearchService mallSearchService;


    /**
     * 自动将页面提交过来的所有请求参数封装成我们指定的对象
     * @param param
     * @return
     */
    @GetMapping(value = "/search/list")
    public R listPage(SearchParam param,  HttpServletRequest request) {

        param.set_queryString(request.getQueryString());

        //1、根据传递来的页面的查询参数，去es中检索商品
        SearchResult result = mallSearchService.search(param);

        return R.ok().put("data", result);
    }

}
