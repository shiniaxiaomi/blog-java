package com.lyj.blog.service;

import com.lyj.blog.ESmodel.ESBlog;
import com.lyj.blog.ESmodel.ESHeader;
import com.lyj.blog.util.VarUtil;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.MultiSearchRequest;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.Randomness;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

/**
 * @author yingjie.lu
 * @version 1.0
 * @date 2019/12/11 12:50 下午
 */

@Service
public class ElasticsearchService {

    @Autowired
    RestHighLevelClient client;


    //初始化所有header数据
    public void addHeaderBulk() throws IOException {
        //创建index请求
        BulkRequest request = new BulkRequest();

        Iterator<String> iterator = ESHeader.map.keySet().iterator();
        while(iterator.hasNext()){
            List<ESHeader> headerList = ESHeader.map.get(iterator.next());
            for(ESHeader header:headerList){
                //添加数据
                request.add(new IndexRequest("header", "doc", String.valueOf(Randomness.get().nextInt()))
                        .source(XContentType.JSON, "headerName", header.getHeaderName(),"blogId",header.getBlogId(),"level",header.getLevel()));
            }
        }

        //通过client创建批量添加索引
        BulkResponse bulkResponse = client.bulk(request, RequestOptions.DEFAULT);
        System.out.println(bulkResponse);
    }

    //初始化所有blog数据
    public void addBlogBulk() throws IOException {
        //创建index请求
        BulkRequest request = new BulkRequest();

        Iterator<ESBlog> iterator = ESBlog.list.iterator();
        while(iterator.hasNext()){
            ESBlog next = iterator.next();
            request.add(new IndexRequest("blog", "doc", String.valueOf(Randomness.get().nextInt()))
                        .source(XContentType.JSON,
                                "headerName", next.getHeaders(),
                                "blogText",next.getBlogText(),
                                "blogName",next.getBlogName().substring(0,next.getBlogName().length()-3),
                                "url",next.getUrl().substring(VarUtil.getNotePathLength(),next.getBlogName().length()-3)));
        }

        //通过client创建批量添加索引
        BulkResponse bulkResponse = client.bulk(request, RequestOptions.DEFAULT);
        System.out.println(bulkResponse);
    }


    //查询所有的header
    public SearchHit[] searchAllHeader(String keyword) throws IOException {
        //构造查询条件
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.regexpQuery("headerName",".*"+keyword+".*"));//使用正则匹配查询
        sourceBuilder.from(0);
        sourceBuilder.size(20);

        //指定查询文档
        SearchRequest searchRequest = new SearchRequest().indices("header").source(sourceBuilder);

        //通过client进行查询
        SearchResponse search = client.search(searchRequest, RequestOptions.DEFAULT);
        return search.getHits().getHits();
    }


    public SearchHit[] searchAll(String keyword) throws IOException {
        //构造查询条件
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        MatchQueryBuilder blogText = QueryBuilders.matchQuery("blogText", keyword);
        MatchQueryBuilder blogName = QueryBuilders.matchQuery("blogName", keyword);
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .must(blogText)
                .should(blogName);
        sourceBuilder.query(queryBuilder);

        sourceBuilder.from(0);
        sourceBuilder.size(5);

        //指定查询文档
        SearchRequest searchRequest = new SearchRequest().indices("blog").source(sourceBuilder);

        //通过client进行查询
        SearchResponse search = client.search(searchRequest, RequestOptions.DEFAULT);
        return search.getHits().getHits();
    }
}
