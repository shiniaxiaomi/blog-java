package com.lyj.blog.util;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;

import java.io.IOException;

/**
 * @author yingjie.lu
 * @version 1.0
 * @date 2019/12/12 2:22 下午
 */
public class ElasticseachClientUtil {

    public static RestHighLevelClient client=getClient();

    //创建client
    private static RestHighLevelClient getClient(){
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("127.0.0.1:9200")
                .build();
        return RestClients.create(clientConfiguration).rest();
    }

    //删除索引
    public static void deleteIndex(String index) throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest(index);
        AcknowledgedResponse deleteIndexResponse = client.indices().delete(request, RequestOptions.DEFAULT);
    }

    public static boolean existIndex(String index) throws IOException {
        GetIndexRequest request = new GetIndexRequest("header");
        return client.indices().exists(request, RequestOptions.DEFAULT);
    }

    public static void main(String[] args) {

    }

}
