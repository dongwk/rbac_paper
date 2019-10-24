RBAC 项目

# app_inter 对移动端的接口类，名字后期调整
# app_util 各工具类
# app_common 暂未定
# app_core 暂未定
# app_manage 后台管理
# app_web web 项目
# app_api api


http://blog.csdn.net/painsonline/article/details/7183613/
参见相关图解：相关材料/rbac图解.jpg

文件表暂时不需求，暂时还没想好应用场景
功能操作表不需要父操作ID，暂时没想好应用场景
权限表不太好描述，一般的系统性质相当于角色表，不需要权限表
权限类型描述参照文章说明，我也会建
用户组暂时也没想好应用场景，需要
用户组应该是

用户组解释
这样一来，用户拥有的所有权限，就是用户个人拥有的权限与该用户所在用户组拥有的权限之和。
当用户的数量非常大时，要给系统每个用户逐一授权（授角色），是件非常烦琐的事情。这时，就需要给用户分组，每个用户组内有多个
用户。除了可给用户授权外，还可以给用户组授权。这样一来，用户拥有的所有权限，就是用户个人拥有的权限与该用户所在用户组拥有的权限之和。（下图为用户组、用户与角色三者的关联关系）

20180109
感觉页面元素表和功能表可以用一张表来用

可以建角色表和3张基础表建一套，独立出权限表

角色表和3张基础表可以是另一套逻辑，和这个不冲突

又来用户组的解释
如果有用户组了，为什么还要给用户关联角色表呢。个人理解如果有用户组的话，直接将角色关联给用户组，在系统中直接将用户添加至用户组中，该用户不就得到了这个角色下的一些权限了吗？
回复SDN_SUPERUSER：嗯，通常理解是这样的，但是又很多特殊情况，需要给特殊的人设置权限，这样的话就要这么做了。如果你研究过win server 2003 或者 Linux ,里面有一个叫做“权限的累积性” 就是“个人的权限”=“用户的权限”+“用户组的权限” ，就了解这个了

这个问题不好，但回答可以
好文，但是这样这不是太啰嗦了？用户组和角色的设计是否重复？
回复fusionc：角色表与用户组是不冲突的，拿一个公司来说把，用户组是部门，角色表是职位，用户表就是员工
https://github.com/macrozheng/mall

想法
1、
做两套逻辑
一套是现有这个
一套是我自己想的
    简单一些
    菜单，页面，功能合成一张表

2、
去掉权限表，直接角色表和资源表关联，和想法1想结合
3、
总共是3套逻辑，想法1和想法2，列出来就是
角色-权限-菜单、元素、功能
角色-菜单、元素、功能
角色-权限-合成一张表
角色-合成一张表
4、
合成一张表肯定有类型的，类似菜单类型，元素

url 用正则来考虑，有些是带*的要特殊处理，/user/id

应该有个菜单表和页面元素表的应该有关系

mybatis 可能会继承一个第三方类  mybatis-plus 文档类 http://baomidou.oschina.io/mybatis-plus-doc/#/
service 也想继承一个第三方类
controller 也想继承一个第三方类

可能要用spring boot

可能需要转变下思路，先看成熟的spring boot项目怎么搭建的，先参照搭建一般
后期再优化

20180313 使用 mybatis-plus 增加 mybatis 的使用

jar 包先随便填，后期再整理清楚

想实现一个绝对简单的开发方式，所有公共的列表等剔除出来
错误状态码到时候可能需要再完善 ErrorPageConfigurar
错误状态码可能未来会支持多种场景，比如 500，json or html, pc 端 or 移动端，ErrorPageController

后期可以使用相关的免费的 bootstrap 模板，
https://blog.csdn.net/li396864285/article/details/78095814

后台参考
https://www.oschina.net/project/tag/464/backend
目前参考
https://www.oschina.net/p/guns

1、spring boot log4j2
2、spring boot spring mvc 环境有问题，一直再调试，搭建spring boot 1.4.0 现在的配置是可以的，其实根本问题已经查到了，
不是默认的项目问题，是maven中默认增加了自定义properties，可能和一些开源jar包的问题，使用了公共的名称可能覆盖了原先的版本，
比如在这个项目之前定义的 <spring.version>4.3.2.RELEASE</spring.version> ，虽然 spring boot 2 默认使用的是 spring 5 但加入这个后
没有任何地方引用，但依赖关系还是变了
3、去掉 properties 中的自定义属性就好了
4、app_web spring boot log4j2
5、完整的格式化 log4j2 的输出格式，除了高亮问题没解决
6、集成 druid mybatis MySQL
7、集成 druid filter，durid filter解释
8、druid filter wall SQL 注入攻击
9、druid filter web 监控配置 WebStatFilter
10、druid filter web 配置 StatViewServlet
11、陪完了具体参照配置说明
12、spring boot 配置首页
13、spring boot 500,404处理，错误页面已经准备好了，异常先，错误编码后，统一交给错误编码处理
14、maven 插件是继承的，spring boot 加载只针对对应的那个项目就可以
15、先暂停该项，调整标准 rest，未完 TODO
16、接口超时时间，每层接口都加一个超时时间记录，已加，要验证一下
17、rest 动态字段内容，需要修改 mybatis plus 源码，已确认 Wrapper 包装类有字段的功能
18、公共字段填充，已解决，忘加了一个注解，源码没怎么看懂
19、肯定是先异常后状态码，自己的理解 exception 之后才是 500
























# Druid内置提供了一个StatViewServlet用于展示Druid的统计信息，详细说明 https://github.com/alibaba/druid/wiki/%E9%85%8D%E7%BD%AE_StatViewServlet%E9%85%8D%E7%BD%AE
# Web关联监控配置 https://github.com/alibaba/druid/wiki/配置_配置WebStatFil=ter
# 配置监控页面访问密码
# 配置allow和deny
# 配置allow和deny
# 回头也弄一个 yml 配置和properties一模一样的配置
# 注解时间
# spring boot 默认 json 相关配置
# 默认编码 UTF-8 
# rest 参照 https://github.com/ScienJus/spring-restful-authorization 
# 代码中的作者 ScienJus，是参照他的
# 接口超时参照 https://www.bbsmax.com/A/pRdB2yPdnx/
# trance id 已去掉 接口超时参照里有 trace id，暂时没有微服务暂时没有 trace id，记录日志跟踪
# 多次request.getInputStream 参考两篇文章：
# https://www.programcreek.com/java-api-examples/index.php?api=org.springframework.web.util.ContentCachingRequestWrapper
# http://www.itgo.me/a/7708268096777122645/reading-httprequest-content-from-spring-exception-handler
# 有办法动态实现动态字段么，filed 参数动态查询
# rest token 参考 https://developers.arcgis.com/rest/users-groups-and-items/generate-token.htm
http://www.ruanyifeng.com/blog/2014/05/restful_api.html rest 
