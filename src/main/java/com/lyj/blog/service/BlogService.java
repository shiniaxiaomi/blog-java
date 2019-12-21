package com.lyj.blog.service;

import com.alibaba.fastjson.JSON;
import com.lyj.blog.ESmodel.ESBlog;
import com.lyj.blog.ESmodel.ESHeader;
import com.lyj.blog.file.*;
import com.lyj.blog.util.GitUtil;
import com.lyj.blog.util.VarUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * blog的service，用于启动博客的渲染
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

    @Value("${blogVisitTimesFilePath}")
    String blogVisitTimesFilePath;

    @Autowired
    private DirService dirService;

    @PostConstruct
    public void init() {
        //拉取笔记
        GitUtil.gitPull();

        //清除变量
        DirOrFile.Instance=new DirOrFile();
        ESBlog.htmlMap.clear();
        ESHeader.map.clear();
        ESBlog.list.clear();
        ESBlog.blogMap.clear();

        //遍历目录，渲染笔记
        FileUtil.mapDir(new File(VarUtil.notePath),
                new DirFilter(), new BlogFilter(),
                new DirCallBack(), new BlogCallBack(), DirOrFile.Instance);

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
        VarUtil.SEOData= dirService.getSEOData();

        try {
            //读取文件中的blog的访问次数
            System.out.println("读取blog的访问次数。。。");
            readVisitTimes();
        } catch (IOException e) {
            System.out.println("blog的访问次数读取失败："+e);
        }

        //是否清除和添加elasticsearch的数据
        try {
            if (!isDev) {
                boolean existIndex = elasticsearchService.existIndex("header");
                if(existIndex){
                    System.out.println("删除elasticsearch索引");
                    elasticsearchService.deleteIndex("header");
                }
                //批量添加header数据到elasticsearch
                System.out.println("添加elasticsearch索引");
                elasticsearchService.addHeaderBulk();
            }
        } catch (IOException e) {
            System.out.println("elasticsearch异常:"+e);
        }
    }

    /**
     * 手动初始化
     * @return 是否初始化成功
     */
    public void initByManual(){
        try {
            //先保存访问次数
            System.out.println("保存blog的访问次数。。。");
            writeVisitTimes();
        } catch (IOException e) {
            System.out.println("blog的访问次数保存失败："+e);
        }

        //正常的初始化
        init();
    }


    /**
     * 读取文件中的blog的访问次数
     * @throws IOException
     */
    public void readVisitTimes() throws IOException {
        boolean exist = isBlogVisitTimeFileExist();
        if(exist){
            List<String> lines = FileUtils.readLines(new File(blogVisitTimesFilePath + "blogVisitTimes"));
            for(int i=0;i<lines.size();i+=2){
                String blogId = lines.get(i);
                int visitTimes = Integer.parseInt(lines.get(i + 1));
                ESBlog esBlog = ESBlog.blogMap.get(blogId);
                if(esBlog!=null){
                    esBlog.setVisitTimes(visitTimes);
                }
            }
        }else{
            //如果文件不存在，则创建
            writeVisitTimes();
        }
    }

    /**
     * 将内存中的blog的访问次数保存到文件
     * @throws IOException
     */
    public void writeVisitTimes() throws IOException {
        Iterator<String> iterator = ESBlog.blogMap.keySet().iterator();
        StringBuilder sb=new StringBuilder();
        while(iterator.hasNext()){
            String blogId = iterator.next();
            ESBlog esBlog = ESBlog.blogMap.get(blogId);
            sb.append(blogId);
            sb.append("\n");
            sb.append(esBlog.getVisitTimes());
            sb.append("\n");
        }

        FileUtils.writeStringToFile(new File(blogVisitTimesFilePath+"blogVisitTimes"),sb.toString());
    }

    private boolean isBlogVisitTimeFileExist(){
        File file = new File(blogVisitTimesFilePath + "blogVisitTimes");
        if(file.exists()){
            return true;
        }else{
            return false;
        }
    }

}
