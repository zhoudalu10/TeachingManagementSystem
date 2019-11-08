<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="com.iweb.DAO.GradeDAO"%>
<%@page import="java.util.List"%>
<%@page import="com.iweb.entity.Grade"%>
<%
	List<Grade> grades = GradeDAO.all();
	request.setAttribute("grades", grades);
%>

<!DOCTYPE html>
<html>

<head>

	<meta charset="utf-8">

	<style>
		textarea {
			height: 512px;
		}

		button {
			background-color: #00BFFF;
			border: none;
			color: white;
			padding: 9px 20px;
			text-align: center;
			border-radius: 25px;
			text-decoration: none;
			display: inline-block;
			font-size: 16px;
			margin: 4px 2px;
			cursor: pointer;
		}

		#note {
			width: 1024px;
			height: 512px;
			resize: none;
			outline: none;
		}

		#notegrade {
			width: 100px;
		}
	</style>
	<script src="/TeachingManagementSystem/page/jquery-1.10.2.js"></script>
	<script>
		$(document).ready(function () {
			$('#SubmitNote').click(function () {
				var notegrade = $('#notegrade').val();
				if (notegrade != 'blank') {
					var data = $('#note').val();
					if (data == '') {
						alert('笔记不能为空！');
					} else {
						$.post('/TeachingManagementSystem/NoteAddServlet', {
							data: data, notegrade: notegrade
						}, function (t) {
							if (t == 'true') {
								alert('增加笔记成功');
								$('#note').val('');
							} else {
								alert('增加笔记失败');
							}
						})
					}
				} else {
					alert('请选择班级！');
				}
			})
		})
	</script>
</head>

<body>

	<div>
		请选择班级：
	</div>
	<div>
		<select id="notegrade">
			<option value="blank">----------</option>
			<c:forEach items="${grades}" var="grade">
				<option value="${grade.name}">${grade.name}</option>
			</c:forEach>
		</select>
	</div>
	<br />
	<div class="notes">
		<textarea id="note"></textarea>
	</div>
	<br />
	<div>
		<button id="SubmitNote">提交</button>
	</div>

</body>


</html>