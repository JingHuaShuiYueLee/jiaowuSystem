<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<rapid:override name="head">
    <title>学生的学习情况</title>
</rapid:override>
<rapid:override name="content">
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    %>
    <form class="layui-form" id="changeform" method="post" action="<%=basePath%>admin/insertStudent" style="margin:80px 400px; width:450px;">
        <table class="layui-table" style="margin-top:15px;">
            <colgroup>
                <col width="200">
                <col width="200">
            </colgroup>
            <tbody>
            <tr>
                <td><label class="layui-form-label">获得总学分</label></td>
                <td>
                    <input type="text" name="stuId" value="${credits}" disabled autocomplete="off" class="layui-input">
                </td>
            </tr>
            <tr>
                <td><label class="layui-form-label">学分绩</label></td>
                <td>
                    <input type="text" name="stuId" value="${gpa}" disabled autocomplete="off" class="layui-input">
                </td>
            </tr>
            </tbody>
        </table>
    </form>
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
</rapid:override>
<%@ include file="base.jsp" %>
