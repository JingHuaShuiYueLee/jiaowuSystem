<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<rapid:override name="head">
    <title>教师信息管理</title>
</rapid:override>
<rapid:override name="content">
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    %>
    <div>
        <button type="button" onclick="insert()" class="layui-btn layui-btn-lg layui-btn-warm" style="margin-top:15px;">添加新教师</button>
    </div>
    <form class="layui-form" style="margin:10px 15px 10px;">
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button type="button" class="layui-btn" style="float:right;" onclick="search()">搜索</button>
                <input type="text" id="searchById" class="layui-input" style="float:right; width:200px;"
                       placeholder="请输入教师编号">
            </div>
        </div>
    </form>
    <p style="color:red; margin-top:10px; font-size:15px;">${msg}</p>
    <table class="layui-table" style="margin-top:15px;">
        <colgroup>
            <col width="50">
            <col width="50">
            <col width="50">
            <col width="80">
            <col width="120">
        </colgroup>
        <thead>
        <tr>
            <th>教师编号</th>
            <th>教师名字</th>
            <th>教师性别</th>
            <th>教师密码</th>
            <th>教师职称</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${paging.dataList}" var="teacher">
            <tr>
                <td>${teacher.teaId}</td>
                <td>${teacher.teaName}</td>
                <td>${teacher.sex}</td>
                <td>${teacher.teaPass}</td>
                <td>${teacher.pName}</td>
                <td>
                    <button class="layui-btn layui-btn-normal" onclick="edit_fun(${teacher.teaId})">编辑</button>
                    <button class="layui-btn layui-btn-danger" onclick="remove_fun(${teacher.teaId})">删除</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div style="text-align:center; margin-top:10px; margin-left:-100px;">
        <c:if test="${paging.totalPage >=0}">
            <p style=" color: black; font-size:16px; margin-bottom:10px;">当前第 ${paging.currentPage }
                页/共 ${paging.totalPage} 页</p>
            <c:choose>
                <c:when test="${paging.totalPage==0}">
                    <button class="layui-btn layui-btn-disabled" onclick="goPage(1)">首页</button>
                    <button class="layui-btn layui-btn-disabled" onclick="goPage(${paging.currentPage-1})">上一页</button>
                    <button class="layui-btn layui-btn-disabled" onclick="goPage(${paging.currentPage+1})">下一页</button>
                    <button class="layui-btn layui-btn-disabled" onclick="goPage(${paging.totalPage})">末页</button>
                </c:when>
                <c:when test="${paging.currentPage==1 && paging.totalPage==1}">
                    <button class="layui-btn" onclick="goPage(1)">首页</button>
                    <button class="layui-btn layui-btn-disabled" onclick="goPage(${paging.currentPage-1})">上一页</button>
                    <button class="layui-btn layui-btn-disabled" onclick="goPage(${paging.currentPage+1})">下一页</button>
                    <button class="layui-btn" onclick="goPage(${paging.totalPage})">末页</button>
                </c:when>
                <c:when test="${paging.currentPage==1 && paging.totalPage>1}">
                    <button class="layui-btn" onclick="goPage(1)">首页</button>
                    <button class="layui-btn layui-btn-disabled" onclick="goPage(${paging.currentPage-1})">上一页</button>
                    <button class="layui-btn" onclick="goPage(${paging.currentPage+1})">下一页</button>
                    <button class="layui-btn" onclick="goPage(${paging.totalPage})">末页</button>
                </c:when>
                <c:when test="${paging.currentPage>1 && paging.currentPage<paging.totalPage}">
                    <button class="layui-btn" onclick="goPage(1)">首页</button>
                    <button class="layui-btn" onclick="goPage(${paging.currentPage-1})">上一页</button>
                    <button class="layui-btn" onclick="goPage(${paging.currentPage+1})">下一页</button>
                    <button class="layui-btn" onclick="goPage(${paging.totalPage})">末页</button>
                </c:when>
                <c:when test="${paging.currentPage>1 && paging.currentPage==paging.totalPage}">
                    <button class="layui-btn" onclick="goPage(1)">首页</button>
                    <button class="layui-btn" onclick="goPage(${paging.currentPage-1})">上一页</button>
                    <button class="layui-btn layui-btn-disabled" onclick="goPage(${paging.currentPage+1})">下一页</button>
                    <button class="layui-btn" onclick="goPage(${paging.totalPage})">末页</button>
                </c:when>
            </c:choose>
        </c:if>
    </div>
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
    <script>
        function search() {
            var teaId = document.getElementById("searchById").value;
            window.location.href = "<%=basePath%>admin/searchTeacher?teaId=" + teaId;
        }

        function goPage(page) {
            window.location.href = "<%=basePath%>admin/manageTeacher?page=" + page;
        }

        function edit_fun(teaId) {
            window.location.href = "<%=basePath%>admin/editTeacher?teaId=" + teaId;
        }
        function insert() {
            window.location.href = "<%=basePath%>admin/preInsertTeacher";
        }

        function remove_fun(teaId) {
            var r = confirm("确认删除该教师吗？")
            if (r == true) {
                window.location.href = "<%=basePath%>admin/removeTeacher?teaId=" + teaId;
            }
            else {
                return;
            }
        }
    </script>
</rapid:override>
<%@ include file="base.jsp" %>
