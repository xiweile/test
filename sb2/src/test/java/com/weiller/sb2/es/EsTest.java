package com.weiller.sb2.es;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class EsTest {
    Logger logger = LoggerFactory.getLogger(EsIndexTests.class);

    RestHighLevelClient client;

    @Before
    public void createClient(){
        this.client= new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost",9200,"http")
                ));
    }

    @After
    public void close(){
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
