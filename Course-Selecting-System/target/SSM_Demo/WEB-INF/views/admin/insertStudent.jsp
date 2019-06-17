<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<rapid:override name="head">
    <title>添加新学生</title>
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
                <col width="100">
            </colgroup>
            <tbody>
            <tr>

                    <td><label class="layui-form-label">学生学号</label></td>
                    <td>

                        <input type="text" name="stuId" placeholder="请输入学生学号" autocomplete="off" class="layui-input">

                    </td>

            </tr>
            <tr>

                    <td><label class="layui-form-label">学生名字</label></td>
                    <td>

                        <input type="text" name="stuName" placeholder="请输入学生名字" autocomplete="off" class="layui-input">

                    </td>

            </tr>
            <tr>

                    <td><label class="layui-form-label">学生性别</label></td>
                    <td>
                    <select class="layui-select"  name="sex">
                        <option value="男">男</option>
                        <option value="女">女</option>
                    </select>
                    </td>

            </tr>
            <tr>

                   <td> <label class="layui-form-label">学生密码</label></td>
                    <td>

                        <input type="text" name="stuPass" placeholder="请输入学生密码" autocomplete="off" class="layui-input">
                    </td>

            </tr>
            <tr>
                <td><label class="layui-form-label">所在学院</label></td>
                <td>
                    <select class="layui-select"  name="insId">
                        <c:forEach items="${insList}" var="ins">
                            <option value="${ins.insId}">${ins.insName}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>

            </tbody>
        </table>
    </form>
    <button type="button" id="success" class="layui-btn layui-btn-danger layui-btn-lg" style="margin:0 550px;">
        确认提交
    </button>
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <script>
        $(function () {
            $("#success").click(function () {
                    $("#changeform").submit();
            })
        })
    </script>
</rapid:override>
<%@ include file="base.jsp" %>
