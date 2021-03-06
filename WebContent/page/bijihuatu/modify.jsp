<%@page import="com.iweb.entity.Grade"%>
<%@page import="com.iweb.DAO.GradeDAO"%>
<%@page import="com.iweb.DAO.NoteDAO"%>
<%@page import="com.iweb.entity.Note"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	List<Grade> grades = GradeDAO.all();
	request.setAttribute("grades", grades);
%>
<!DOCTYPE html>
<html>

<head>

	<meta charset="utf-8">
	<script src="/TeachingManagementSystem/page/jquery-1.10.2.js"></script>
	<script type="text/javascript">
		jQuery(document).ready(function () {
			jQuery('#notegrade').change(function () {
				$('#note').val('');
				$("#selectnote").html("<option value='blank'>----------</option> ");
				var notegrade = jQuery('#notegrade').val();
				if (notegrade != 'blank') {
					jQuery.post('/TeachingManagementSystem/GradeSelectServlet', { notegrade: notegrade }, function (t) {
						var optionstring = "";
						for (var i = 0; i < t.length; i++) {
							var notetime = t[i].notetime;
							optionstring += "<option value=\"" + notetime + "\" >" + notetime + "</option>";
							$("#selectnote").html("<option value='blank'>----------</option> " + optionstring);
						}
					}, 'json')
				}
			})

			jQuery('#selectnote').change(function () {
				$('#note').val('');
				var notetime = jQuery('#selectnote').val();
				if (notetime != 'blank') {
					jQuery.post('/TeachingManagementSystem/NoteSelectServlet', { notetime: notetime }, function (t) {
						jQuery('#note').val(t);
					})
				} else {
					jQuery('#note').val('');
				}

			})

			jQuery('#SubmitNote').click(function () {
				var notetime = jQuery('#selectnote').val();
				var notedata = jQuery('#note').val();
				if (notetime != 'blank') {
					if (notedata == '') {
						alert('笔记为空！');
					} else {
						jQuery.post('/TeachingManagementSystem/NoteUpdateServlet', { notetime: notetime, notedata: notedata }, function (t) {
							if (t == 'true') {
								alert('修改成功！');
							} else {
								alert('修改失败！');
							}
						})
					}
				} else {
					alert('请选择笔记！');
				}
			})

			jQuery('#remove').click(function () {
				var notetime = jQuery('#selectnote').val();
				if (notetime != 'blank') {
					jQuery.post('/TeachingManagementSystem/NoteRemoveServlet', { notetime: notetime }, function (t) {
						if (t == 'true') {
							alert('删除成功！');
							location.reload();
						} else {
							alert('删除失败！');
						}
					})
				} else {
					alert('请选择笔记！');
				}
			})
		})
	</script>

	<style>
		textarea {
			height: 512px;
		}

		input {
			background-color: #00BFFF;
			/* 土黄色 */
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
		#notegrade{
			width:100px;
		}
		#selectnote {
			width:180px;
		}
	</style>

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
	&nbsp;&nbsp;
		<select id="selectnote">
			<option value="blank">----------</option>
			<c:forEach items="${notes}" var="note">
				<option value="${note.notetime}">${note.notetime}</option>
			</c:forEach>
		</select>
	</div>

	<br />
	<br />

	<div class="notes">
		<textarea rows="1000" cols="130" id="note"></textarea>
	</div>
	<br />
	<div>
		<input type="button" id="SubmitNote" value="提交修改"><input type="button" id="remove" value="删除">
	</div>
</body>


</html>