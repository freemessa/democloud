package com.example.elasticsearch;

import com.alibaba.fastjson.JSONObject;
import com.example.elasticsearch.domain.ArchitectureDto;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
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
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;


@SpringBootTest
@Slf4j
class ElasticsearchApplicationTests {

    @Resource
    private ElasticsearchOperations elasticsearchOperations;

    @Autowired
    private RestHighLevelClient restHighLevelClient;//推荐使用


    @Test
    void contextLoads() {
    }

    @Test
    public void createIndex(){
        CreateIndexRequest request=new CreateIndexRequest("test");
        request.settings(
                Settings.builder().put("index.number_of_shards",5)
                        .put("index.number_of_replicas",1));
    }
    @Test
    public  void addObject(){
        ArchitectureDto blog = new ArchitectureDto();
        blog.setId("1502988598260113419L");
        blog.setAddress("如何获取es中数据");
        blog.setName("put方法获取。。。。。。");
        blog.setDescription("es是比较流行的一款搜索引擎");
        blog.setPrice(1000);
        blog.setScore(5000);
        //BlogInfo blogInfo = BeanCopyUtils.copyObject(blog, BlogInfo.class);
        elasticsearchOperations.save(blog);
    }

    /***
     * 关键字查询
     */
    @Test
    public void findObject(){
        // 关键字查询，查询性为“女"的所有记录
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("title"/*字段名*/, "hello"/*值*/);

        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(termQueryBuilder);
        NativeSearchQuery nativeSearchQuery = nativeSearchQueryBuilder.build();
        SearchHits<Map> search =  this.elasticsearchOperations.search(nativeSearchQuery, Map.class, IndexCoordinates.of("blog-search"/*索引名*/));
        System.out.println(search);
    }

    /*****
     * BoolQueryBuilder主要方法
     *
     * must（QueryBuilder）：必须满足的条件
     * should（QueryBuilder）：可能满足的条件
     * mustNot（QueryBuilder）：必须不满足的条件
     * minimumShouldMatch（x）：设置在可能满足的条件中，至少必须满足其中x条
     */
    @Test
    public void findBoolean(){
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        // 查询必须满足的条件
        boolQueryBuilder.must(QueryBuilders.termQuery("content", "es"));

        // 查询可能满足的条件
        boolQueryBuilder.should(QueryBuilders.termQuery("title", "hello"));

        // 设置在可能满足的条件中，至少必须满足其中1条
        boolQueryBuilder.minimumShouldMatch(1);

        // 必须不满足的条件
        // boolQueryBuilder.mustNot(QueryBuilders.termQuery("content", 8));

        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(boolQueryBuilder);
        NativeSearchQuery nativeSearchQuery = nativeSearchQueryBuilder.build();
        SearchHits<Map> search= elasticsearchOperations.search(nativeSearchQuery, Map.class, IndexCoordinates.of("blog-search"));//索引名称
        System.out.println(search);

    }

    /****
     * 查询指定大小（int、double …），指定日期数据时可以使用RangeQueryBuilder
     *
     * gte：大于等于
     * lte：小于等于
     * gt ：大于
     * lt ：小于
     * format：指定日期格式
     */
    @Test
    public void rangFind(){
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("thumbs");
        // 大于等于
        rangeQueryBuilder.gte(10);
        // 小于等于
        rangeQueryBuilder.lte(1500);

        RangeQueryBuilder rangeQueryBuilder1 = QueryBuilders.rangeQuery("views");
        // 大于
        rangeQueryBuilder1.gt(7);
        // 小于
        rangeQueryBuilder1.lt(6000);

        RangeQueryBuilder rangeQueryBuilder2 = QueryBuilders.rangeQuery("createTime");
        rangeQueryBuilder2.gte("2022-06-01");
        rangeQueryBuilder2.lte("2022-06-30");
        rangeQueryBuilder2.format("yyyy-MM-dd");

        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(rangeQueryBuilder);
        nativeSearchQueryBuilder.withQuery(rangeQueryBuilder1);
        //nativeSearchQueryBuilder.withQuery(rangeQueryBuilder2);

        NativeSearchQuery nativeSearchQuery = nativeSearchQueryBuilder.build();
        SearchHits<Map> search= elasticsearchOperations.search(nativeSearchQuery, Map.class, IndexCoordinates.of("blog-search"));
        System.out.println(search);

    }

    @Test
    public void deleteIndex(){
        elasticsearchOperations.delete("1502988598260113419L",ArchitectureDto.class);
    }
//============================

    /****
     * 过滤查询
     * query: 精确查询，查询计算文档的得分，并根据文档的得分进行返回
     * filter query：过滤查询，用来在大量数据中筛选出本地查询的相关数据，不会计算文档得分，经常使用
     *              filter query 结果进行缓存
     * 注意：一旦使用query 和filter query， es会优先使用filter query，然后再使用query
     */
    @Test
    public  void findFilter(){
        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.matchAllQuery())
                .postFilter(QueryBuilders.rangeQuery("thumbs").gt(0).lte(1500)); // 指定过滤条件

        searchRequest.source(sourceBuilder);

        SearchResponse response = null;
        try {
            response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("记录总条数: " + response.getHits().getTotalHits().value);
        System.out.println("记录的最大得分: " + response.getHits().getMaxScore());

        // 所有的文档信息
        SearchHit[] hits = response.getHits().getHits();
        for (SearchHit hit : hits) {
            System.out.println("id: " + hit.getId() + ",source: " + hit.getSourceAsString());
        }
    }

    /***
     * 分页查询并高亮显示结果
     */
    @Test
    public void pageFind(){
        //指定查询索引
        SearchRequest searchRequest=new SearchRequest("blog-search");
        SearchSourceBuilder sourceBuilder=new SearchSourceBuilder();
        //创建高亮
        HighlightBuilder highlightBuilder=new HighlightBuilder();
        // 高亮的字段(只有类型为text的才能进行分词)
        highlightBuilder.requireFieldMatch(false).field("title")
                .preTags("<span style='color:red;'>")
                .postTags("</span>");
        // 查询条件拼接
        sourceBuilder.query(QueryBuilders.termQuery("title", "es"))
                .from(0) // 起始页
                .size(2) // 每页的大小
                .sort("thumbs", SortOrder.DESC) // 排序规则
                .fetchSource(new String[]{}, new String[]{}) // 都是数组， 参数1：需要返回的字段(不填默认返回所有)  参数2：需要排序的字段
                .highlighter(highlightBuilder); // 高亮
        searchRequest.source(sourceBuilder);
        SearchResponse response=null;
        try {
            response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        }catch (IOException e){
            throw new RuntimeException(e);
        }


        System.out.println("记录总条数: " + response.getHits().getTotalHits().value);
        System.out.println("记录的最大得分: " + response.getHits().getMaxScore());

        // 所有的文档信息
        SearchHit[] hits = response.getHits().getHits();
        for (SearchHit hit : hits) {
            System.out.println("id: " + hit.getId() + ",source: " + hit.getSourceAsString());

            // 获取高亮的字段
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            if (highlightFields.containsKey("description")){
                System.out.println("description 高亮的结果 " + highlightFields.get("description").getFragments());
            }
            if (highlightFields.containsKey("title")){
                System.out.println("description 高亮的结果 " + highlightFields.get("title").getFragments());
            }
        }
    }

    /****
     *   // 1.关键字查询
     *         // query(QueryBuilders.termQuery("title", "小浣熊"));
     *         // 2.range 范围查询
     *         // query(QueryBuilders.rangeQuery("price").gt(0).lte(10));
     *         // 3.prefix前缀查询
     *         // query(QueryBuilders.prefixQuery("title", "小浣"));
     *         // 4.wildcard 通配符查询 ? 一个字符， * 任意多个字符
     *         // query(QueryBuilders.wildcardQuery("title", "烤冷*"));
     *         // 5.ids 多个指定   id 查询
     *         // query(QueryBuilders.idsQuery().addIds("1").addIds("3"));
     *         // 6.multi_query 多字段查询
     *         // query(QueryBuilders.multiMatchQuery("好吃", "title","description"));
     */
    @Test
    public  void keyWordFind(){
        QueryBuilder queryBuilder= QueryBuilders.termQuery("title", "es");
        // 指定查询的索引
        SearchRequest searchRequest = new SearchRequest("blog-search");
        // 构建查询对象
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        // 关键词查询
        // sourceBuilder.query(QueryBuilders.termQuery("price", 10));
        // sourceBuilder.query(QueryBuilders.termQuery("description", "浣熊"));
        sourceBuilder.query(queryBuilder);
        // 构建查询请求
        searchRequest.source(sourceBuilder);
        SearchResponse response = null;
        try {
            response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 获取最大得分和总条数
        System.out.println("总条数 = " + response.getHits().getTotalHits().value);
        System.out.println("最大的得分 = " + response.getHits().getMaxScore());
        // 获取所有符合条件的文档信息
        SearchHit[] hits = response.getHits().getHits();
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }
    }

    @Test
    public void getAll(){
        // 指定查询的索引
        SearchRequest searchRequest = new SearchRequest("blog-search");
        // 构建查询条件对象
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        // 指明查询所有
        sourceBuilder.query(QueryBuilders.matchAllQuery());
        // 指明查询条件
        searchRequest.source(sourceBuilder);
        SearchResponse response = null;
        try {
            response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 查询总条数以及最大得分
        System.out.println("总条数 = " + response.getHits().getTotalHits().value);
        System.out.println("最大得分 = " + response.getHits().getMaxScore());

        // 查询到的详细的文档信息
        SearchHit[] hits = response.getHits().getHits();
        for (SearchHit hit : hits) {
            String id = hit.getId();
            System.out.println("id: " + id + " 文档内容: " + hit.getSourceAsString());
        }
    }
    @Test
    public  void findById(){
        GetRequest getRequest = new GetRequest("blog-search", "1502988598260113409");
        GetResponse response = null;
        try {
            response = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("response.getId() = " + response.getId());
        System.out.println("source = " + response.getSourceAsString());
    }
    @Test
    public  void deleteById(){
        // 参数1: 删除请求对象
        // 参数2：请求配置对象
        DeleteResponse response =
                null;
        try {
            response = restHighLevelClient.delete(new DeleteRequest("blog-search", "1502988598260113409"), RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(response.status());
    }
    @Test
    public void addBlog(){ //不建议使用
        // 指定在哪个索引下创建文档
        IndexRequest indexRequest = new IndexRequest("blog-search");
        ArchitectureDto blog = new ArchitectureDto();
        blog.setId("1502988598260113419L");
        blog.setAddress("如何获取es中数据");
        blog.setName("put方法获取。。。。。。");
        blog.setDescription("es是比较流行的一款搜索引擎");
        blog.setPrice(1000);
        blog.setScore(5000);
        indexRequest
                .id("3") // 指定创建文档的id号
                .source(JSONObject.toJSON(blog), ArchitectureDto.class);
        // 参数1：索引请求对象，  参数2:请求配置对象
        IndexResponse response = null;
        try {
            response = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // 查看添加文档的状态
        System.out.println(response.status());
    }

    @Test
    public  void updateInfo(){//不建议使用
        // 参数1：更新的是哪个索引的文档    参数2：更新的具体文档id
        UpdateRequest updateRequest = new UpdateRequest("blog-search", "2");
        // doc()表示在原数据上进行修改信息
        updateRequest.doc("{\n" +
                "    \"views\":2500\n" +
                "  }", XContentType.JSON);
        // 参数1：更新的请求对象
        // 参数2：请求配置对象
        UpdateResponse response = null;
        try {
            response = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(response.status());
    }


    /**
     * 删除文档信息
     */
    @Test
    public void deleteDocument() {
        try {
            // 创建删除请求对象
            DeleteRequest deleteRequest = new DeleteRequest("blog-search", "_doc", "OvKEgn8Btm9EUK0M8ZZq");
            // 执行删除文档
            DeleteResponse response = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
            log.info("删除状态：{}", response.status());
        } catch (IOException e) {
            log.error("", e);
        }
    }

/*
    @Test
    public void getBlogHomePage() {
        QueryPageBean queryPageBean = new QueryPageBean();
        queryPageBean.setCurrentPage(1);
        queryPageBean.setPageSize(3);
        queryPageBean.setQueryString("页面展示如下");
        Page<BlogVO> homePage = blogService.findHomePage(queryPageBean);
        System.out.println(homePage.getRecords());
    }

 */

}
