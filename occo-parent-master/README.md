# occo

#### 介绍
这是一个反应式的spring驱动微服务架构，目前还是项目初期，正则逐步完善中。

#### 软件架构
| 相关技术          | 选型        |
|---------------|-----------|
| JDK           | 1.8       |
| 容器            | netty     |
| MAVEN         | 3.6.3     |
| 关系数据库         | mysql     |
| spring boot   | 2.3.0 |
| spring cloud  | 未定        |
| cloud alibaba | 未定        |


#### 项目说明

<table cellpadding="1" cellspacing="1" style="width:600px">
	<thead>
		<tr>
			<th scope="col">项目</th>
			<th scope="col">说明</th>
			<th scope="col">传送门</th>
			<th scope="col">备注</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td><span style="background-color:#ffffff; color:#40485b">occo-parent</span></td>
			<td><span style="background-color:#ffffff; color:#40485b">父级、全局版本控制器</span></td>
			<td><a href="https://gitee.com/occo/occo-parent" target="_blank">https://gitee.com/occo/occo-parent</a></td>
			<td>待完善</td>
		</tr>
		<tr>
			<td><span style="background-color:#f6f8fa; color:#40485b">occo-common</span></td>
			<td><span style="background-color:#f6f8fa; color:#40485b">全局工具类、枚举、常量等</span></td>
			<td><a href="https://gitee.com/occo/occo-common" target="_blank">https://gitee.com/occo/occo-common</a></td>
			<td>待完善</td>
		</tr>
		<tr>
			<td><span style="background-color:#ffffff; color:#40485b">occo-gateway</span></td>
			<td>
			<p><span style="background-color:#ffffff; color:#40485b">网关：<br />
			&nbsp; &nbsp; 基于nacos实现动态网关和动态权重</span><br />
			<span style="background-color:#ffffff; color:#40485b">&nbsp; &nbsp; 基于sentinel实现动态限流<br />
			&nbsp; &nbsp; sentinel基于nacos数据持久化</span></p>
			</td>
			<td><a href="https://gitee.com/occo/occo-gateway" target="_blank">https://gitee.com/occo/occo-gateway</a></td>
			<td>可用</td>
		</tr>
		<tr>
			<td><span style="background-color:#f6f8fa; color:#40485b">occo-sso</span></td>
			<td>SSO：反应式单点登录</td>
			<td><a href="https://gitee.com/occo/occo-sso" target="_blank">https://gitee.com/occo/occo-sso</a></td>
			<td>待完善</td>
		</tr>
	</tbody>
</table>

