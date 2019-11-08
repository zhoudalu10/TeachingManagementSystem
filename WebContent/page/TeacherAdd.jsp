
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

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

select {
	width: 160.8px;
}

tr {
	height: 30px;
}
</style>


</head>
<body>
	<center>
		<form action="/TeachingManagementSystem/TeacherAddServlet" method="post">
			<table>
				<tr>
					<td>教师姓名：</td>
					<td>
						<input type="text" name="tname" placeholder="2-4位汉字">
					</td>
				</tr>
				<tr>
					<td>性别：</td>
					<td>
						男
						<input type="radio" name="tsex" value="男">
						女
						<input type="radio" name="tsex" value="女">
					</td>
				</tr>
				<tr>
					<td>年龄：</td>
					<td>
						<input type="text" name="tage" placeholder="数字20-59">
					</td>
				</tr>


				<tr>
					<td>职位：</td>
					<td>
						<select name="tlev">
							<option value="讲师">讲师</option>
							<option value="班主任">班主任</option>
							<option value="管理员">管理员</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>联系电话：</td>
					<td>
						<input type="text" name="ttel" placeholder="11位数字">
					</td>
				</tr>
				<tr>
					<td>QQ：</td>
					<td>
						<input type="text" name="tqq" placeholder="5-12位数字">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="确定" class="t">
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="reset" value="重新填写" class="t">
					</td>
				</tr>
			</table>


		</form>
	</center>
	<c:choose>
		<c:when test="${result==true}">
			<script>
				alert('增加教师成功');
			</script>
			<%
				response.setHeader("refresh", "1;url=/TeachingManagementSystem/page/TeacherAdd.jsp");
			%>
		</c:when>
		<c:when test="${result==false}">
			<script>
				alert('增加教师失败');
			</script>
			<%
				response.setHeader("refresh", "1;url=/TeachingManagementSystem/page/TeacherAdd.jsp");
			%>
		</c:when>
		<c:when test="${judge==false}">
			<script>
				alert('数据输入有误，请重新输入');
			</script>
			<%
				response.setHeader("refresh", "1;url=/TeachingManagementSystem/page/TeacherAdd.jsp");
			%>
		</c:when>
	</c:choose>


</body>
</html>

