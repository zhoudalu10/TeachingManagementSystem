<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<form action="/TeachingManagementSystem/CourseModifyServlet2" method="post">
			<table>
				<tr>
					<td>
						课程编号：
						<input type="hidden" name="cno" value="${course.no}">
					</td>
					<td>${course.no}</td>
				</tr>
				<tr>
					<td>课程名字：</td>
					<td>
						<input type="text" name="cname" value="${course.name}">
					</td>
				</tr>
				<tr>
					<td>课时数：</td>
					<td>
						<input type="text" name="cper" value="${course.per}" placeholder="2-3位数字">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="确定">
						&nbsp;&nbsp;&nbsp;
						<input type="reset" value="重新填写">
					</td>
				</tr>
			</table>
		</form>
		<c:choose>
			<c:when test="${result==true}">
				<script>
					alert('更新课程成功');
				</script>
				<%
					response.setHeader("refresh", "1;url=/TeachingManagementSystem/CourseListServlet");
				%>
			</c:when>
			<c:when test="${result==false}">
				<script>
					alert('更新课程失败');
				</script>
				<%
					response.setHeader("refresh", "1;url=/TeachingManagementSystem/CourseListServlet");
				%>
			</c:when>
			<c:when test="${judge==false}">
				<script>
					alert('数据输入有误，请重新输入');
				</script>
				<%
					response.setHeader("refresh", "1;url=/TeachingManagementSystem/CourseListServlet");
				%>
			</c:when>
		</c:choose>
	</center>

</body>
</html>