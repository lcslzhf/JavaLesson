<%--
  Created by IntelliJ IDEA.
  User: xjt92
  Date: 2020/4/20
  Time: 19:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.lagou.pojo.Resume" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html >
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>简历列表</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
<div class="container">
    <h2>简历列表</h2>

        <%
    ArrayList<Resume> list = null;
    Object obj = request.getAttribute("resumeList");
    if (obj != null) {
        list = (ArrayList<Resume>) obj;
    }
%>

    <div>
        <button type="button" class="btn btn-default" id="add_rows" style="float: right; margin-bottom: 10px;">增加
        </button>
    </div>


    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Id</th>
            <th>姓名</th>
            <th>手机号</th>
            <th>地址</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <%
            if (list != null) {
                for (Resume resume : list) {
        %>
                
        <tr>
            <td><%=resume.getId() %>
            </td>
                        
            <td><%=resume.getName()%>
            </td>
                        
            <td><%=resume.getPhone()%>
            </td>
                        
            <td><%=resume.getAddress()%>
            </td>
            <td>
                <button type="button" class="btn btn-default" onclick="modifySingleUser(<%=resume.getId() %>)">编辑
                </button>
                <button type="button" class="btn btn-default" onclick="confirmDeleteUser(<%=resume.getId() %>)">删除
                </button>
            </td>
                    
        </tr>
                <%
                }
            }
        %>
            
        </tbody>
    </table>
        
</div>
<script type="text/javascript">

    var flag = true;

    /* 是否删除单行 */
    function confirmDeleteUser(id) {
        if (confirm("请确认是否删除?")) {
            location.href = "<%=request.getContextPath() %>/deleteById?id=" + id;
        } else {

        }
    }

    /* 是否修改单行 */
    function modifySingleUser(id) {
        location.href = "<%=request.getContextPath() %>/queryById?id=" + id;
    }


    /* 增加成员 */
    $("#add_rows").click(function () {
        location.href = "<%=request.getContextPath() %>/add";
    });

</script>
</body>

</html>