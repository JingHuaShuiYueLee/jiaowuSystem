<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<rapid:override name="head">
    <title>编辑学生信息</title>
</rapid:override>
<rapid:override name="content">
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    %>
    <form class="layui-form" id="changeform" method="post" action="<%=basePath%>admin/changeStudent" style="margin:80px 400px; width:450px;">
        <table class="layui-table" style="margin-top:15px;">
            <colgroup>
                <col width="200">
                <col width="100">
            </colgroup>
            <tbody>
            <tr>

                <td><label class="layui-form-label">学生学号</label></td>
                <td>

                    <input type="text" name="xueshengId" value="${student.stuId}" disabled autocomplete="off" class="layui-input">

                </td>
                <input type="hidden" name="stuId" value="${student.stuId}">
            </tr>
            <tr>

                <td><label class="layui-form-label">学生名字</label></td>
                <td>

                    <input type="text" name="stuName" value="${student.stuName}" placeholder="请输入学生名字" autocomplete="off" class="layui-input">

                </td>

            </tr>
            <tr>

                <td><label class="layui-form-label">学生性别</label></td>
                <td>
                    <select class="layui-select"  name="sex" >
                        <c:if test="${student.sex=='男'}">
                            <option value="男" selected>男</option>
                        </c:if>
                        <c:if test="${student.sex=='女'}">
                            <option value="女" selected>女</option>
                        </c:if>
                    </select>
                </td>

            </tr>
            <tr>

                <td> <label class="layui-form-label">学生密码</label></td>
                <td>

                    <input type="text" name="stuPass" value="${student.stuPass}" placeholder="请输入学生密码" autocomplete="off" class="layui-input">
                </td>

            </tr>
            <tr>
                <td><label class="layui-form-label">所在学院</label></td>
                <td>
                    <select class="layui-select"  name="insId">
                        <c:forEach items="${insList}" var="ins">
                            <option value="${ins.insId}" <c:if test="${ins.insId==student.insId}">selected</c:if>>${ins.insName}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
    <button type="button" id="success" class="layui-btn layui-btn-danger layui-btn-lg" style="margin:0 550px;">
        提交修改
    </button>
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <script>
        $(function () {
            $("#success").click(function () {
                var a = confirm("确定要提交修改吗");
                if(a==true) {
                    $("#changeform").submit();
                }
            })
        })
    </script>
</rapid:override>
<%@ include file="base.jsp" %>
