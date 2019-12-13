package com.lyj.blog.util;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;

/**
 * @author yingjie.lu
 * @version 1.0
 * @date 2019/12/12 2:22 下午
 */

public class ElasticseachClientUtil {

//    public static RestHighLevelClient client=getClient();

    //创建client
    private static RestHighLevelClient getClient(){
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("127.0.0.1:9200")
                .build();
        return RestClients.create(clientConfiguration).rest();
    }

    public static void main(String[] args) {

    }

}
