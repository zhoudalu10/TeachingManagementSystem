

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
				<th>教室编号</th>
				<th>姓名</th>
				<th>性别</th>
				<th>年龄</th>
				<th>职务</th>
				<th>联系电话</th>
				<th>QQ</th>
			</tr>
			<c:forEach items="${teachers}" var="teacher">
				<tr>
					<td>${teacher.no}</td>
					<td>${teacher.name}</td>
					<td>${teacher.sex}</td>
					<td>${teacher.age}</td>
					<td>${teacher.lev}</td>
					<td>${teacher.tel}</td>
					<td>${teacher.qq}</td>
					<td>
						<form action="/TeachingManagementSystem/TeacherRemoveServlet" method="POST" onsubmit="return sure()">
							<input type="submit" value="删除" class="t">
							<input type="hidden" name="tno" value="${teacher.no}">
						</form>
					</td>
					<td>
						<form action="/TeachingManagementSystem/TeacherModifyServlet1" method="POST">
							<input type="submit" value="修改" class="t">
							<input type="hidden" name="tno" value="${teacher.no}">
						</form>
					</td>
				</tr>
			</c:forEach>
			<c:choose>
				<c:when test="${result==true}">
					<script>
						alert('删除教师成功');
					</script>
					<%
						response.setHeader("refresh", "1;url=/TeachingManagementSystem/TeacherListServlet");
					%>
				</c:when>
				<c:when test="${result==false}">
					<script>
						alert('删除教师失败');
					</script>
					<%
						response.setHeader("refresh", "1;url=/TeachingManagementSystem/TeacherListServlet");
					%>
				</c:when>
			</c:choose>
		</table>
	</center>

</body>
</html>