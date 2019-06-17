<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<rapid:override name="head">
    <title>编辑教师信息</title>
</rapid:override>
<rapid:override name="content">
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    %>
    <form class="layui-form" id="changeform" method="post" action="<%=basePath%>admin/changeTeacher" style="margin:80px 400px; width:450px;">
        <table class="layui-table" style="margin-top:15px;">
            <colgroup>
                <col width="200">
                <col width="100">
            </colgroup>
            <tbody>
            <tr>

                <td><label class="layui-form-label">教师编号</label></td>
                <td>

                    <input type="text" name="teaId" value="${teacher.teaId}" placeholder="请输入教师编号" autocomplete="off" class="layui-input">

                </td>

            </tr>
            <tr>

                <td><label class="layui-form-label">教师名字</label></td>
                <td>

                    <input type="text" name="teaName" value="${teacher.teaName}" placeholder="请输入教师名字" autocomplete="off" class="layui-input">

                </td>

            </tr>
            <tr>

                <td><label class="layui-form-label">教师性别</label></td>
                <td>
                    <select class="layui-select"  name="sex">
                        <c:if test="${teacher.sex=='男'}">
                            <option value="男" selected>男</option>
                        </c:if>
                        <c:if test="${teacher.sex=='女'}">
                            <option value="女" selected>女</option>
                        </c:if>
                    </select>
                </td>

            </tr>
            <tr>
                <td> <label class="layui-form-label">教师密码</label></td>
                <td>
                    <input type="text" name="teaPass" value="${teacher.teaPass}" placeholder="请输入教师密码" autocomplete="off" class="layui-input">
                </td>
            </tr>
            <tr>
                <td> <label class="layui-form-label">教师职称</label></td>
                <td>
                    <select class="layui-select"  name="pId">
                        <c:forEach items="${titleList}" var="title">
                            <option value="${title.pId}" <c:if test="${title.pId==teacher.pId}">selected</c:if>>${title.pName}</option>
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
