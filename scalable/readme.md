# 系统性能测试报告

#### 实验要求

+ 使用`HAproxy`搭建一个水平扩展的集群系统
+ 使用`Redis`存储管理web应用的多个实例的会话及缓存数据

+ 使用`Gatling`压测工具，测试在多种配置（不同的web实例个数/有无`redis`缓存）下的性能



#### 实验过程

1. 搭建并配置相关环境
2. 改写第一次作业中的项目，使其支持Redis缓存
3. 编写Gatling测试用例，对每种配置进行三次测试，记录有关数据



#### 测试内容

向系统中插入随机生成的300条数据后，用Gatling请求查询姓名含有'a'、院系含有'E'、籍贯含有'a'的记录

```scala
val scn = scenario("Test Stu Mng").exec(
            http("stu_request")
            .get("/stu?name=a&department=E&birthPlace=a")
        )
```

对每种配置的三次测试结果取平均数，一定程度上减小误差



#### 测试结果

**平均响应时间（mean response time）**

<table border="2" bordercolor="black">  
        <tr>  
            <td rowspan="2"><b><center>有无Redis缓存</b></td>  
            <td rowspan="2"><b><center>Web实例数</b></td>  
            <td colspan="3"><b><center>并发请求数 / 时间</b></td> 
        </tr>  
        <tr>  
            <td>10</td> 
            <td>50</td>  
            <td>150</td>  
        </tr>  
        <tr>  
            <td rowspan="3"><b>无</b></td>
            <td>1</td>
            <td>1154.7</td> 
            <td>1665</td> 
            <td>3573</td> 
        </tr>  
        <tr>  
            <td>2</td>
            <td>846.3</td> 
            <td>1117.7</td> 
            <td>2184.3</td> 
        </tr>
        <tr>  
            <td>4</td>
            <td>694.7</td> 
            <td>767.7</td> 
            <td>1592.7</td> 
        </tr>
        <tr>  
            <td rowspan="3"><b>有</b></td>
            <td>1</td>
            <td>918.67</td> 
            <td>1565</td> 
            <td>3000.7</td> 
        </tr>  
        <tr>  
            <td>2</td>
            <td>780.3</td> 
            <td>1004</td> 
            <td>1786.7</td>
        </tr>
        <tr>  
            <td>4</td>
            <td>765.3</td> 
            <td>787</td> 
            <td>1441.3</td> 
        </tr>  
    </table>  

> Gatling输出详见result文件夹，data-\<redis\>-\<webcnt\>-\<reqcnt\>.txt



#### 后记

起初测试时，开启Redis缓存后消耗的时间比未开启时更长，经过分析后得知：

+ 使用了h2数据库的内存模式，无文件系统IO，查询性能高
+ 测试的请求为某个固定id的学生信息，Student表上对id建立了索引
+ 数据总量小

再次测试时，将h2数据库设为存储单个文件，增大了查询的复杂度，开启Redis缓存的性能表现才略强于未开启的情况