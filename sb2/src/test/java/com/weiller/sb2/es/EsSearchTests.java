package com.weiller.sb2.es;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class EsSearchTests extends EsTest{

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

    @Test
    public void metchAll() throws IOException {
        SearchRequest searchRequest = new SearchRequest("book");
        searchRequest
                .source(new SearchSourceBuilder()
                        .query( QueryBuilders.matchAllQuery())
                        .from(0)
                        .size(5)
                ).types("news");
        SearchResponse search = client.search(searchRequest);
        search.getHits().forEach(t ->{
            System.out.println(t.getId());
        });
    }
    @Test
    public void metch() throws IOException {
        SearchRequest searchRequest = new SearchRequest("book");
        searchRequest
                .source(new SearchSourceBuilder()
                        .query(
                                QueryBuilders.matchQuery("content","itself")
                        )
                ).types("news");
        SearchResponse search = client.search(searchRequest);
        search.getHits().forEach(t ->{
            System.out.println(t.getId());
        });
    }
}
