<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>课程添加</title>

<style>
table {
	text-align: center;
}

.t {
	background-color: #00BFFF;
	border: none;
	color: white;
	padding: 6px 10px;
	text-align: center;
	border-radius: 25px;
	text-decoration: none;
	display: inline-block;
	margin: 4px 2px;
	cursor: pointer;
}

tr {
	height: 30px;
}
</style>

</head>
<body>
	<center>
		<form action="/TeachingManagementSystem/CourseAddServlet" method="post">
			<table>
				<tr>
					<td>课程名字：</td>
					<td>
						<input type="text" name="cname">
					</td>
				</tr>
				<tr>
					<td>课时数：</td>
					<td>
						<input type="text" name="cper" placeholder="2-3位数字">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="确定" class="t">
						&nbsp;&nbsp;&nbsp;
						<input type="reset" value="重新填写" class="t">
					</td>
				</tr>
			</table>

		</form>
	</center>
	<c:choose>
		<c:when test="${result==true}">
			<script>
				alert('增加课程成功');
			</script>
			<%
				response.setHeader("refresh", "1;url=/TeachingManagementSystem/page/CourseAdd.jsp");
			%>
		</c:when>
		<c:when test="${result==false}">
			<script>
				alert('增加课程失败');
			</script>
			<%
				response.setHeader("refresh", "1;url=/TeachingManagementSystem/page/CourseAdd.jsp");
			%>
		</c:when>
		<c:when test="${judge==false}">
			<script>
				alert('数据输入有误，请重新输入');
			</script>
			<%
				response.setHeader("refresh", "1;url=/TeachingManagementSystem/page/CourseAdd.jsp");
			%>
		</c:when>
	</c:choose>

</body>
</html>