package com.lyj.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yingjie.lu
 * @version 1.0
 * @date 2019/12/4 12:57 下午
 */


@Controller
@RequestMapping
public class IndexController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
