package com.lyj.blog;

import org.apache.http.HttpHost;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.Randomness;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class BlogApplicationTests {

    @Test
    void contextLoads() {
    }


    public static void main(String[] args) throws IOException {

        search();

    }


    public static void index() throws IOException {
        //创建客户端请求
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));

        //创建index请求
        IndexRequest request = new IndexRequest("posts", "doc", "3");//指定对应的index，type和id

        //添加数据方式一
//        String jsonString = "{" +
//                "\"user\":\"kimchy\"," +
//                "\"postDate\":\"2013-01-30\"," +
//                "\"message\":\"trying out Elasticsearch\"" +
//                "}";
//        IndexRequest source = request.source(jsonString, XContentType.JSON);
        //添加数据方式二
        IndexRequest source = request.source("user", "aaa", "postDate", new Date(), "message", "hello");

        //通过client创建index并添加一个文档
        IndexResponse index = client.index(source, RequestOptions.DEFAULT);
        System.out.println(index);

        //关闭连接
        client.close();
    }

    //批量创建
    public static void multiIndex() throws IOException {
        //创建客户端请求
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));

        //创建index请求
        BulkRequest request = new BulkRequest();
        //添加数据
        request.add(new IndexRequest("posts", "doc", String.valueOf(Randomness.get().nextInt()))
                .source(XContentType.JSON,"field", "foo"));
        request.add(new IndexRequest("posts", "doc", String.valueOf(Randomness.get().nextInt()))
                .source(XContentType.JSON,"field", "bar"));
        request.add(new IndexRequest("posts", "doc", String.valueOf(Randomness.get().nextInt()))
                .source(XContentType.JSON,"field", "baz"));

        //通过client创建批量添加索引
        BulkResponse bulkResponse = client.bulk(request, RequestOptions.DEFAULT);
        System.out.println(bulkResponse);

        //关闭连接
        client.close();
    }

    public static void get() throws IOException {
        //创建客户端请求
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));

        //创建get请求
        GetRequest request = new GetRequest("posts", "doc", "1");

        //通过client进行查询
        GetResponse getResponse = client.get(request, RequestOptions.DEFAULT);
        System.out.println(getResponse);

        //关闭连接
        client.close();
    }

    public static void delete() throws IOException {
        //创建客户端请求
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));

        //创建delete请求
        DeleteRequest request = new DeleteRequest("posts", "doc", "1");

        //通过client进行删除
        DeleteResponse deleteResponse = client.delete(request, RequestOptions.DEFAULT);
        System.out.println(deleteResponse);

        //关闭连接
        client.close();

    }

    public static void update() throws IOException {

        //创建客户端请求
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));

        //创建update请求
        UpdateRequest request = new UpdateRequest("posts", "doc", "2");

        //在请求中添加要修改的内容
        request.doc("updated", new Date(),"reason","i don't know");

        //通过client进行更改
        UpdateResponse update = client.update(request, RequestOptions.DEFAULT);
        System.out.println(update);

        //关闭连接
        client.close();
    }


    public static void search() throws IOException {

        //创建客户端请求
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));

        //构造查询条件
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.regexpQuery("user",".*lu.*"));//使用正则匹配查询
        sourceBuilder.from(0);
        sourceBuilder.size(5);

        //指定查询文档
        SearchRequest searchRequest = new SearchRequest().indices("posts").source(sourceBuilder);

        //通过client进行查询
        SearchResponse search = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(search);

        //关闭连接
        client.close();


    }

}
