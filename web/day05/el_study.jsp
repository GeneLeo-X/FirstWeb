<!--导入JSTL核心库，前缀设置为c-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Demo04.bean.Car" %>
<%@ page import="Demo04.bean.User" %>
<%--
  Created by IntelliJ IDEA.
  User: Think
  Date: 2022/2/10
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EL与JSTL学习</title>
</head>
<body>

    <!--
    EL表达式语法：
    https://blog.csdn.net/weixin_49343190/article/details/108158774?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522166037172716782395377658%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=166037172716782395377658&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~top_positive~default-2-108158774-null-null.142^v40^pc_rank_34_queryrelevant0,185^v2^control&utm_term=el%E8%A1%A8%E8%BE%BE%E5%BC%8F&spm=1018.2226.3001.4187

    -->

    <%
        pageContext.setAttribute("name","lily");
        request.setAttribute("age",23);
        request.setAttribute("name" , "tom");

        Car car = new Car();
        car.setName("路虎");
        User user = new User();
        user.setName("jack");
        user.setSex("男");
        user.setCar(car);
        session.setAttribute("user" , user);

        List<User> list = new ArrayList<>();
        list.add(user);
        User user1 = new User();
        user1.setName("lucy");
        user1.setSex("女");
        list.add(user1);
        application.setAttribute("userList" , list);


    %>

    <!--可以直接通过key值的方式，取值-->
    ${pageScope.name}
    ${requestScope.age}
    ${sessionScope.user.name}
    ${sessionScope.user.sex}
    ${sessionScope.user.car.name}

    <!--数组的索引位置从0开始 -->
    ${applicationScope.userList[1].name}

    <hr>
    <!-- 相当于pageContext.findAttribute("key")-->
    ${user.name}
    <hr>
    ${header["User-Agent"]}
    <br>
    ${userList[1].sex}

    <!--request.getContextPath()-->
    ${pageContext.request.contextPath}
    <!--操作循环的，和EL配合使用的 JSTL中定义的变量，都会存于域中 begin : 开始索引 ， end ： 结束的索引-->
    <hr>
    <c:forEach begin="0" end="10" var="i">
        <c:if test="${i == 5}">
            ${i + 100}
        </c:if>
        <c:if test="${i != 5}">
            ${i}
        </c:if>
    </c:forEach>
    <hr>
    <!--items : 访问集合元素  var ： 存放的是每一个集合中的元素 varStatus ： 行记录 -->
    <c:forEach items="${userList}" var="user" varStatus="vs">
        编号: ${vs.count} &nbsp;&nbsp;&nbsp; 名字: ${user.name} &nbsp;&nbsp;&nbsp; ${user.sex} <br>
    </c:forEach>
</body>
</html>
