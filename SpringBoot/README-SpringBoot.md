## 前言
本文档中仅包含项目中SpringBoot相关内容。 <br>
若想看有关Vue的请移步至Vue项目下的README-Vue.md <br>
若想看项目整体说明文档请移步至整个项目下的README.md <br>

**注：
若pull下来的项目出现故障，
请先根据本文档中 故障排查模块 自行排查，
如仍未解决或您的故障并未包含其中，
可以在项目博客中进行留言，
项目负责人将根据具体情况提供相应处理方案
（包括但不限于 将问题升格为issues、将故障写入本文档中 等）**


## 前置环境
### 数据库 
数据库名: test_database <br>
数据表名: user_table <br>
用户名及密码: root <br>

### 相关插件及版本
SpringBoot: v2.3.7 RELEASE <br>
Maven: v3.8 <br>
mysql <br>
mybatis <br>
lombok <br>
...

### 文件编码
均UTF-8

### 访问url
index: http://localhost:8081/ <br>
data: http://localhost:8081/data?id=xxx

## 故障排查(持续更新)

**故障1** *类型：频发，贡献者：项目负责人*<br>
>启动报找不到类<br>
1. 检查maven
2. 请确保项目所在路径所有文件夹均是英文
   【例：E:\Program Files\ceshi\SpringBoot_Vue √，
        E:\Program Files\测试\SpringBoot_Vue ×】