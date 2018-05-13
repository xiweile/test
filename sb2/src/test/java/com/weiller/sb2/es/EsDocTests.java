package com.weiller.sb2.es;

import org.apache.http.HttpHost;
import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Map;

@SpringBootTest
public class EsDocTests extends EsTest{

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
    public void docCreate() throws IOException {
        XContentBuilder builder = XContentFactory.jsonBuilder()
                .startObject()
                .field("title","Money")
                .field("content","shop and work.")
                .field("time","2018-04-13")
                .array("wards","local","life")
                .endObject();

        IndexRequest indexRequest = new IndexRequest("book","news","6")
                .source(builder);
        IndexResponse index = client.index(indexRequest);
        if(index.getResult() == DocWriteResponse.Result.CREATED){
            logger.info("创建doc : data : {}",index.toString());
        }else if(index.getResult() == DocWriteResponse.Result.UPDATED){
            logger.info("更新doc : data : {}",index.toString());

        }
    }

    @Test
    public void docUpdate() throws IOException {
        XContentBuilder builder = XContentFactory.jsonBuilder()
                .startObject()
                .field("content","shop and work.updated")
                .field("time","2018-04-23")
                .endObject();
        UpdateRequest updateRequest = new UpdateRequest("book","news","6");
        updateRequest.doc(builder);
        client.update(updateRequest);
    }

    @Test
    public  void  docGet() throws IOException {
        GetRequest getRequest = new GetRequest("book_alias","news","1");

        GetResponse getResponse = client.get(getRequest);
        Map<String, Object> source = getResponse.getSource();
        logger.info("查询结果source： {}",source.toString());
    }

    @Test
    public void docDel() throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest("book","news","6");

        DeleteResponse delete = client.delete(deleteRequest);
        if(delete.getResult() == DocWriteResponse.Result.DELETED){
            logger.info("删除成功： {}",delete.getId());
        }
    }

    @Test
    public void bulkOpt() throws IOException {
        XContentBuilder builder = XContentFactory.jsonBuilder()
                .startObject()
                .field("title","A&B")
                .field("content","aa and bb.")
                .field("time","2018-04-13")
                .array("wards","local" )
                .endObject();

        IndexRequest indexRequest = new IndexRequest("book","news","7")
                .source(builder);


        XContentBuilder builder2 = XContentFactory.jsonBuilder()
                .startObject()
                .field("content","shop and work updated 222")
                .field("time","2018-05-3")
                .endObject();
        UpdateRequest updateRequest = new UpdateRequest("book","news","6");
        updateRequest.doc(builder2);

        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.add(updateRequest)
                .add(indexRequest);
        BulkResponse bulk = client.bulk(bulkRequest);
        bulk.forEach(b -> {
            if(b.getOpType() == DocWriteRequest.OpType.CREATE){
                // 创建操作
            }else if(b.getOpType() == DocWriteRequest.OpType.UPDATE){
                // 更新操作
            }

            if(b.isFailed()){
                BulkItemResponse.Failure failure = b.getFailure();

            }
        });
        logger.info("批量操作 ：{}",bulk);
    }


}
