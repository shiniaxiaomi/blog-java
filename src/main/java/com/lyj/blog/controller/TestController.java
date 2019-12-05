package com.lyj.blog.controller;

import com.lyj.blog.filter.DirFilter;
import com.lyj.blog.filter.FileFilter;
import org.apache.commons.io.FileUtils;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import static org.elasticsearch.action.support.WriteRequest.RefreshPolicy.IMMEDIATE;

/**
 * @author yingjie.lu
 * @version 1.0
 * @date 2019/11/28 4:49 下午
 */
@RestController
@RequestMapping
public class TestController {

    @Autowired
    RestHighLevelClient client;

    //添加数据
    @RequestMapping("indexTest")
    public String indexTest(String id) throws IOException {
        IndexRequest request = new IndexRequest("posts", "doc", id)
                .source("user","luyingjie","blog","...")
                .setRefreshPolicy(IMMEDIATE);

        IndexResponse index = client.index(request, RequestOptions.DEFAULT);
        System.out.println(index.getResult());
        return "success";
    }

    //获取数据
    @RequestMapping("getTest")
    public String getTest() throws IOException {
        GetRequest request = new GetRequest("posts", "doc", "20");
        GetResponse document = client.get(request, RequestOptions.DEFAULT);
        Iterator<String> iterator = document.getSource().keySet().iterator();
        while(iterator.hasNext()){
            System.out.println(document.getSource().get(iterator.next()));
        }

        return "success";
    }

    //根据正则获取数据
    @RequestMapping("searchTest")
    @ResponseBody
    public SearchHits searchTest(String name) throws IOException {

        //构造查询条件
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.regexpQuery("user",".*"+name+".*"));//使用正则匹配查询
        sourceBuilder.from(0);
        sourceBuilder.size(5);

        //指定查询文档
        SearchRequest searchRequest = new SearchRequest().indices("posts").source(sourceBuilder);

        //通过client进行查询
        SearchResponse search = client.search(searchRequest, RequestOptions.DEFAULT);

        return search.getHits();
    }

    //删除索引
    @RequestMapping("deleteIndexTest")
    @ResponseBody
    public String deleteIndexTest() throws IOException {

        //构造删除索引请求
        DeleteIndexRequest request = new DeleteIndexRequest("customer");

        //通过client进行查询
        AcknowledgedResponse delete = client.indices().delete(request, RequestOptions.DEFAULT);
        System.out.println(delete);
        return "success";
    }


    public static void main(String[] args) throws IOException {


        FileUtils.listFiles(new File("/Users/yingjie.lu/Documents/note"),new FileFilter(),new DirFilter());



    }

}
