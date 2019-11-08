
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function sure() {
		return confirm("确定要删除?");
	}
</script>

<style>
table {
	text-align: center;
	background-color: #F5FFFA;
}

td {
	width: 156px;
}

.t {
	background-color: #00BFFF;
	border: none;
	color: white;
	padding: 3px 5px;
	text-align: center;
	border-radius: 25px;
	text-decoration: none;
	display: inline-block;
	margin: 4px 2px;
	cursor: pointer;
}
</style>

</head>
<body>
	<center>
		<table>


			<tr>
				<th>课程编号</th>
				<th>课程名</th>
				<th>课时</th>
				<th></th>
				<th></th>
			</tr>
			<c:forEach items="${courses}" var="course">
				<tr>
					<td>${course.no}</td>
					<td>${course.name}</td>
					<td>${course.per}</td>
					<td>
						<form action="/TeachingManagementSystem/CourseRemoveServlet" method="POST" onsubmit="return sure()">
							<input type="submit" value="删除" class="t">
							<input type="hidden" name="cno" value="${course.no}">
						</form>
					</td>
					<td>
						<form action="/TeachingManagementSystem/CourseModifyServlet1" method="POST">
							<input type="submit" value="修改" class="t">
							<input type="hidden" name="cno" value="${course.no}">
						</form>
					</td>
				</tr>
			</c:forEach>
			<c:choose>
				<c:when test="${result==true}">
					<script>
						alert('删除课程成功');
					</script>
					<%
						response.setHeader("refresh", "1;url=/TeachingManagementSystem/CourseListServlet");
					%>
				</c:when>
				<c:when test="${result==false}">
					<script>
						alert('删除课程失败');
					</script>
					<%
						response.setHeader("refresh", "1;url=/TeachingManagementSystem/CourseListServlet");
					%>
				</c:when>
			</c:choose>
		</table>
	</center>

</body>
</html>