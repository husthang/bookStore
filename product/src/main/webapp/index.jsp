<%@page language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>
        211书籍管理首页
    </title>
    <!-- rel属性:relationship的缩写,规定当前文档与被链接文档之间的关系;type 规定被链接文档的mime类型; href:Hypertext Reference-->
    <link rel="stylesheet" href="css/main.css" type="text/css">
</head>

<body class="main">
    <!-- jsp中的三个指令,page include taglib-->
    <%@include file="head.jsp"%>
    <%@include file="menu_search.jsp"%>
    <jsp:include page="foot.jsp"/>
</body>
</html>