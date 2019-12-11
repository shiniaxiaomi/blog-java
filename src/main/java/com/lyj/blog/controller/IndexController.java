package com.lyj.blog.controller;

import com.alibaba.fastjson.JSON;
import com.lyj.blog.ESmodel.ESBlog;
import com.lyj.blog.ESmodel.ESHeader;
import com.lyj.blog.file.DirOrFile;
import com.lyj.blog.service.DirService;
import com.lyj.blog.service.ElasticsearchService;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * @author yingjie.lu
 * @version 1.0
 * @date 2019/12/4 12:57 下午
 */


@Controller
@RequestMapping
public class IndexController {

    @Autowired
    DirService dirService;

    @Autowired
    ElasticsearchService elasticsearchService;

    @RequestMapping("/")
    public ModelAndView index(){
        ModelAndView index = new ModelAndView("index");
        String s = ESBlog.htmlMap.get("/README");
        index.addObject("blog",s);
        return index;
    }

    @RequestMapping("blog/**")
    public ModelAndView blog(HttpServletRequest servletRequest){


        ModelAndView index = new ModelAndView("index");

        String s = ESBlog.htmlMap.get(servletRequest.getServletPath().substring(5));
        if(s==null){
            s = ESBlog.htmlMap.get("/README");//返回首页
        }
        index.addObject("blog",s);
        return index;
    }

    @RequestMapping(value = {"dirSearch/{path}","dirSearch"})
    public ModelAndView dirSearch(@PathVariable(required = false) String path){
        ModelAndView modelAndView = new ModelAndView("dirSearch");
        List<DirOrFile> dirs = dirService.getDir(path);
        modelAndView.addObject("dirs", JSON.toJSONString(dirs));

        return modelAndView;
    }

    //获取博客的目录
    @RequestMapping(value = {"getDir/{path}", "getDir"})
    @ResponseBody
    public List<DirOrFile> getDir(@PathVariable(required = false) String path){
        return dirService.getDir(path);
    }


    //获取对应博客的具有结构的headers
    @RequestMapping("getHeaders/**")
    @ResponseBody
    public List<ESHeader> getHeaders(HttpServletRequest servletRequest){
        List<ESHeader> headers = ESHeader.getHeader(servletRequest.getServletPath().substring(11));
        return headers;
    }


    @RequestMapping("search/{keyword}")
    @ResponseBody
    public List<ESHeader> search(@PathVariable String keyword){




        return null;
    }

    //搜索所有标题
    @RequestMapping("searchAllHeader/{keyword}")
    @ResponseBody
    public SearchHit[] searchAllHeader(@PathVariable String keyword) throws IOException {
        return elasticsearchService.searchAllHeader(keyword);
    }

    /**
     * 搜索全站内容
     * @return
     * @throws IOException
     */
    @RequestMapping("searchAll/{keyword}")
    @ResponseBody
    public SearchHit[] searchAll(@PathVariable String keyword) throws IOException {
        return elasticsearchService.searchAll(keyword);
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


}
