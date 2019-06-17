<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<html>
<head>
    <rapid:block name="head"></rapid:block>
    <link rel="stylesheet" href="//cdnjs.loli.net/ajax/libs/mdui/0.4.2/css/mdui.min.css">
    <script src="//cdnjs.loli.net/ajax/libs/mdui/0.4.2/js/mdui.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/layui.css">
    <style type="text/css">
        .layui-nav-item.side a {
            font-size: 22px;
            margin: 10px;
        }
        /*解决Chrome下表单自动填充后背景色为黄色*/
        input:-webkit-autofill {
            -webkit-box-shadow: 0 0 0px 1000px white inset;
        }
        .juzhong {
            margin: 0 auto;
        }
    </style>
</head>
<body class="layui-layout-body" style="padding:10px 50px;">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<div class="juzhong">
    <h1 class="juzhong">教务系统</h1>
    <h3>欢迎您，您当前的身份是: ${status}</h3>
</div>
<ul class="layui-nav layui-bg-blue">
    <li class="layui-nav-item"><a href="<%=basePath%>student/studentIndex" style="font-size:20px;">首页</a></li>
    <!--这里${requestScope}取不到值，需要改为${param}-->
    <li class="layui-nav-item"><a href="<%=basePath%>student/studentInfo?stuid=${sessionScope.stuid}" style="font-size:20px;">个人资料</a></li>
    <li class="layui-nav-item ">
        <a href="javascript:;" style="font-size:20px;">选课</a>
        <dl class="layui-nav-child">
            <dd><a href="<%=basePath%>student/courseList?page=1" style="font-size:16px;">排课信息</a></dd>
            <dd><a href="<%=basePath%>student/checkedCourseList?page=1" style="font-size:16px;">我的选课</a></dd>
        </dl>
    </li>
    <li class="layui-nav-item"><a href="<%=basePath%>student/grade?stuid=${sessionScope.stuid}" style="font-size:20px;">我的成绩</a></li>
    <li class="layui-nav-item" style="float:right; "><a href="<%=basePath%>exit" style="font-size: 20px;">退出登录</a></li>
    <li class="layui-nav-item" style="float:right; font-size:20px;"><i class="layui-icon" style="font-size:18px;" >&#xe612;</i>&nbsp;${sessionScope.username}&nbsp;</li>
</ul>
<rapid:block name="content"></rapid:block>
<script src="${pageContext.request.contextPath}/static/layui.all.js"></script>
</body>
</html>
