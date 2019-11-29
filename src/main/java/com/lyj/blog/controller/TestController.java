package com.lyj.blog.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author yingjie.lu
 * @version 1.0
 * @date 2019/11/28 4:49 下午
 */
@RestController
@RequestMapping
public class TestController {

    @RequestMapping("test")
    public String test() {
        return "success";
    }

    public static void main(String[] args) throws IOException {


    }

}
