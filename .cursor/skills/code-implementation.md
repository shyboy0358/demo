# Skill: 代码编写

## 触发场景
当需求解析完成，进入编码实现阶段时使用此 skill。

## 项目架构速查

```
src/main/java/com/example/demo/
├── config/          # SecurityConfig, GlobalExceptionHandler
├── controller/      # @RestController, 返回 ApiResult<T>
├── service/         # 接口定义
│   └── impl/        # @Service 实现, @Transactional
├── mapper/          # @Mapper MyBatis 接口
├── entity/          # 数据库实体, implements Serializable
├── dto/             # API 传输对象
└── enums/           # 枚举类型

src/main/resources/
├── mapper/          # MyBatis XML 映射文件
├── schema.sql       # DDL (CREATE TABLE)
├── data.sql         # 种子数据 (INSERT)
└── application.properties
```

## 编码步骤（严格按顺序）

### Step 1: Entity

```java
package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

public class XxxEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    // 业务字段...
    private Date createTime;
    private Date updateTime;
    // getter/setter（手写，无 Lombok）
}
```

### Step 2: Enum（如需要）

```java
package com.example.demo.enums;

public enum XxxType {
    TYPE_A("描述A"),
    TYPE_B("描述B");
    
    private final String description;
    XxxType(String description) { this.description = description; }
    public String getDescription() { return description; }
}
```

### Step 3: Mapper 接口

```java
package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface XxxMapper {
    List<Xxx> findAll();
    Xxx findById(@Param("id") Long id);
    int insert(Xxx entity);
    int update(Xxx entity);
    int deleteById(@Param("id") Long id);
}
```

### Step 4: Mapper XML

文件位置: `src/main/resources/mapper/XxxMapper.xml`

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.example.demo.mapper.XxxMapper">
    <resultMap id="xxxMap" type="com.example.demo.entity.Xxx">
        <id column="id" property="id"/>
        <result column="xxx_name" property="xxxName"/>
        <!-- 更多字段... -->
    </resultMap>
    <!-- SQL 语句... -->
</mapper>
```

**注意**: H2 不支持 MySQL `COMMENT` 语法，DDL 不要加 `COMMENT`。

### Step 5: Service 接口 + 实现

```java
// service/XxxService.java — 纯接口
// service/impl/XxxServiceImpl.java — @Service, 构造器注入, @Transactional
```

### Step 6: DTO（如需要）

用于复杂请求/响应的数据封装。简单场景直接用 Entity。

### Step 7: Controller

```java
@RestController
@RequestMapping("/api/xxx")
public class XxxController {
    // 构造器注入 Service
    // 返回 ApiResult.success(data) 或 ApiResult.error(code, msg)
}
```

### Step 8: 更新基础设施

1. **schema.sql** — 在文件末尾追加新表的 `CREATE TABLE`（注意 `DROP TABLE IF EXISTS` 在前面的顺序要考虑外键依赖）
2. **data.sql** — 追加种子数据
3. **SecurityConfig** — 如果新增了非 `/api/` 前缀的路径，添加 `.antMatchers(...).permitAll()`

### Step 9: 立即编译验证

```bash
JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64 ./mvnw compile
```

编译不过就立刻修，不要堆积到测试阶段。

## 关键参考文件

开始编码前，建议先读取以下文件了解现有模式:
- `src/main/java/com/example/demo/entity/MemberLevel.java` — Entity 示例
- `src/main/java/com/example/demo/mapper/MemberLevelMapper.java` — Mapper 接口示例
- `src/main/resources/mapper/MemberLevelMapper.xml` — XML 映射示例
- `src/main/java/com/example/demo/service/impl/MemberLevelServiceImpl.java` — Service 实现示例
- `src/main/java/com/example/demo/controller/MemberLevelController.java` — Controller 示例
- `src/main/java/com/example/demo/dto/ApiResult.java` — 统一响应体
