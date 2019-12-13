package com.lyj.blog.service;

import com.alibaba.fastjson.JSON;
import com.lyj.blog.ESmodel.ESBlog;
import com.lyj.blog.file.*;
import com.lyj.blog.util.ElasticseachClientUtil;
import com.lyj.blog.util.GitUtil;
import com.lyj.blog.util.VarUtil;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

/**
 * @author yingjie.lu
 * @version 1.0
 * @date 2019/12/11 3:31 下午
 */

@Service
public class BlogService {


    @Autowired
    ElasticsearchService elasticsearchService;

    @Value("${isDev}")
    boolean isDev;

    @Autowired
    private DirService dirService;

    @PostConstruct
    public void init() throws IOException {

        //拉取笔记
        GitUtil.gitPull();

        //遍历目录，渲染笔记
        FileUtil.mapDir(new File(VarUtil.notePath),
                new DirFilter(), new BlogFilter(),
                new DirCallBack(), new BlogCallBack(), DirOrFile.Instance);

        //是否清除和添加elasticsearch的数据
        if (!isDev) {
            boolean existIndex = elasticsearchService.existIndex("header");
            if(existIndex){
                elasticsearchService.deleteIndex("header");
            }

            //批量添加header数据到elasticsearch
            elasticsearchService.addHeaderBulk();
        }

        //==========================扫尾工作===========================
        //将list转为map，保存blog的基本信息
        for(int i=0;i< ESBlog.list.size();i++){
            ESBlog esBlog = ESBlog.list.get(i);
            ESBlog blog = ESBlog.blogMap.get(esBlog.getBlogId());
            if(blog==null){
                ESBlog.blogMap.put(esBlog.getBlogId(),esBlog);
            }
        }

        //创建全目录字符串
        VarUtil.dirData= JSON.toJSONString(dirService.getDir("/"));

    }


}
