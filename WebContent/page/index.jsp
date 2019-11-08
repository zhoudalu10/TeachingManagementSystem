<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.iweb.entity.Use"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	Use u = (Use) request.getSession().getAttribute("use");
%>
<!DOCTYPE html>

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<!-- Tell the browser to be responsive to screen width -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
	<meta name="author" content="">
	<title>主页</title>
	<!-- Bootstrap Core CSS -->
	<link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<!-- chartist CSS -->
	<link href="assets/plugins/chartist-js/dist/chartist.min.css" rel="stylesheet">
	<link href="assets/plugins/chartist-js/dist/chartist-init.css" rel="stylesheet">
	<link href="assets/plugins/chartist-plugin-tooltip-master/dist/chartist-plugin-tooltip.css" rel="stylesheet">
	<!--This page css - Morris CSS -->
	<link href="assets/plugins/c3-master/c3.min.css" rel="stylesheet">
	<!-- Custom CSS -->
	<link href="css/style.css" rel="stylesheet">
	<!-- You can change the theme colors from here -->
	<link href="css/colors/blue.css" id="theme" rel="stylesheet">
	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
</head>

<body class="fix-header fix-sidebar card-no-border">
	<!-- ============================================================== -->
	<!-- Preloader - style you can find in spinners.css -->
	<!-- ============================================================== -->
	<div class="preloader">
		<svg class="circular" viewBox="25 25 50 50">
			<circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="2" stroke-miterlimit="10" /> </svg>
	</div>
	<!-- ============================================================== -->
	<!-- Main wrapper - style you can find in pages.scss -->
	<!-- ============================================================== -->
	<div id="main-wrapper">
		<!-- ============================================================== -->
		<!-- Topbar header - style you can find in pages.scss -->
		<!-- ============================================================== -->
		<header class="topbar">
			<nav class="navbar top-navbar navbar-toggleable-sm navbar-light">
				<div class="navbar-collapse">
					<ul class="navbar-nav my-lg-0">
						<!-- ============================================================== -->
						<!-- Profile -->
						<!-- ============================================================== -->
						<li class="nav-item dropdown">
							<a class="nav-link dropdown-toggle text-muted waves-effect waves-dark"
								href="/TeachingManagementSystem/Logout" 
								onclick="if(confirm('确定退出?')==false)return false;">
								<img src="assets/images/users/1.jpg" alt="user" class="profile-pic m-r-10" />
								${use.uname}
							</a>
						</li>
					</ul>
				</div>
			</nav>
		</header>
		<!-- ============================================================== -->
		<!-- End Topbar header -->
		<!-- ============================================================== -->
		<!-- ============================================================== -->
		<!-- Left Sidebar - style you can find in sidebar.scss  -->
		<!-- ============================================================== -->
		<aside class="left-sidebar">
			<!-- Sidebar scroll-->
			<div class="scroll-sidebar">
				<!-- Sidebar navigation-->
				<nav class="sidebar-nav">
					<ul id="sidebarnav">

						<li>
							<a class="waves-effect waves-dark" href="" aria-expanded="false">
								<i class="mdi mdi-account-check"></i>
								<span class="hide-menu">笔记&画板</span>
							</a>
							<ul>
								<iframe src="Link.html" width="190" height="180" frameborder="no"
									style="display: block"></iframe>
							</ul>
						</li>
						<li>
							<a class="waves-effect waves-dark" href="" aria-expanded="false">
								<i class="mdi mdi-table"></i>
								<span class="hide-menu">排课系统</span>
							</a>
							<ul>
								<iframe src="SysLink.html" width="190" height="400" frameborder="no"
									style="display: block"></iframe>
							</ul>
						</li>

					</ul>
				</nav>
				<!-- End Sidebar navigation -->
			</div>
			<!-- End Sidebar scroll-->
		</aside>
		<!-- ============================================================== -->
		<!-- End Left Sidebar - style you can find in sidebar.scss  -->
		<!-- ============================================================== -->
		<!-- ============================================================== -->
		<!-- Page wrapper  -->
		<!-- ============================================================== -->
		<div class="page-wrapper">
			<!-- ============================================================== -->
			<!-- Container fluid  -->
			<!-- ============================================================== -->
			<div class="container-fluid">
				<!-- ============================================================== -->
				<!-- Bread crumb and right sidebar toggle -->
				<!-- ============================================================== -->
				<div class="row page-titles">
					<div class="col-md-5 col-8 align-self-center">
						<h3 name='suoyin1' class="text-themecolor"></h3>
						<ol class="breadcrumb">
							<li class="breadcrumb-item">
								<a href="index.jsp">Home</a>
							</li>
							<li name='suoyin1' class="breadcrumb-item active"></li>
						</ol>
					</div>
				</div>


				<div>
					<iframe name='content' src="" width='1248' height='1000' frameborder="no"></iframe>
				</div>


			</div>
			<!-- ============================================================== -->
			<!-- End Container fluid  -->
			<!-- ============================================================== -->
			<!-- ============================================================== -->
			<!-- footer -->
			<!-- ============================================================== -->
			<footer class="footer">在线管理系统</footer>
			<!-- ============================================================== -->
			<!-- End footer -->
			<!-- ============================================================== -->
		</div>
		<!-- ============================================================== -->
		<!-- End Page wrapper  -->
		<!-- ============================================================== -->
	</div>
	<!-- ============================================================== -->
	<!-- End Wrapper -->
	<!-- ============================================================== -->
	<!-- ============================================================== -->
	<!-- All Jquery -->
	<!-- ============================================================== -->
	<script src="assets/plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap tether Core JavaScript -->
	<script src="assets/plugins/bootstrap/js/tether.min.js"></script>
	<script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
	<!-- slimscrollbar scrollbar JavaScript -->
	<script src="js/jquery.slimscroll.js"></script>
	<!--Wave Effects -->
	<script src="js/waves.js"></script>
	<!--Menu sidebar -->
	<script src="js/sidebarmenu.js"></script>
	<!--stickey kit -->
	<script src="assets/plugins/sticky-kit-master/dist/sticky-kit.min.js"></script>
	<!--Custom JavaScript -->
	<script src="js/custom.min.js"></script>
	<!-- ============================================================== -->
	<!-- This page plugins -->
	<!-- ============================================================== -->
	<!-- chartist chart -->
	<script src="assets/plugins/chartist-js/dist/chartist.min.js"></script>
	<script src="assets/plugins/chartist-plugin-tooltip-master/dist/chartist-plugin-tooltip.min.js"></script>
	<!--c3 JavaScript -->
	<script src="assets/plugins/d3/d3.min.js"></script>
	<script src="assets/plugins/c3-master/c3.min.js"></script>
	<!-- Chart JS -->
	<script src="js/dashboard1.js"></script>
</body>
<script>
	$(function () { ($('.sidebar-nav>ul>li').children('ul').children('iframe').hide()) });
	$(function () {
		$('.sidebar-nav>ul>li').click(function () {
			$(this).children('ul').children('iframe').show().parent().parent().siblings('li').children('ul').children('iframe').hide();
		})
	});  
</script>

</html>