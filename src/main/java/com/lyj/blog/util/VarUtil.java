package com.lyj.blog.util;

import com.lyj.blog.model.Blog;
import com.lyj.blog.model.BlogFile;
import com.lyj.blog.model.Header;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yingjie.lu
 * @version 1.0
 * @date 2019/12/5 9:59 上午
 */
public class VarUtil {

    public static Map<String, Header> headerMap=new HashMap();

    public static BlogFile blogRoot=new BlogFile("blog","");

    public static BlogFile blogFile=blogRoot;



}
