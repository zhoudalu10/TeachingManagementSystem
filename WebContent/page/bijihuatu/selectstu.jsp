<%@page import="com.iweb.entity.Use"%>
<%@page import="com.iweb.entity.Grade"%>
<%@page import="com.iweb.DAO.GradeDAO"%>
<%@page import="com.iweb.DAO.NoteDAO"%>
<%@page import="com.iweb.entity.Note"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	Use user = (Use) session.getAttribute("use");
	String notegrade = user.getUclass();
	List<Note> notes = NoteDAO.allNote(notegrade);
	request.setAttribute("notes", notes);
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="utf-8">
<script src="/TeachingManagementSystem/page/jquery-1.10.2.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function() {
		jQuery('#selectnote').change(function() {
			$('#note').val('');
			var notetime = jQuery('#selectnote').val();
			if (notetime != 'blank') {
				jQuery.post('/TeachingManagementSystem/NoteSelectServlet', {
					notetime : notetime
				}, function(t) {
					jQuery('#note').val(t);
				})
			} else {
				jQuery('#note').val('');
			}

		})
	})
</script>

<style>
textarea {
	height: 512px;
}

button {
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
	background-color: white;
}
#selectnote {
			width:180px;
}
</style>

</head>

<body>

	<div>
		请选择笔记：
	</div>
	
	<div>
		<select id="selectnote">
			<option value="blank">----------</option>
			<c:forEach items="${notes}" var="note">
				<option value="${note.notetime}">${note.notetime}</option>
			</c:forEach>
		</select>
	</div>
	<br />
	<div class="notes">
		<textarea rows="1000" cols="130" id="note" disabled="disabled"></textarea>
	</div>


</body>


</html>