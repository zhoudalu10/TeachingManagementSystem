<%@page import="com.iweb.DAO.ClassroomDAO"%>
<%@page import="com.iweb.entity.Classroom"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.iweb.DAO.GradeDAO"%>
<%@page import="com.iweb.entity.Grade"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	List<Grade> grades = GradeDAO.all();
	List<Classroom> classrooms = ClassroomDAO.all();
	request.setAttribute("grades", grades);
	request.setAttribute("classrooms", classrooms);
%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style type="text/css">
		.grade {
			width: 1300px;
			height: 240px;
		}

		.month {
			width: 210px;
			height: 210px;
			background-color: #fff;
			border-collapse: collapse;
			border-style: solid;
			border-width: 0.5px;
			float: left;
			margin-left: 1px;
			border-color: #fff;
			position: relative;
			z-index: 1;
		}

		div.month:after {
			content: "";
			background: url(/TeachingManagementSystem/img/divbody.jpg);
			opacity: 0.5;
			top: 0;
			left: 0;
			right: 0;
			bottom: 0;
			position: absolute;
			z-index: -1;
		}

		.innerblank {
			width: 29px;
			height: px;
			background-color: #fff;
			border-style: solid;
			border-width: 0.5px;
			float: left;
			border-color: #fff;
			opacity: 0.8;
		}

		.innernumber {
			width: 29px;
			height: 29px;
			background-color: #fff;
			border-style: solid;
			border-width: 0.5px;
			float: left;
			border-color: #fff;
			text-align: center;
			opacity: 0.8;
		}

		.halfday {
			width: 100%;
			height: 50%;
			background-color: #fff;
			border-style: dashed;
			border-width: 0px;
			font-size: 10px;
		}

		.innerweekday {
			width: 29px;
			height: 25px;
			line-height: 25px;
			border-style: solid;
			border-width: 0.5px;
			float: left;
			border-color: #fff;
			background: url(/TeachingManagementSystem/img/head.jpg);
			text-align: center;
		}

		button {
			background-color: #00BFFF;
			/* 土黄色 */
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

		#t {
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

		table {
			text-align: center;
		}

		th {
			border-style: solid;
			border-color: black;
			border-width: 1px;
			background-color: #eaeaea;
		}

		.gradename {
			width: 130px;
		}

		.course {
			width: 170px;
		}

		.nowcourse {
			width: 110px;
		}

		.leftcourse {
			width: 110px;
		}

		.personnum {
			width: 110px;
		}

		.selectroom {
			width: 200px;
		}

		.coursemode {
			width: 200px;
		}

		.reset {
			width: 200px;
		}
	</style>
	<script src="/TeachingManagementSystem/page/jquery-1.10.2.js"></script>
	<script type="text/javascript">
		$(document).ready(function () {
			//取消网页默认右击事件
			$("div").bind("contextmenu", function () {
				return false;
			})
			//自定义点击事件
			$("div").mousedown(function (e) {
				//3为右击
				var selectval = $(this).parents('.gradeout').find('.classroom').val();
				if (3 == e.which) {
					if (selectval != 'blank') {
						var mode = $(this).parents('.gradeout').find('#mode').val();
						var index_t = $(this).attr('idx');
						var status_t = $(this).attr('status');
						var gno = $(this).parents('.gradeout').find('.classroom').attr('gno');
						var left = $(this).parents('.grade').attr('left');
						var countstatus = 0;
						$(this).parents('.grade').find('.halfday').each(function (j, k) {
							var k_status = $(k).attr('status');
							if (k_status == '1') {
								countstatus++;
							}
						})
						switch (mode) {
							case '0':
								//判断当前div状态 1为已排课
								if (status_t == 1) {
									//遍历 找到第一个状态为0的div 切换状态
									eachmode0(index_t, selectval, gno);
									$(this).attr('status', '0');
									$(this).css('background-color', '#fff');
								} else if (status_t == 0 && otherstatus(selectval, gno, index_t)) {
									each($($(this).parents('.gradeout').find('.halfday').get().reverse()));
									$(this).attr('status', '1');
									$(this).css('background-color', 'rgb(189, 255, 188)');
								}
								break;
							case '1':
								//判断当前div状态 1为已排课
								if (status_t == 1) {
									//遍历 找到第一个状态为0的div 切换状态
									eachmode1(index_t, selectval, gno);
									$(this).attr('status', '0');
									$(this).css('background-color', '#fff');
								} else if (status_t == 0 && otherstatus(selectval, gno, index_t)) {
									each($($(this).parents('.gradeout').find('.halfday').get().reverse()));
									$(this).attr('status', '1');
									$(this).css('background-color', 'rgb(189, 255, 188)');
								}
								break;
							case '2':
								//判断当前div状态 1为已排课
								if (status_t == 1) {
									//遍历 找到第一个状态为0的div 切换状态
									eachmode2(index_t, selectval, gno);
									$(this).attr('status', '0');
									$(this).css('background-color', '#fff');
								} else if (status_t == 0 && otherstatus(selectval, gno, index_t)) {
									each($($(this).parents('.gradeout').find('.halfday').get().reverse()));
									$(this).attr('status', '1');
									$(this).css('background-color', 'rgb(189, 255, 188)');
								}
								break;
							case '3':
								//判断当前div状态 1为已排课
								if (status_t == 1) {
									//遍历 找到第一个状态为0的div 切换状态
									eachmode3(index_t, selectval, gno);
									$(this).attr('status', '0');
									$(this).css('background-color', '#fff');
								} else if (status_t == 0 && otherstatus(selectval, gno, index_t)) {
									each($($(this).parents('.gradeout').find('.halfday').get().reverse()));
									$(this).attr('status', '1');
									$(this).css('background-color', 'rgb(189, 255, 188)');
								}
								break;
							case '4':
								if (status_t == 1) {
									$(this).attr('status', '0');
									$(this).css('background-color', '#fff');
								} else if (status_t == 0 && otherstatus(selectval, gno, index_t) && countstatus - left < 0) {
									$(this).attr('status', '1');
									$(this).css('background-color', 'rgb(189, 255, 188)');
								}
								break;
						}
					} else {
						alert('请先选择教室！');
					}
				}
			})

			//遍历函数
			function eachmode0(index_t, selectval, gno) {
				$("[class='gradeout'][gno=" + gno + "]").find('.halfday').each(function (i, o) {
					var odx = $(o).attr('idx');
					if (odx - index_t > 0) {
						if (otherstatus(selectval, gno, odx) && ($(o).attr('status') == 0)) {
							$(o).attr('status', '1');
							$(o).css('background-color', 'rgb(189, 255, 188)');
							return false;
						}
					}
				});
			}

			function eachmode1(index_t, selectval, gno) {
				var countBlank = $("[class='gradeout'][gno=" + gno + "]").find('.grade').attr('countblank');
				$("[class='gradeout'][gno=" + gno + "]").find('.halfday').each(function (i, o) {
					var odx = $(o).attr('idx');
					if (odx - index_t > 0) {
						if (otherstatus(selectval, gno, odx) && ($(o).attr('status') == 0)) {
							if ($(o).attr('idx') % 2 == 0) {
								if (($(o).attr('idx') / 2 - -countBlank) % 7 != 1) {
									$(o).attr('status', '1');
									$(o).css('background-color', 'rgb(189, 255, 188)');
									return false;
								}
							} else {
								if ((($(o).attr('idx') - -1) / 2 - -countBlank) % 7 != 1) {
									$(o).attr('status', '1');
									$(o).css('background-color', 'rgb(189, 255, 188)');
									return false;
								}
							}
						}
					}
				});
			}

			function eachmode2(index_t, selectval, gno) {
				var countBlank = $("[class='gradeout'][gno=" + gno + "]").find('.grade').attr('countblank');
				$("[class='gradeout'][gno=" + gno + "]").find('.halfday').each(function (i, o) {
					var odx = $(o).attr('idx');
					if (odx - index_t > 0) {
						if (otherstatus(selectval, gno, odx) && ($(o).attr('status') == 0)) {
							if ($(o).attr('idx') % 2 == 0) {
								if (($(o).attr('idx') / 2 - -countBlank) % 7 == 1 || ($(o).attr('idx') / 2 - -countBlank) % 7 == 0) {
									$(o).attr('status', '1');
									$(o).css('background-color', 'rgb(189, 255, 188)');
									return false;
								}
							} else {
								if ((($(o).attr('idx') - -1) / 2 - -countBlank) % 7 == 1 || (($(o).attr('idx') - -1) / 2 - -countBlank) % 7 == 0) {
									$(o).attr('status', '1');
									$(o).css('background-color', 'rgb(189, 255, 188)');
									return false;
								}
							}
						}
					}
				});
			}

			function eachmode3(index_t, selectval, gno) {
				$("[class='gradeout'][gno=" + gno + "]").find('.halfday').each(function (i, o) {
					var odx = $(o).attr('idx');
					if (odx - index_t > 0) {
						if (otherstatus(selectval, gno, odx) && ($(o).attr('status') == 0)) {
							if ($(o).attr('idx') % 2 == 0) {
								$(o).attr('status', '1');
								$(o).css('background-color', 'rgb(189, 255, 188)');
								return false;
							}
						}
					}
				});
			}



			//倒序遍历函数2
			function each(array) {
				array.each(function (i, o) {
					var ostatus = $(o).attr('status');
					if (ostatus == 1) {
						$(o).attr('status', '0');
						$(o).css('background-color', '#fff');
						return false;
					}
					temp = o;
				});
			}

			//左键点击事件 自动排课
			$('.halfday').click(function () {
				var selectval = $(this).parents('.gradeout').find('.classroom').val();
				if ($(this).attr('status') == 0) {
					if (selectval != 'blank') {
						var left = $(this).parents('.grade').attr('left');
						var gno = $(this).parents('.gradeout').find('.classroom').attr('gno');
						var idx = $(this).attr('idx');
						var mode = $(this).parents('.gradeout').find('#mode').val();
						var countBlank = $(this).parents('.grade').attr('countblank');

						$(this).parents('.grade').find('.halfday').each(function (i, o) {
							var odx = $(o).attr('idx');
							if (odx - idx >= 0) {
								var countstatus = 0;
								$(this).parents('.grade').find('.halfday').each(function (j, k) {
									var k_status = $(k).attr('status');
									if (k_status == '1') {
										countstatus++;
									}
								})
								if (countstatus - left < 0) {
									var boo = otherstatus(selectval, gno, odx);
									if (boo) {
										switch (mode) {
											case '0': $(o).attr('status', '1');
												$(o).css('background-color', 'rgb(189, 255, 188)');
												break;
											case '1':
												if ($(o).attr('idx') % 2 == 0) {
													if (($(o).attr('idx') / 2 - -countBlank) % 7 != 1) {
														$(o).attr('status', '1');
														$(o).css('background-color', 'rgb(189, 255, 188)');
													}
												} else {
													if ((($(o).attr('idx') - -1) / 2 - -countBlank) % 7 != 1) {
														$(o).attr('status', '1');
														$(o).css('background-color', 'rgb(189, 255, 188)');
													}
												}
												break;
											case '2':
												if ($(o).attr('idx') % 2 == 0) {
													if (($(o).attr('idx') / 2 - -countBlank) % 7 == 1 || ($(o).attr('idx') / 2 - -countBlank) % 7 == 0) {
														$(o).attr('status', '1');
														$(o).css('background-color', 'rgb(189, 255, 188)');
													}
												} else {
													if ((($(o).attr('idx') - -1) / 2 - -countBlank) % 7 == 1 || (($(o).attr('idx') - -1) / 2 - -countBlank) % 7 == 0) {
														$(o).attr('status', '1');
														$(o).css('background-color', 'rgb(189, 255, 188)');
													}
												}
												break;
											case '3':
												if ($(o).attr('idx') % 2 == 0) {
													$(o).attr('status', '1');
													$(o).css('background-color', 'rgb(189, 255, 188)');
												}
												break;
											case '4':
												break;
										}

									}
								}
							}
						})
					} else {
						alert('请先选择教室！');
					}
				}
			});

			function otherstatus(selectval, gno, odx) {
				var sameselectgno = [];

				$("[class='classroom'][gno!=" + gno + "]").each(function (i, o) {
					if ($(o).val() == selectval) {
						sameselectgno.push($(o).attr('gno'));
					}
				})

				var boo = true;
				for (var a = 0; a < sameselectgno.length; a++) {
					var otherstatus = $("[class='gradeout'][gno=" + sameselectgno[a] + "]").find("[idx=" + odx + "]").attr('status');
					if (otherstatus == '1') {
						boo = false;
					}
				}
				return boo;
			}

			$('.classroom').change(function () {
				$(this).parents('.gradeout').find('.halfday').each(function (i, o) {
					var odx = $(o).attr('idx');
					$(o).attr('status', '0');
					$(o).css('background-color', '#fff');
				});
			})

			$('.refresh').click(function () {
				$(this).parents('.gradeout').find('.halfday').each(function (i, o) {
					var odx = $(o).attr('idx');
					$(o).attr('status', '0');
					$(o).css('background-color', '#fff');
				});
			});

			$('#save').click(function () {
				var grade = [];
				$('.gradeout').each(function (i, o) {
					var classroom = $(o).find('.classroom').val();
					var gno = $(o).attr('gno');
					var rno = $(o).find('.classroom').find('option:selected').attr('rno');
					if (classroom != 'blank') {
						grade.push([]);
						grade[grade.length - 1].push('+' + $(o).find('.grade').attr('gname') + '-');
						grade[grade.length - 1].push(gno);
						grade[grade.length - 1].push(classroom + '-');
						grade[grade.length - 1].push(rno + '-');
					}
				});

				for (var i = 0; i < grade.length; i++) {
					$('[gno = "' + grade[i][1] + '"]').find('.halfday').each(function (index, o) {
						grade[i].push($(o).attr('status'));
					})

					grade[i][1] = grade[i][1] + '-';
				}

				var string = "";
				for (var i = 0; i < grade.length; i++) {
					for (var j = 0; j < grade[i].length; j++) {
						string += grade[i][j];
					}
				}
				$.post('SaveServlet', { string: string }, function (t) {
					if (t == 'true') {
						alert('保存数据成功！');
					} else {
						alert('保存数据失败！');
					}
				});
			});

			$('#output').click(function () {
				$.post('GetExcelServlet', function (t) {
					if (t == 'true') {
						alert('导出Excel成功！');
					} else {
						alert('导出Excel失败！');
					}
				});
			});
		});
	</script>
</head>

<body>
	<c:forEach items="${grades}" var="grade">
		<div class="gradeout" gno="${grade.no}">
			<table>
				<tr>
					<th class="gradename">班级名</th>
					<th class="course">课程名</th>
					<th class="nowcourse">当前课时进度</th>
					<th class="leftcourse">剩余课时</th>
					<th class="personnum">班级人数</th>
					<th class="selectroom">教室选择</th>
					<th class="coursemode">排课模式</th>
					<th class="reset">重新排课</th>
				</tr>
				<tr>
					<td class="gradename">${grade.name}</td>
					<td class="course">${grade.course}</td>
					<td class="nowcourse">${grade.pernow}</td>
					<td class="leftcourse">${grade.perleft}</td>
					<td class="personnum">${grade.snum}</td>
					<td class="selectroom">
						<select class="classroom" gno="${grade.no}">
							<option value="blank">--请选择教室--</option>
							<c:forEach items="${classrooms}" var="classroom">
								<c:if test="${grade.snum <= classroom.num}">
									<option value="${classroom.loc}" rno="${classroom.no}">
										${classroom.loc}/可容纳${classroom.num}人
									</option>
								</c:if>
							</c:forEach>
						</select>
					</td>
					<td class="coursemode">
						<select id="mode">
							<option value="0">默认模式（全天排课）</option>
							<option value="1">工作日模式（跳过周日）</option>
							<option value="2">周末模式（只排周六周日）</option>
							<option value="3">下午模式（只排下午）</option>
							<option value="4">右键手动排课模式</option>
						</select>
					</td>
					<td class="reset"><button type="button" class="refresh">重新排课</button></td>
				</tr>
			</table>

			<div class="grade" left="${grade.perleft}" gname="${grade.name}" countblank="${months.countBlank}">
				<c:forEach items="${months.months}" var="Month">

					<div class="month">
						<div class="daytime">${Month.year}年${Month.month}月</div>
						<div class="innerweekday">日</div>
						<div class="innerweekday">一</div>
						<div class="innerweekday">二</div>
						<div class="innerweekday">三</div>
						<div class="innerweekday">四</div>
						<div class="innerweekday">五</div>
						<div class="innerweekday">六</div>
						<c:forEach items="${Month.weekDay}" var="blank">
							<div class="innerblank">${blank}</div>
						</c:forEach>
						<c:forEach items="${Month.dayCountMonth}" var="day" varStatus="s">
							<div class="innernumber">
								<div class="halfday" idx="${(s.count + Month.dayCount) * 2 - 1}" status="0">${day}</div>
								<div class="halfday" idx="${(s.count + Month.dayCount) * 2}" status="0"></div>
							</div>

						</c:forEach>
					</div>
				</c:forEach>
				<br />
			</div>
		</div>
	</c:forEach>
	<button type="button" id="save">保存</button>
	<button type="button" id="output">导出</button>
	<a href="/TeachingManagementSystem/page/download.jsp" id="t">下载</a>
</body>

</html>