package com.lyj.blog.controller;


import com.alibaba.fastjson.JSON;
import com.lyj.blog.model.Blog;
import com.lyj.blog.model.Tag;
import com.lyj.blog.service.BlogService;
import com.lyj.blog.service.TagService;
import com.lyj.blog.service.UserService;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
public class PageController {

    @Value("${imgUploadPath}")
    String imgUploadPath;//上传图片的路径


    @Autowired
    BlogService blogService;
    @Autowired
    TagService tagService;

    @Autowired
    UserService userService;

    @Autowired
    HttpSession session;

    @RequestMapping("/")
    public ModelAndView index() throws Exception {

        ModelAndView index = new ModelAndView("index");

        //查询所有置顶的博客
        List<Blog> stickBlogs = tagService.getBlogsByTagName("置顶");
        index.addObject("stickBlogs",stickBlogs);
        //分页查询blog
        List<Blog> blogs = blogService.get(0, 10,stickBlogs);
        index.addObject("blogs",blogs);
        //查询所有的tag
        List<Tag> tags = tagService.getMaxCountBySize(10);
        index.addObject("tags",tags);
        //查询首页访问次数
        Integer homePageVisitCount = userService.selectAndIncrHomePageVisitCount();
        index.addObject("homePageVisitCount",homePageVisitCount);//首页访问次数

        return index;
    }

    @RequestMapping("blog")
    public ModelAndView blog(Integer id) {

        ModelAndView modelAndView = new ModelAndView();

        if(id==null){
            modelAndView.setViewName("forward:/");//转发到首页
            return modelAndView;
        }

        modelAndView.setViewName("blog");
        //查询对应的blog
        Blog blog = blogService.selectBlogById(id);
        Integer visitCount = blogService.selectAndIncrVisitCount(id);//查询对应博客的访问次数并递增
        blog.setHot(visitCount);
        modelAndView.addObject("blog",blog);
        //查询所有的tag
        List<Tag> tags = tagService.getMaxCountBySize(10);
        modelAndView.addObject("tags",tags);
        //查询首页访问次数
        Integer homePageVisitCount = userService.selectAndIncrHomePageVisitCount();
        modelAndView.addObject("homePageVisitCount",homePageVisitCount);//首页访问次数

        return modelAndView;
    }


    @RequestMapping("/edit")
    public ModelAndView edit(){
        ModelAndView edit = new ModelAndView("edit");
        edit.addObject("editFlag","edit");
        return edit;
    }


    @RequestMapping("/test")
    @ResponseBody
    public String test() throws Exception {
        userService.updateHomePageVisitCountToDataBase();
        return "test";
    }

    /**
     * 上传图片
     * @param request
     * @return
     * @throws IOException
     * @throws FileUploadException
     * @throws ServletException
     */
    @RequestMapping(value = "/uploadImg")
    @ResponseBody
    public String uploadImg(HttpServletRequest request){

        //todo 还需要进行登入校验才可以上传文件

        String fileName=null;
        Iterator<Part> iterator = null;
        try {
            iterator = request.getParts().iterator();
            while(iterator.hasNext()){
                Part next = iterator.next();
                fileName=next.getSubmittedFileName();
                File file = new File(imgUploadPath,fileName);
                if(!file.exists()){
                    file.createNewFile();
                }
                next.write(file.getAbsolutePath());
            }

            HashMap<String, Object> map = new HashMap<>();
            map.put("success",1);
            map.put("message","上传成功");
            map.put("url",fileName);

            return JSON.toJSONString(map);
        } catch (Exception e) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("success",0);
            map.put("message","上传失败");
            return JSON.toJSONString(map);
        }

    }


}
