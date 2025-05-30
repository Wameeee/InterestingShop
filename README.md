# InterestingShop 趣买买商城

## 项目介绍

趣买买商城是一个基于 Java Web 的电子商城系统，提供用户注册、登录、浏览商品、购物车、订单管理等功能。

## 技术栈

- Java 8
- Maven
- MyBatis
- JSP/Servlet
- MySQL
- HTML/CSS/JavaScript

## 项目结构

```
InterestingShop/
├── src/                   # 源代码目录
│   ├── cn/interestingshop/
│   │   ├── dao/           # 数据访问层
│   │   ├── entity/        # 实体类
│   │   ├── filter/        # 过滤器
│   │   ├── listener/      # 监听器
│   │   ├── param/         # 参数类
│   │   ├── service/       # 业务逻辑层
│   │   ├── utils/         # 工具类
│   │   └── web/           # Web控制层
│   └── main/resources/    # 资源文件目录
│       ├── mappers/       # MyBatis映射文件
│       ├── log4j.properties # 日志配置
│       └── mybatis-config.xml # MyBatis配置文件
├── web/                   # Web资源目录
│   ├── WEB-INF/           # Web配置目录
│   ├── client/            # 前台页面
│   ├── common/            # 公共资源
│   ├── files/             # 上传文件
│   ├── manager/           # 后台管理页面
│   └── statics/           # 静态资源
└── pom.xml                # Maven项目配置文件
```

## 项目转换说明

本项目已从原有的 JDBC 实现转换为使用 MyBatis 框架，主要修改如下：

1. 添加 Maven 支持，通过 pom.xml 管理依赖
2. 引入 MyBatis 框架，替换原有的 JDBC 实现
3. 为每个实体类创建 Mapper 接口和 XML 映射文件
4. 增加 MyBatis 配置文件和初始化监听器

## 开发说明

1. 使用 MyBatis 进行数据库操作，不再直接使用 JDBC
2. DAO 层使用 Mapper 接口与 XML 映射文件结合的方式
3. 使用 MyBatisUtil 工具类获取 SqlSession 和 Mapper 实例

## 部署说明

1. 确保已安装 Maven
2. 在项目根目录执行`mvn clean package`打包项目
3. 将生成的 war 包部署到 Tomcat 或其他 Servlet 容器中
4. 配置数据库连接参数（在 mybatis-config.xml 中）

## 数据库设置

修改`src/main/resources/mybatis-config.xml`中的数据库连接信息：

```xml
<property name="driver" value="com.mysql.cj.jdbc.Driver"/>
<property name="url" value="jdbc:mysql://localhost:3306/interestingshop?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai"/>
<property name="username" value="your_username"/>
<property name="password" value="your_password"/>
```
