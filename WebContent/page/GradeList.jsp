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
				<th>班级编号</th>
				<th>班级名称</th>
				<th>班级课程</th>
				<th>班主任</th>
				<th>讲师</th>
				<th>班级人数</th>
				<th>当前课时</th>
				<th></th>
				<th></th>
			</tr>
			<c:forEach items="${grades}" var="grade">
				<tr>
					<td>${grade.no}</td>
					<td>${grade.name}</td>
					<td>${grade.course}</td>
					<td>${grade.headtname}</td>
					<td>${grade.lecname}</td>
					<td>${grade.snum}</td>
					<td>${grade.pernow}</td>
					<td>
						<form action="/TeachingManagementSystem/GradeRemoveServlet" method="POST" onsubmit="return sure()">
							<input type="submit" value="删除" class="t">
							<input type="hidden" name="gno" value="${grade.no}">
						</form>
					</td>
					<td>
						<form action="/TeachingManagementSystem/GradeModifyServlet1" method="POST">
							<input type="submit" value="修改" class="t">
							<input type="hidden" name="gno" value="${grade.no}">
						</form>
					</td>
				</tr>
			</c:forEach>
			<c:choose>
				<c:when test="${result==true}">
					<script>
						alert('删除班级成功');
					</script>
					<%
						response.setHeader("refresh", "1;url=/TeachingManagementSystem/GradeListServlet");
					%>
				</c:when>
				<c:when test="${result==false}">
					<script>
						alert('删除班级失败');
					</script>
					<%
						response.setHeader("refresh", "1;url=/TeachingManagementSystem/GradeListServlet");
					%>
				</c:when>
			</c:choose>
		</table>
	</center>

</body>
</html>
