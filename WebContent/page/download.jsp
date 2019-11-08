<%@page import="com.iweb.DAO.ExcelDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	List<String> downloadPaths = ExcelDAO.all();
	request.setAttribute("downloadPaths", downloadPaths);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.download {
	background-color: #00BFFF;
	border: none;
	color: white;
	padding: 5px 10px;
	text-align: center;
	border-radius: 25px;
	text-decoration: none;
	display: inline-block;
	font-size: 12px;
	margin: 4px 2px;
	cursor: pointer;
}

td {
	text-align: center;
	width: 156px;
}
</style>
</head>
<body>
<center>
	<table>
		<tr>
			<th>文件名</th>
			<th>下载链接</th>
		</tr>
		<c:forEach items="${downloadPaths}" var="downloadPath">
		<tr>
			<td>${downloadPath}</td>
			<td>
				<a href="/TeachingManagementSystem/excel/${downloadPath}" class="download">下载</a>
			</td>
			</tr>
		</c:forEach>
	</table>
</center>
</body>
</html>