package com.weiller.sb2.es;

import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.admin.indices.close.CloseIndexRequest;
import org.elasticsearch.action.admin.indices.close.CloseIndexResponse;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.open.OpenIndexRequest;
import org.elasticsearch.action.admin.indices.open.OpenIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;

import java.io.IOException;

public class EsIndexTests extends EsTest{

    @Test
    public void IndexCreate(){
        CreateIndexRequest request = new CreateIndexRequest("book");
        request.settings(Settings.builder()
                .put("index.number_of_shards",1)
                .put("index.number_of_replicas",0)
                .build())
                .mapping("news",
                "{\n" +
                        "    \"news\":{\n" +
                        "      \"properties\": {\n" +
                        "        \"title\":{\n" +
                        "          \"type\": \"text\",\n" +
                        "          \"analyzer\": \"standard\"\n" +
                        "        },\n" +
                        "        \"content\":{\n" +
                        "          \"type\": \"text\",\n" +
                        "          \"analyzer\": \"english\"\n" +
                        "        }\n" +
                        "      }\n" +
                        "    }\n" +
                        "  }", XContentType.JSON)
                .alias(new Alias("book_alias"));
        try {
            CreateIndexResponse createIndexResponse = client.indices().create(request);
            logger.info("创建索引"+request.index()+ "响应 cacknowledged :{}" ,createIndexResponse.isAcknowledged()) ;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void indexDel(){
        DeleteIndexRequest request= new DeleteIndexRequest("book");
        try {
            DeleteIndexResponse delete = client.indices().delete(request);
            logger.info("删除索引 "+request.indices()[0].toString()+" isAcknowledged: {}",delete.isAcknowledged());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void indexOpen(){
        OpenIndexRequest request = new OpenIndexRequest("book");
        try {
            OpenIndexResponse open = client.indices().open(request);
            logger.info("打开索引 "+request.indices()[0].toString()+" isAcknowledged: {}",open.isAcknowledged());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void indexClose(){
        CloseIndexRequest request = new CloseIndexRequest("book");
        try {
            CloseIndexResponse close = client.indices().close(request);
            logger.info("关闭索引 "+request.indices()[0].toString()+" isAcknowledged: {}",close.isAcknowledged());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
