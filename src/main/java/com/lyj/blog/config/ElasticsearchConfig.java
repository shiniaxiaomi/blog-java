package com.lyj.blog.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;

/**
 * @author yingjie.lu
 * @version 1.0
 * @date 2019/12/4 10:17 上午
 */

@Configuration
public class ElasticsearchConfig {

    @Value("${elasticsearch.domin}")
    private String domain;


    //配置client客户端
    @Bean
    RestHighLevelClient client() {

        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(domain+":9200")
                .build();

        return RestClients.create(clientConfiguration).rest();
    }
}
