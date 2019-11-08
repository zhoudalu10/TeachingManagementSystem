<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.iweb.DAO.GradeDAO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	List<String> courses = GradeDAO.selectCourse();
	List<String> headteachers = GradeDAO.selectHeadteacher();
	List<String> lecturers = GradeDAO.selectLecturer();
	request.setAttribute("courses", courses);
	request.setAttribute("headteachers", headteachers);
	request.setAttribute("lecturers", lecturers);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<form action="/TeachingManagementSystem/GradeModifyServlet2" method="post">
			<table>
				<tr>
					<td>
						班级编号:
						<input type="hidden" name="gno" value="${grade.no}">
					</td>
					<td>${grade.no}</td>
				</tr>
				<tr>
					<td>班级名：</td>
					<td>
						<input type="text" name="gname" value="${grade.name}">
					</td>
				</tr>
				<tr>
					<td>课程：</td>
					<td>
						<select name="gcourse">
							<c:forEach items="${courses}" var="course">
								<option value="${course}">${course}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>班主任：</td>
					<td>
						<select name="gheadtname">
							<c:forEach items="${headteachers}" var="headteacher">
								<option value="${headteacher}">${headteacher}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>讲师：</td>
					<td>
						<select name="glecname">
							<c:forEach items="${lecturers}" var="lecturer">
								<option value="${lecturer}">${lecturer}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>班级人数：</td>
					<td>
						<input type="text" name="gsnum" value="${grade.snum}">
					</td>
				</tr>
				<tr>
					<td>当前课时：</td>
					<td>
						<input type="text" name="gpernow" value="${grade.pernow}">
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
					alert('更新班级成功');
				</script>
				<%
					response.setHeader("refresh", "1;url=/TeachingManagementSystem/GradeListServlet");
				%>
			</c:when>
			<c:when test="${result==false}">
				<script>
					alert('更新班级失败');
				</script>
				<%
					response.setHeader("refresh", "1;url=/TeachingManagementSystem/GradeListServlet");
				%>
			</c:when>
			<c:when test="${judge==false}">
				<script>
					alert('数据输入有误，请重新输入');
				</script>
				<%
					response.setHeader("refresh", "1;url=/TeachingManagementSystem/GradeListServlet");
				%>
			</c:when>
		</c:choose>
	</center>

</body>
</html>