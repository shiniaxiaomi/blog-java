package com.lyj.blog.controller;

import com.lyj.blog.ESmodel.ESBlog;
import com.lyj.blog.ESmodel.ESHeader;
import com.lyj.blog.file.DirOrFile;
import org.apache.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author yingjie.lu
 * @version 1.0
 * @date 2019/12/4 12:57 下午
 */


@Controller
@RequestMapping
public class IndexController {

    @RequestMapping("/")
    public ModelAndView index(){
        ModelAndView index = new ModelAndView("index");
        return index;
    }

    @RequestMapping("blog/**")
    public ModelAndView blog(HttpServletRequest servletRequest){


        ModelAndView index = new ModelAndView("index");

        String s = ESBlog.htmlMap.get(servletRequest.getServletPath().substring(5));
        if(s==null){
            index.setViewName("dirSearch");
        }
        index.addObject("blog",s);
        return index;
    }

    //获取博客的目录
    @RequestMapping("getDir")
    @ResponseBody
    public List<DirOrFile> getDir(){
        return DirOrFile.Instance.getChild().get(0).getChild();
    }


    //获取对应博客的具有结构的headers
    @RequestMapping("getHeaders/**")
    @ResponseBody
    public List<ESHeader> getHeaders(HttpServletRequest servletRequest){
        List<ESHeader> headers = ESHeader.getHeader(servletRequest.getServletPath().substring(11));
        return headers;
    }

    //searchToJSON/xxx：search的精简版，只返回字符串，不返回页面，用于本地直接打开

    //search/xxx：搜索与关键词相关的所有内容（包括博客的名称，header和内容），返回匹配的博客连接
    //searchTitle：搜索博客的名称
    //searchHeader：搜索全部的header，返回对应的博客的#header的链接
    //searchText：搜索搜索博客的内容，返回对应的博客的链接（如果可以，返回离内容最近的header的链接）


    //blog/路径xxx：返回对应的博客页面

    //getHeader/指定博客：返回对应博客的大纲

    //getDir：返回博客的目录结构


}
