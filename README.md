项目中的知识点总结：

1.在Junit单元测试中，常用的注解：
- @Test：定义测试方法。
- @BeforeEach：每个测试方法前执行。
- @AfterEach：每个测试方法后执行。
- @BeforeAll：整个测试类开始前执行一次。
- @AfterAll：整个测试类结束后执行一次。
- @ParameterizedTest: 参数化测试。

2.Spring中声明 Bean的注解有哪些，分别用在什么地方呢 ？
- @Component：泛用组件。
- @Service：业务逻辑组件。
- @Repository：数据访问层组件。
- @Controller：控制器组件。
- @Bean: 加在方法上，spring会将方法的返回值， 声明为IOC容器的Bean。

3.数据库中的常见约束：
- 主键（primary key）：唯一标识一行记录。
- 外键（foreign key）：在多表中，保证数据的一致性和完整性。
- 唯一（unique）：列的值必须唯一。
- 非空（not null）：列不能为空。
- 默认（default）：指定默认值。



4.请详细描述char与varchar的区别？ 以及在设计表时，该如何选择？
第一点呢，就是存储方式不同：
  - char：定长字符串，长度是固定的，不管实际存储的字符串长度如何，都会占用固定长度的存储空间。如：char(10) 会始终占用10个字符的空间。
  - varchar：变长字符串，长度不固定。占用的空间与实际存储的字段长度有关。 如：varchar(10) 表示最多可以存储10个字符，如果存储的字符串长度不足10，假设为5，只会占用5个字符空间。
第二点呢，就是性能不同：
  - 对于char，由于其固定长度，操作会快些，但是会存储浪费磁盘空间的问题。
  - 对于varchar，由于长度可变，操作时会相对慢一点，但是可以节省磁盘空间，尤其是存储的数据长度不固定时。



5.请按照你的理解说说什么是控制反转IOC，以及什么依赖注入 DI? 
- IOC：控制反转。对象的创建控制权由程序自身转移到外部（容器），这种思想称为控制反转。
- DI：依赖注入。容器为应用程序提供运行时，所依赖的资源，称之为依赖注入。



6.DQL语句分组查询时，where，group by，having之间的执行顺序是什么样的？ where 与having的区别是什么？
执行顺序：where  ---> group by ----> having
where 与 having 的区别:
- where 是在分组之前，对数据进行过滤，不符合条件的数据，不会参与分组。 where后面不能使用聚合函数。
- having 是在分组之后，对数据进行过滤。 having之后，可以使用聚合函数。



7.Mybatis的映射配置文件定义的规范是什么 
1. XML映射文件的名称与Mapper接口名称一致，并且将XML映射文件和Mapper接口放置在相同包下（同包同名）。
2. XML映射文件的namespace属性为Mapper接口全限定名一致。
3. XML映射文件中sql语句的id与Mapper 接口中的方法名一致，并保持返回类型一致。



8.使用Mybatis的注解，主要是来完成一些简单的增删改查功能。如果需要实现复杂的SQL功能，建议使用XML来配置映射语句。



9.Mybatis 中的 # 与 $ 的区别是什么 ？实际开发中推荐用哪个? 为什么 ?
- #：预编译处理，防止SQL注入，安全。【推荐】
- $：字符串拼接，容易导致SQL注入，不安全。



10.- @RequestParam：用于绑定HTTP请求中的查询参数到方法参数上。
      - 场景1：前端传递参数名与controller方法形参不一致。
      - 场景2：设置controller方法参数的默认值。
      - 场景3：将前端传递的过个参数封装到一个List集合中。如： /emps?ids=1,2,3  ----> @RequestParam List<Integer> ids
  
   - @RequestBody：用于将HTTP请求体中的内容绑定到方法参数上。
      - 应用场景：将json格式的数据，封装到对象/集合中。
      - 使用细节：常用于POST / PUT等方式中。
  
   - @PathVariable：用于将URL路径中的参数绑定到方法参数上。
      - 应用场景：获取URL路径中的动态部分。
      - 使用细节：配合{}中的占位符使用。

    实体类上面要加的注解：
      -@Data 
      -@AllArgsConstructor 全参构造
      -@NoArgsConstructor  无参构造



11.  - GET：读取资源。
     - POST：创建资源。
     - PUT：更新资源。
     - DELETE：删除资源。



12.request是请求体，response是响应体



13.- 内连接：查询两张表交集部分的数据。
   - 外连接：
      - 左外连接：会查询返回所有左表中的数据，以及两张表交集部分的数据。
      - 右外连接：会查询返回所有右表中的数据，以及两张表交集部分的数据。



14.分页查询可以用PageHelper插件 简化代码
  
        1. 设置分页参数(PageHelper)
        
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize()); 获取页码和每页记录数

        2.执行查询
        List<Emp> rows = empMapper.list(empQueryParam);

        3.解析查询结果,并封装
        Page<Emp> p = (Page<Emp>) rows;
        return new PageResult<Emp>(p.getTotal(), p.getResult());

        //1.调用Mapper接口，获取统计数据
        List<Map<String, Object>> list = empMapper.countEmpJobData(); // 这个list储存的是键值对类型的数据
                                                                        
                                                                        
        //2.组装结果并返回
        //因为list里面没有map方法，所以需要先调用stream()方法，再调用map()方法遍历拿到dataMap指定的数据，最后调用toList()方法将结果转换为List
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("num")).toList();



15.请说明什么是事务? 在数据库中如何控制事务，控制事务的SQL语句 ？ 以及事务的四大特性 ？请详细聊聊在项目开发中，你们的事务是如何控制的 ？
      事务：一系列操作要么全部成功，要么全部失败。
      
      控制事务的SQL：
      - 开启事务：start transaction; / begin;
      - 提交事务：commit；
      - 回滚事务：rollback；
      
      四大特性：
      - 原子性（Atomicity）：事务是不可分割的最小单元，要么全部成功，要么全部失败。
      - 一致性（Consistency）：事务完成时，必须使所有的数据都保持一致状态。
      - 隔离性（Isolation）：数据库系统提供的隔离机制，保证事务在不受外部并发操作影响的独立环境下运行。
      - 持久性（Durability）：事务一旦提交或回滚，它对数据库中的数据的改变就是永久的。
      
      事务控制：
      - 项目开发中，控制事务，我们可以在业务方法上加上 @Transactional 注解即可。 @Transactional 可以加载 方法上、类上、接口上。 推荐加在方法上。



16.Mybatis中的动态SQL
- if：根据条件判断是否拼接对应的SQL片段 。
- where：自动处理WHERE关键字 和 并且会自动去除第一个条件前面多余的 AND/OR。
- set: 用在update语句中，用来替换set关键字，并且会自动去除更新字段后面多余的逗号。
- foreach: 循环遍历。
  - collection
  - item
  - sepearator
  - open
  - close


17.如何定义一个全局异常处理器？
   全局异常处理器定义：
  - 类上加的注解：@RestControllerAdvice
  - 方法上加的注解：@ExceptionHandler



18.MySQL数据库中经常使用的函数
- COUNT(): 统计总数量。
- SUM(): 求和。
- AVG(): 平均值。
- MAX(): 最大值。
- MIN(): 最小值。
- now(): 获取当前系统时间。
- if(...): 条件判断。
- ifnull(...): 判断null值的。
- case ... when ... then ... when ... then ... end : 流程控制函数。


19.会话跟踪技术Cookie  与 Session 各自的原理是什么 ?  各自有什么优缺点 ?
Cookie:
原理：
- Cookie 是一种客户端会话跟踪技术。
  - 服务器通过 HTTP 响应头 Set-Cookie 将数据发送给浏览器，浏览器将其保存在本地。
  - 当浏览器再次请求同一服务器时，它会在请求头 Cookie 中将 Cookie 发送给服务器。
优点：
- HTTP协议中支持的技术，提供了一个响应头 Set-Cookie， 一个请求头 Cookie。
缺点：
- 安全性较低，用户可以随意操作/禁用。
- 移动端APP无法直接使用Cookie。
- Cookie不支持跨域操作。

Session：
- Session 是一种服务器端会话跟踪技术，用于存储用户会话所需的信息。底层是基于Cookie的。
  - 服务器为每个会话分配一个唯一的 ID（Session ID），并将该 ID 通过 Cookie 的方式发送给客户端。
优点：
- 更安全，因为数据存储在服务器端。
缺点：
- Cookie的缺点。
- 集群环境下，无法直接使用Session。



20.JWT 由三个部分组成，它们分别是：
1. Header（头部）
  - 表示令牌的类型和所使用的签名算法。
  - 通常格式为 { "typ": "JWT", "alg": "HS256" }。
2. Payload（负载）
  - 通常存储令牌的默认信息，以及自定义的数据。原始数据格式为json，进行了Base64编码后就变成了jwt的第二部分。
3. Signature（签名）
  - 用于验证接收到的 JWT 未被篡改。
  - 生成方式是将 Header、Payload 和一个密钥（通常是私钥或共享密钥）使用指定的算法（如 HMAC SHA-256）进行编码。

          public class JwtUtils {
          
              private static String signKey = "SVRIRUlNQQ=="; // 签名密钥
              private static Long expire = 43200000L;  // 过期时间，单位毫秒
          
              /**
               * 生成JWT令牌
               * @return JWT令牌
               */
              public static String generateJwt(Map<String,Object> claims){
                  String jwt = Jwts.builder()
                          .addClaims(claims)  // 添加负载
                          .signWith(SignatureAlgorithm.HS256, signKey)  // 使用HS256算法和signKey进行签名
                          .setExpiration(new Date(System.currentTimeMillis() + expire))
                          .compact();
                  return jwt;
              }
          
              /**
               * 解析JWT令牌
               * @param jwt JWT令牌
               * @return JWT第二部分负载 payload 中存储的内容
               */
              public static Claims parseJWT(String jwt){
                  Claims claims = Jwts.parser()
                          .setSigningKey(signKey)  // 使用signKey进行签名
                          .parseClaimsJws(jwt)  // 解析JWT令牌
                          .getBody();
                  return claims;
              }
          }

21.拦截器的拦截路径中，/*  与 /** 的区别是什么 ?  那 /emps/* 与  /emps/** 的区别又是什么呢 ?

      -/*：只能匹配一级路径
      -/**：可以匹配任意级路径
      -/emps/*：只会拦截 /emps 目录下的一级路径
      -/emps/**：会拦截所有以 /emps 开始的路径，无论这些路径有多深


22.过滤器 Filter  与 拦截器 Interceptor 之间的区别是什么？

    -接口规范不同：
        - 过滤器Filter是JavaEE规范中提供的技术，要实现Filter接口。
        - 拦截器Interceptor是Spring中提供的技术，要实现HandlerInterceptor接口。
    -拦截范围不同：
        - 过滤器的拦截范围更大，它可以拦截Web服务器中所有的资源。
        - 拦截器只能拦截Spring中的资源（主要是控制器中的方法）。

23.数据库的增删改查：
  insert into  表名 (....,....,...)  value (...,...,...);
  delet ... from 表名;
  update 表名 set ......=....... ，....=..... ;
  select * from 表名;
  




















































































