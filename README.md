RBAC 项目

# app_inter 对移动端的接口类，名字后期调整
# app_util 各工具类
# app_common 暂未定
# app_core 暂未定


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
合成一张表肯定有类型的

url 用正则来考虑，有些是带*的要特殊处理，/user/id

应该有个菜单表和页面元素表的应该有关系


