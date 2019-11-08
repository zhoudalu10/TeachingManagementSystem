<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<meta charset="utf-8">
	<title>在线考试</title>
	<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
	<script>
		$.get("/TeachingManagementSystem/questionServlet", {}, function (data) {
			var d2 = $("#d2");
			var str = '';
			for (var i = 0; i < 10; i++) {
				str += '<li style="list-style:none">';
				str += '<p>' + '(' + (i + 1) + ')' + data[i].item + '</p>';
				str += "<div><input type='radio' name=" + data[i].id + " value='A'>(A)" + data[i].optionA + "</div>";
				str += "<div><input type='radio' name=" + data[i].id + "  value='B'>(B)" + data[i].optionB + "</div>";
				str += "<div><input type='radio' name=" + data[i].id + "  value='C'>(C)" + data[i].optionC + "</div>";
				str += "<div><input type='radio' name=" + data[i].id + "  value='D'>(D)" + data[i].optionD + "</div>";
				str += '</li>';
			}
			d2.html(str);
			var d3 = $("#d3");
			var str = '';
			for (var i = 10; i < 20; i++) {
				str += '<li style="list-style:none">';
				str += '<p>' + '(' + (i - 10 + 1) + ')' + data[i].item + '</p>';
				str += "<div><input type='checkbox' name=" + data[i].id + " value='A'>(A)" + data[i].optionA + "</div>";
				str += "<div><input type='checkbox' name=" + data[i].id + "  value='B'>(B)" + data[i].optionB + "</div>";
				str += "<div><input type='checkbox' name=" + data[i].id + "  value='C'>(C)" + data[i].optionC + "</div>";
				if (data[i].optionD != null) (str += "<div><input type='checkbox' name=" + data[i].id + "  value='D'>(D)" + data[i].optionD + "</div>");
				str += '</li>';
			}
			d3.html(str);
		});
		var maxtime = 20 * 60;
		function CountDown() {
			if (maxtime >= 0) {
				var minutes = Math.floor(maxtime / 60);
				var seconds = Math.floor(maxtime % 60);
				var msg = "距离考试结束还剩" + minutes + "分" + seconds + "秒";
				document.all["timer"].innerHTML = msg;
				if (maxtime == 30 * 60) alert("还剩5分钟");
				--maxtime;
			} else {
				clearInterval(timer);
				alert("时间到，结束!");
				document.getElementById("frm").submit();

			}
		}
		timer = setInterval("CountDown()", 1000);   
	</script>
	<style>
		#timer,
		#warring {
			position: fixed;
			top: 5%;
			left: 84%;
			color: #FF5722;
			font: bold 18px 'Arial Black';
		}

		.title {
			font: bold 36px '黑体';
			text-align: center;

		}

		#d2,
		#d3 {
			text-align: left;
		}

		body {
			background: rgb(225, 233, 220);
		}

		#btn {
			text-align: center;
		}
	</style>
</head>

<body>
	<div id="timer" style="color:red"></div>
	<div id="warring" style="color:red"></div>
	<form id="frm" action="/TeachingManagementSystem/resultServlet" method="post">
		<br><br>
		<h1 class="title">在线考试</h1>
		<br><br>
		<section class="panel panel-default">
			<div class="panel-heading">
				<h1>一、单选题（每题4分，共40分）</h1>
			</div>
			<div id="d2" class="panel-body"></div>
		</section>

		<section class="panel panel-default">
			<div class="panel-heading">
				<h1>二、多选题（每题6分，共60分）</h1>
			</div>
			<div id="d3" class="panel-body"></div>
		</section>

		<div id="btn">
			<button type="submit" class="btn btn-default">交卷</button>
		</div>
	</form>
</body>

</html>