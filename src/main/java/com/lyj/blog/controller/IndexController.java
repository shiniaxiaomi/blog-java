package com.lyj.blog.controller;


import com.lyj.blog.service.ElasticsearchService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 博客的主控制器
 * @author yingjie.lu
 * @version 1.0
 * @date 2019/12/4 12:57 下午
 */


//@Controller
@RequestMapping
@Slf4j
public class IndexController {


    @Autowired
    ElasticsearchService elasticsearchService;





    /**
     * 手动更新博客
     * @return 返回首页
     * @throws IOException
     */
    @RequestMapping("pull")
    @ResponseBody
    public String manualPull(){
        return "博客更新成功";
    }

    /**
     * 保存blog的访问次数到文件
     * @return
     */
    @RequestMapping("writeBlogVisitTimes")
    @ResponseBody
    public String writeBlogVisitTimes(){


        return "success";
    }

    /**
     * 读取blog的访问次数到文件
     * @return
     */
    @RequestMapping("readBlogVisitTimes")
    @ResponseBody
    public String readBlogVisitTimes(){

        return "blog的访问次数读取成功";
    }

    //searchToJSON/xxx：search的精简版，只返回字符串，不返回页面，用于本地直接打开

    //search/xxx：搜索与关键词相关的所有内容（包括博客的名称，header和内容），返回匹配的博客连接
    //searchTitle：搜索博客的名称
    //searchHeader：搜索全部的header，返回对应的博客的#header的链接
    //searchText：搜索搜索博客的内容，返回对应的博客的链接（如果可以，返回离内容最近的header的链接）


    //=========已完成=========
    //blog/路径xxx：返回对应的博客页面
    //getHeader/指定博客：返回对应博客的大纲
    //getDir：返回博客的目录结构

//    @RequestMapping("test")
//    @ResponseBody
//    public String test(){
//        String seoData = dirService.getSEOData();
//        return seoData;
//    }


}
