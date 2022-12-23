package com.example.elasticsearch.utils;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Locale;

@Slf4j
public class EsHelper {

    @Autowired
    private RestHighLevelClient restHighLevel;//推荐使用

    public Boolean createIndex(String indexName) {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName.toLowerCase(Locale.ROOT));
        createIndexRequest.settings(Settings.builder().put("index.number_of_shards",4)
        .put("index.number_of_replicas",2));
        try {
            XContentBuilder xContentBuilder = XContentFactory.jsonBuilder();
            xContentBuilder.startObject().startObject("properties")
                    .startObject("type").field("type","integer").endObject()
                    .startObject("content").field("type", "text").field("analyzer","ik_max_word").endObject()
                    .endObject().endObject();
            createIndexRequest.mapping(xContentBuilder);
            CreateIndexResponse response = restHighLevel.indices().create(createIndexRequest, RequestOptions.DEFAULT);
            boolean acknowledged = response.isAcknowledged();
            boolean shardsAcknowledged = response.isShardsAcknowledged();
            if (acknowledged || shardsAcknowledged) {
                log.info("创建索引成功！索引名称{}",indexName);
                return Boolean.TRUE;
            }

        } catch ( IOException e) {
            log.info("创建索引失败{}", e);
        }
        return Boolean.FALSE;
    }

    void delExistIndex(String indexName) throws IOException{
        DeleteIndexRequest delRequest = new  DeleteIndexRequest(indexName.toLowerCase(Locale.ROOT));//这里是删除的索引名
        AcknowledgedResponse delet = restHighLevel.indices().delete(delRequest,RequestOptions.DEFAULT);
        System.err.println(delet);
    }

}
