# 爬虫统计数据

`Java` `JSOUP` `POI`

### 1.使用
> 在 src/main/java/com.chenshuyusc.getStatistic 包下找到 Main 函数运行即可

### 2.结果
#### 2.1 excel 统计
> 文件名：拓扑名_节点个数_日期
>
> 格式：生成 xlsx 格式的 excel 文件

#### 2.2 console 输出
> 输出拓扑名：拓扑图对应的 graphml 网址（拓扑节点数）


### 3.主要类的补充说明
#### 3.1 Data 类
> 首页 dataSet 的表格对应的实体类映射

#### 3.2 GetHtml
> 爬虫并处理信息，内部有两个爬虫的方法

#### 3.3 WriteNodes
> 将数据以邻接矩阵的数据结构写入 excel 中

