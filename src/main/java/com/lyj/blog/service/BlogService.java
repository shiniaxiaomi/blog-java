package com.lyj.blog.service;

import com.lyj.blog.file.*;
import com.lyj.blog.util.ElasticseachClientUtil;
import com.lyj.blog.util.GitUtil;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostConstruct
    public void init() throws IOException {

        //拉取笔记
        GitUtil.gitPull();

        //遍历目录，渲染笔记
        FileUtil.mapDir(new File("/Users/yingjie.lu/Documents/note"),
                new DirFilter(), new BlogFilter(),
                new DirCallBack(), new BlogCallBack(), DirOrFile.Instance);

        //是否清除和添加elasticsearch的数据
        if (true) {
            ElasticseachClientUtil.deleteIndex("header");

            //批量添加header数据到elasticsearch
            elasticsearchService.addHeaderBulk();
        }
    }


}
