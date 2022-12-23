package com.example.elasticsearch.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.elasticsearch.domain.ArchitectureDto;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
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
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArchitectureService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    // 测试文档的添加
    public void createDoc() throws IOException {

        //CreateDoc5()创建实体方法，可自己实现
        ArchitectureDto architectureDto = new ArchitectureDto();// DocDemo.CreateDoc5();
        // 创建好index请求
        IndexRequest indexRequest = new IndexRequest("architecture_index");
        // 设置索引
        indexRequest.id("5");
        // 设置超时时间（默认）
        indexRequest.timeout(TimeValue.timeValueSeconds(5));
        // 往请求中添加数据
        indexRequest.source(JSON.toJSONString(architectureDto), XContentType.JSON);
        //执行添加请求
        IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println(indexResponse);
    }


    public void getDoc() throws IOException {

        //获得查询索引的请求对象
        GetRequest gerRequest = new GetRequest("architecture_index").id("2");
        //获得文档对象
        GetResponse doc = restHighLevelClient.get(gerRequest, RequestOptions.DEFAULT);
        //获得文档数据
        System.out.println(doc.getSourceAsString());
    }


    public void delDoc() throws IOException {

        //获得删除的索引请求对象
        DeleteRequest delRequest = new DeleteRequest("architecture_index").id("1");
        //删除文档
        DeleteResponse delete = restHighLevelClient.delete(delRequest, RequestOptions.DEFAULT);
        System.out.println(delete.getIndex());
    }

    public void delIndex() throws IOException {

        IndicesClient indices = restHighLevelClient.indices();
        DeleteIndexRequest delReq = new DeleteIndexRequest("architecture_index");
        AcknowledgedResponse delete = indices.delete(delReq, RequestOptions.DEFAULT);
        System.out.println(delete.isAcknowledged());
    }

    public void contextLoads() throws IOException {

        //查询mysql中所有数据
        List<ArchitectureDto> architectures = new ArrayList<>();

        //创建批量处理对象
        BulkRequest bulkRequest = new BulkRequest();

        //循环添加新增处理请求
        for (ArchitectureDto architecture : architectures) {

            String architecturJson = JSON.toJSONString(architecture);
            IndexRequest indexRequest = new IndexRequest("architecture_index").id(architecture.getId() + "").source(architecturJson, XContentType.JSON);
            bulkRequest.add(indexRequest);
        }

        //提交批量处理对象
        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);

        //查看添加状态
        System.out.println(bulk.status());

    }

    /**
     * 查询条件数据    匹配查询不到将字段类似设置为.keyword
     * @throws IOException
     */
    public void searchAll() throws IOException {

        //定义请求对象
        SearchRequest request = new SearchRequest("architecture_index");
        //制定检索条件
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchAllQuery());   //查询所有
//        builder.query(QueryBuilders.termQuery("address","huahexi777"));    //非String 类型查询
//        builder.query(QueryBuilders.termQuery("location.lat",33.2));    //非String 类型查询
//        builder.query(QueryBuilders.matchPhraseQuery("area","高新区"));   //精准查询String
//        builder.query(QueryBuilders.matchQuery("area.keyword","高新区"));   // 不能匹配到
//        builder.query(QueryBuilders.termQuery("area.keyword","高新区"));
//        builder.sort("price", SortOrder.DESC);
        request.source(builder);
        //获得文档对象
        SearchResponse search = restHighLevelClient.search(request, RequestOptions.DEFAULT);
        //获得文档数据
        for (SearchHit hit : search.getHits().getHits()) {

            ArchitectureDto art = JSONObject.parseObject(hit.getSourceAsString(), ArchitectureDto.class);
            System.out.println(JSON.toJSONString(art));
        }
    }

    /**
     * 类似于数据库的 or 查询
     * @throws IOException
     */
    public void searchByBolt() throws IOException {

        //定义请求对象
        SearchRequest request = new SearchRequest("architecture_index");
        //制定检索条件
        SearchSourceBuilder builder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.should(QueryBuilders.matchQuery("price",10));
        boolQueryBuilder.should(QueryBuilders.matchQuery("score",4.6).boost(10));
        builder.query(boolQueryBuilder);    //非String 类型查询
        request.source(builder);
        //获得文档对象
        SearchResponse search = restHighLevelClient.search(request, RequestOptions.DEFAULT);
        //获得文档数据
        for (SearchHit hit : search.getHits().getHits()) {

            ArchitectureDto art = JSONObject.parseObject(hit.getSourceAsString(), ArchitectureDto.class);
            System.out.println(JSON.toJSONString(art));
        }
    }

    /**
     * 查询部分字段
     * @throws IOException
     */
    public void searchByParam() throws IOException {

        //定义请求对象
        SearchRequest request = new SearchRequest("architecture_index");
        //制定检索条件
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchAllQuery());   //查询所有
        String[]  includes = {
                "name","address","price"};
        String[] excludes = {
        };
        /** 会多出一个score字段 默认值都为0 具体原因不详  */
        builder.fetchSource(includes,excludes);
        request.source(builder);
        //获得文档对象
        SearchResponse search = restHighLevelClient.search(request, RequestOptions.DEFAULT);
        //获得文档数据
        for (SearchHit hit : search.getHits().getHits()) {

            ArchitectureDto art = JSONObject.parseObject(hit.getSourceAsString(), ArchitectureDto.class);
            System.out.println(JSON.toJSONString(art));
        }
    }

    /**
     * 范围查询 大于小于
     * @throws IOException
     */
    public void searchByFilter() throws IOException {

        //定义请求对象
        SearchRequest request = new SearchRequest("architecture_index");
        //制定检索条件
        SearchSourceBuilder builder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        //定制查询条件
        boolQueryBuilder.filter (QueryBuilders.rangeQuery("price").gte(10).lte(30));
        builder.query(boolQueryBuilder);    //非String 类型查询
        request.source(builder);

        SearchResponse search = restHighLevelClient.search(request, RequestOptions.DEFAULT);
        for (SearchHit hit : search.getHits().getHits()) {

            ArchitectureDto art = JSONObject.parseObject(hit.getSourceAsString(), ArchitectureDto.class);
            System.out.println(JSON.toJSONString(art));
        }
    }

    /**
     * 迷糊查询
     * @throws IOException
     */
    public void searchByLike() throws IOException {

        //定义请求对象
        SearchRequest request = new SearchRequest("architecture_index");
        //制定检索条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //定制查询条件
        TermQueryBuilder builder = QueryBuilders.termQuery("name.keyword","北京大");//.fuzziness(Fuzziness.ONE);
        searchSourceBuilder.query(builder);
        request.source(searchSourceBuilder);

        SearchResponse search = restHighLevelClient.search(request, RequestOptions.DEFAULT);
        for (SearchHit hit : search.getHits().getHits()) {

            ArchitectureDto art = JSONObject.parseObject(hit.getSourceAsString(), ArchitectureDto.class);
            System.out.println(JSON.toJSONString(art));
        }
    }


}
