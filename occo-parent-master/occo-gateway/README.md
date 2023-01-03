# occo-gateway

#### 介绍
spring cloud gateway 响应式网关，基于nacos实现动态网关，基于sentinel实现动态限流，sentinel基于nacos数据持久化

#### 软件架构
版本号：gateway、nacos、sentinel的版本号参考父级项目[occo-parent](http://https://gitee.com/occo/occo-parent)

#### 安装教程

1.  启动注册中心和配置中心Nacos
    文档地址：[Nacos文档](https://nacos.io/zh-cn/docs/what-is-nacos.html)
    下载地址：[Nacos下载](https://github.com/alibaba/nacos/releases)

2.  启动限流服务Sentinel
    文档地址：[Sentinel文档](https://github.com/alibaba/spring-cloud-alibaba/wiki/Sentinel)
    下载地址：[Sentinel下载](https://github.com/alibaba/Sentinel/releases)

3.  启动网关

- 下载occo-gateway启动项目
- 启动gateway
- 在nacos中建《路由》配置：

```
Data ID: gateway_router
Group：DEFAULT_GROUP  （默认的）
配置内容：[{"id":"user-server","uri":"lb://kb-user-center","order":0,"predicates":[{"args":{"pattern":"/user/**"},"name":"Path"}],"filters":[{"name":"StripPrefix","args":{"_genkey_0":"1"}}]},{"id":"sso-server","uri":"lb://kb-user-center","order":0,"predicates":[{"args":{"pattern":"/sso/**"},"name":"Path"}],"filters":[{"name":"StripPrefix","args":{"_genkey_0":"1"}}]}]    （JSON格式）
配置字段说明：       
       ①  id：路由的名字；
       ②  uri：路由的地址，lb表示去那儿一个服务，kb-user-center是nacos注册中心的服务名；
       ③  order：0 ，直接配置为0，为1是请求的url就会带服务名；
       ④  predicates：
            After路由匹配:匹配指定时间之后的请求：        name=After ，_genkey_0=2020-01-20T17:42:47.789-07:00[America/Denver]
            Before路由匹配：匹配指定时间之前的请求：      name=Before，_genkey_0=2020-01-20T17:42:47.789-07:00[America/Denver] 
            Between路由匹配：匹配指定时间区间的请求：     name=Before，_genkey_0=时间一 ，时间二         （PS：两个时间逗号隔开）
            Cookie路由匹配：匹配指定Cookie的值匹配请求：  name=Cookie， _genkey_0=chocolate, ch.p （PS：匹配cookie名为chocolate的值为ch.p的请求）
            Header路由匹配：匹配指定header的值匹配请求：  name=Header， _genkey_0=X-Request-Id, \d+ （PS：匹配头信息X-Request-Id的值匹配\d+的正则表达式）
            Host路由匹配：Host匹配设定的表达式：          name=Host， _genkey_0=**.fuse.org 
            Method路由匹配：匹配HttpMethod方法请求：      name=Method， _genkey_0=GET
            Path路由匹配:请求路径匹配设定表达式的请求：    name=Path， _genkey_0=/foo/{segment}    （PS：请求路径匹配路径匹配表达式/foo/{segment})
            Path路由匹配:请求路径匹配设定值的请求 ：       name=Path， _genkey_0=/foo/**  （PS：请求路径匹配路径以/foo开头)
            Query路由匹配：请求参数匹配设定值或表达式的请求：name=Query， _genkey_0=baz   （PS：请求参数中包含baz参数）
```
4、发布gateway_router配置，每次重新启动gateway都要重新发布一下这个配置，才能被gateway获取到《路由》配置。     
5、在nacos中建《限流》配置如下：    
Data ID: gateway-sentinel    
Group：DEFAULT_GROUP  （默认的）     
配置内容：[{"resource":"user-server","controlBehavior":0,"count":2,"grade":1,"limitApp":"default","strategy":0}]      （JSON格式）   
配置说明：
- resource：资源名，即限流规则的作用对象
- limitApp：流控针对的调用来源，若为 default 则不区分调用来源
- grade：限流阈值类型（QPS 或并发线程数）；0代表根据并发数量来限流，1代表根据QPS来进行流量控制
- count：限流阈值
- strategy：调用关系限流策略
- controlBehavior：流量控制效果（直接拒绝、Warm Up、匀速排队）
- clusterMode：是否为集群模式


#### 整合说明
[整合细节](https://my.oschina.net/bianxin/blog/4259532)

