<%@page import="com.iweb.DAO.GradeDAO"%>
<%@page import="java.util.List"%>
<%@page import="com.iweb.entity.Grade"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	List<Grade> grades = GradeDAO.all();
	request.setAttribute("grades", grades);
%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta http-equiv="X-UA-Compatible" content="ie=edge" />
	<title>Login</title>
	<script src="/TeachingManagementSystem/page/jquery-1.10.2.js"></script>
	<script type="text/javascript">
		//验证用户名密码格式
		var regflagname = false;
		var regflagpwd = false;
		$(function () {
			$("#uname").blur(function () { //失去焦点
				var namestr = $(this).val();
				var regstr = /^[\u4e00-\u9fa5a-zA-Z0-9]{2,10}$/;
				if (!regstr.test(namestr)) {
					$("#errname").html("用户名必须是2-10个汉字或字母");
					regflagname = false;
				} else {
					regflagname = true;
				}
			});
			$("#uname").focus(function () { //获取焦点
				$(this).val("");
				$("#errname").html("");
			});
		});

		$(function () {
			$("#pwd").blur(function () { //失去焦点
				var namestr = $(this).val();
				var regstr = /^[a-zA-Z0-9]{4,10}$/;
				if (!regstr.test(namestr)) {
					$("#errpwd").html("密码必须为4-10位数字或字母");
					regflagpwd = false;
				} else {
					regflagpwd = true;
				}
			});
			$("#pwd").focus(function () { //获取焦点
				$(this).val("");
				$("#errpwd").html("");
			});
		});

		$(document).ready(function () {
			$('#register').click(function () {
				if (regflagname == true && regflagpwd == true) {
					var data = $('#uname').val() + '-';
					data += $('#pwd').val() + '-';
					data += $('#pwded').val() + '-';
					data += $('#position').val() + '-';
					data += $('#uclass').val();
					$.post('/TeachingManagementSystem/RegisteredServlet', { data: data }, function (t) {
						if (t == '0') {
							alert('注册成功');
							location.reload();
						} else if (t == '1') {
							alert('注册失败，用户名已存在');
						} else {
							alert('两次密码不一致。请重新输入');
						}
					});
				}
			});

			$('#login').click(function () {
				var uname = $('#loginuname').val();
				var pwd = $('#loginpwd').val();
				$.post('/TeachingManagementSystem/LoginServlet', { uname: uname, pwd: pwd }, function (t) {
					if (t == '1') {
						window.location.href = "/TeachingManagementSystem/page/indexstu.jsp";
					} else if (t == '2') {
						window.location.href = "/TeachingManagementSystem/page/index.jsp";
					} else {
						alert('用户名或密码错误！');
					}
				})
			})
		})
	</script>
	<style>
		* {
			font-family: 'montserrat', sans-serif;
		}

		body {
			margin: 0;
			padding: 0;
			background: #1e88e5;
		}

		.login-box {
			position: absolute;
			top: 0;
			left: -100%;
			width: 100%;
			height: 100vh;
			/* vh 视口高度 viewport height 百分比单位*/
			background-image: linear-gradient(45deg, #26c6da, #1e88e5);
			/*设置颜色渐变 方向(0deg垂直向上) 起点颜色 终点颜色*/
			transition: 0.4s;
			/*过度效果  property duration timing-function delay 默认属性:all 0 ease 0*/
		}

		.register-box {
			position: absolute;
			top: 0;
			left: -100%;
			width: 100%;
			height: 100vh;
			/* vh 视口高度 viewport height 百分比单位*/
			background-image: linear-gradient(45deg, #26c6da, #1e88e5);
			/*设置颜色渐变 方向(0deg垂直向上) 起点颜色 终点颜色*/
			transition: 0.4s;
			/*过度效果  property duration timing-function delay 默认属性:all 0 ease 0*/
		}

		.login-form {
			position: absolute;
			top: 50%;
			left: 50%;
			transform: translate(-50%, -50%);
			/*定义 2D 转换8 */
			color: white;
			text-align: center;
		}

		.login-form h1 {
			font-weight: 400;
			margin-top: 0;
		}

		.register-form {
			position: absolute;
			top: 50%;
			left: 50%;
			transform: translate(-50%, -50%);
			/*定义 2D 转换8 */
			color: white;
			text-align: center;
		}

		.register-form h1 {
			font-weight: 400;
			margin-top: 0;
		}

		.txtb {
			display: block;
			box-sizing: border-box;
			width: 240px;
			background: #ffffff28;
			border: 1px solid white;
			padding: 10px 20px;
			color: white;
			-webkit-text-fill-color: black;
			outline: none;
			margin: 10px 0;
			border-radius: 6px;
			text-align: center;
		}

		.txtc {
			display: block;
			box-sizing: border-box;
			width: 240px;
			background: #ffffff28;
			border: 1px solid white;
			padding: 10px 20px;
			color: BLACK;
			outline: none;
			margin: 10px 0;
			border-radius: 6px;
			text-align: center;
		}

		.login-btn {
			width: 240px;
			background: #2c3e50;
			border: 0;
			color: white;
			padding: 10px;
			border-radius: 6px;
			cursor: pointer;
		}

		.register-btn {
			width: 240px;
			background: #2c3e50;
			border: 0;
			color: white;
			padding: 10px;
			border-radius: 6px;
			cursor: pointer;
		}

		.hide-login-btn {
			color: #000;
			position: absolute;
			top: 40px;
			right: 40px;
			cursor: pointer;
			font-size: 30px;
			opacity: 0.7;
			transform: rotate(45deg);
			/*定义 2D 转换8 */
		}

		.hide-register-btn {
			color: #000;
			position: absolute;
			top: 40px;
			right: 40px;
			cursor: pointer;
			font-size: 30px;
			opacity: 0.7;
			transform: rotate(45deg);
			/*定义 2D 转换8 */
		}

		.show-login-btn {
			position: absolute;
			top: 45%;
			left: 50%;
			transform: translate(-50%, -50%);
			color: white;
			border: 2px solid;
			padding: 10px;
			cursor: pointer;
		}

		.show-register-btn {
			position: absolute;
			top: 55%;
			left: 50%;
			transform: translate(-50%, -50%);
			color: white;
			border: 2px solid;
			padding: 10px;
			cursor: pointer;
		}

		.showed {
			left: 0;
		}

		.aaa {
			position: absolute;
			top: 30%;
			left: 50%;
			transform: translate(-50%, -50%);
			color: white;
			font-size: 50px;
		}
	</style>
</head>

<body>
	<div class="aaa">教学辅助管理系统</div>
	<div class="show-login-btn">-> 登录</div>

	<div class="show-register-btn">-> 注册</div>

	<div class="login-box">
		<div class="hide-login-btn">+</div>
		<form action="/TeachingManagementSystem/LoginServlet" method="POST" class="login-form">
			<h1>欢迎</h1>
			<input class="txtb" type="text" id="loginuname" placeholder="Username" />
			<input class="txtb" type="password" id="loginpwd" placeholder="Password" />
			<input class="login-btn" type="button" id="login" value="登录" />
		</form>
	</div>

	<div class="register-box">
		<div class="hide-register-btn">+</div>
		<form action="" method="POST" class="register-form">
			<h1>欢迎注册</h1>
			<input class="txtb" type="text" id="uname" placeholder="Username" />
			<h4 id="errname" style="color: red"></h4>
			<input class="txtb" type="password" id="pwd" placeholder="Password" />
			<h4 id="errpwd" style="color: red"></h4>
			<input class="txtb" type="password" id="pwded" placeholder="Password again" />
			<select class="txtc" id="uclass">
				<c:forEach items="${grades}" var="grade">
					<option value="${grade.name}">${grade.name}</option>
				</c:forEach>
			</select>
			<select class="txtc" id="position">
				<option value="学生">学生</option>
				<option value="教师">教师</option>
			</select>
			<input class="register-btn" type="button" id="register" value="注册" />


		</form>
	</div>


	<script type="text/javascript">
		function hasClass(element, clssname) {
			return element.className.match(new RegExp('(\\s|^)' + clssname
				+ '(\\s|$)'))
		}
		function addClass(element, clssname) {
			if (!this.hasClass(element, clssname))
				element.className += ' ' + clssname
		}
		function removeClass(element, clssname) {
			if (hasClass(element, clssname)) {
				var reg = new RegExp('(\\s|^)' + clssname + '(\\s|$)')
				element.className = element.className.replace(reg, ' ')
			}
		}
		function toggleClass(element, clssname) {
			if (hasClass(element, clssname)) {
				removeClass(element, clssname)
			} else {
				addClass(element, clssname)
			}
		}
		var loginBox = document.getElementsByClassName('login-box')
		var showBtnl = document.getElementsByClassName('show-login-btn')
		var hideBtnl = document.getElementsByClassName('hide-login-btn')
		var registerBox = document.getElementsByClassName('register-box')
		var showBtnr = document.getElementsByClassName('show-register-btn')
		var hideBtnr = document.getElementsByClassName('hide-register-btn')

		showBtnl[0].addEventListener('click', function () {
			toggleClass(loginBox[0], 'showed')
		})
		hideBtnl[0].addEventListener('click', function () {
			toggleClass(loginBox[0], 'showed')
		})

		showBtnr[0].addEventListener('click', function () {
			toggleClass(registerBox[0], 'showed')
		})
		hideBtnr[0].addEventListener('click', function () {
			toggleClass(registerBox[0], 'showed')
		})
	</script>
</body>

</html>