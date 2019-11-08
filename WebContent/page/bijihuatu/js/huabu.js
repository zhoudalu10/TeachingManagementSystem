var canvas;// 定义全局画布
var context;// 定义全局context
var img1 = [];// 储存图像数组，用于撤销
var canX;// 画布左上角的x坐标
var canY;// 画布左上角y坐标

function resizeCanvas(){
	dw = $(window).width();
	dw -= 30;
	dh = $(window).height();
	dh -=100;	
	//$('#cavs').css({'width':dw, 'height':dh});
	/*
	 * 我们一般通过canvas元素的width和height属性来改变元素的大小，我们也可以通过CSS来设置canvas元素的大小。但是，两种设置方式效果是不一样的。
        使用CSS来设置canvas元素的大小与直接设置属相相比，其差别是基于这样一个事实：canvas元素实际上有两套尺寸。一个是元素本身的大小，还有一个是元素绘图表面的大小。
       而设置元素的width和height属性时，实际上市同时修改了改元素本身的大小与元素绘图表面的大小。然而，如果是通过CSS来设定canvas元素的大小，那么只会改变元素本身的大小，而不会影响到绘图表面。
	 */
	$('#cavs').attr('width', dw).attr('height', dh);
}

$(function() {
	resizeCanvas();	
	canvas = $('#cavs')[0];// 获取画布的dom	
	canX = canvas.offsetLeft;// 获取画布左上角的x坐标
	canY = canvas.offsetTop;// 获取画布左上角的y坐标
	context = canvas.getContext('2d');// 获取context
	context.lineCap = "round";// 线条起始和结尾样式
	context.lineJoin = "round";// 线条转弯样式
	context.lineWidth = $('#size').val();
	initDrawLine();
	
	$('#line').click(function(event) {// 点击线条按钮，获取线条对象
		context.lineWidth = $('#size').val();
		context.strokeStyle = $("#color").val();
		initDrawLine();
	});
	$('#xpc').click(function(event) {//橡皮擦
		context.strokeStyle = '#fff';
		context.lineWidth = $('#size').val()*5;
		initEraseLine();
	});
	$('#clear').click(function() {
		context.clearRect(0, 0, canvas.width, canvas.height);
	});
	$('#cancel').click(function() {
		context.putImageData(img1.pop(), 0, 0);
	});	
	$("#save").click(save);	
	window.onresize = resizeCanvas;
});

function initDrawLine() {
	var painting = false;// 初始化设置为不可画状态
	var p_x;// 画笔初始x坐标，相对于Canvas
	var p_y;// 画笔初始y坐标
	$('#cavs').mousemove(function(e) {// 当鼠标在画布上移动时执行
		if (painting === true) {// 判断是否是可绘画状态
			var x = e.pageX - canX;// 鼠标当前x坐标
			var y = e.pageY - canY;// 鼠标当前y坐标
			context.lineTo(x, y);// 确定线的结束位置
			context.stroke();
		}
		
	});
	$('#cavs').mousedown(function(e) {// 当鼠标按下时触发
		painting = true;// 鼠标按下可以作画
		p_x = e.pageX - canX;// 画笔起始x坐标
		p_y = e.pageY - canY;// 画笔起始y坐标
		context.beginPath();// 开始作画
		context.moveTo(p_x, p_y);// 画笔开始位置
	});
	$('#cavs').mouseup(function(e) {
		painting = false;// 鼠标松开，禁止作画
		context.closePath();// 关闭画笔路径
		
		// 复制图像，为了撤销
		var imgData = context.getImageData(0, 0, canvas.width, canvas.height);
		// 加入数组
		img1.push(imgData);
	});
	$('#cavs').mouseleave(function(e) {// 鼠标移出时，禁止作画
		painting = false;
		context.closePath();
	});
	$("#color").change(function(e) {// 当颜色改变时触发
		context.strokeStyle = $(this).val();// 改变画笔颜色
	});
	$("#size").change(function(e) {//当线条宽度改变时触发
		context.lineWidth = $(this).val();
	});
}

function initEraseLine() {
	var painting = false;// 初始化设置为不可画状态
	var p_x;// 画笔初始x坐标
	var p_y;// 画笔初始y坐标
	//console.log(context.strokeStyle);
	$('#cavs').mousemove(function(e) {// 当鼠标在画布上移动时执行
		if (painting === true) {// 判断是否是可绘画状态
			var x = e.pageX - canX;// 鼠标当前x坐标
			var y = e.pageY - canY;// 鼠标当前y坐标
			context.lineTo(x, y);// 确定线的结束位置，canvas.offsetLeft画板离浏览器左侧的距离，canvas.offsetTop画板离浏览器上部的距离
			context.stroke();
		}
	});
	$('#cavs').mousedown(function(e) {// 当鼠标按下时触发
		painting = true;// 鼠标按下可以作画
		p_x = e.pageX - canX;// 画笔起始x坐标
		p_y = e.pageY - canY;// 画笔起始y坐标
		context.beginPath();// 开始作画
		context.moveTo(p_x, p_y);// 画笔开始位置
		$('#cavs').css('cursor', 'pointer');// 将鼠标图形设置成小手
		// 复制图像，为了撤销
		var imgData = context.getImageData(0, 0, canvas.width,
				canvas.height);
		// 加入数组
		img1.push(imgData);
	});
	$('#cavs').mouseup(function(e) {
		painting = false;// 鼠标松开，禁止作画
		context.closePath();// 关闭画笔路径
		$('#cavs').css('cursor', '');// 消除鼠标小手
	});
	$('#cavs').mouseleave(function(e) {// 鼠标移出时，禁止作画
		painting = false;
		context.closePath();
	});
	$("#color").change(function(e) {// 当颜色改变时触发
		context.strokeStyle = '#fff';
	});
	$("#size").change(function(e) {
		context.lineWidth = $(this).val()*5;
	});
}

function save() {
	// 保存图片，下载到本地
	var type = 'png';
	var imgData = $('#cavs')[0].toDataURL(type);
	/**
	 * 获取mimeType
	 * 
	 * @param {String}
	 *            type the old mime-type
	 * @return the new mime-type
	 */
	var _fixType = function(type) {
		type = type.toLowerCase().replace(/jpg/i, 'jpeg');
		var r = type.match(/png|jpeg|bmp|gif/)[0];
		return 'image/' + r;
	};
	// 加工image data，替换mime type
	imgData = imgData.replace(_fixType(type), 'image/octet-stream');
	/**
	 * 在本地进行文件保存
	 * 
	 * @param {String}
	 *            data 要保存到本地的图片数据
	 * @param {String}
	 *            filename 文件名
	 */
	var saveFile = function(data, filename) {
		var save_link = document.createElementNS(
				'http://www.w3.org/1999/xhtml', 'a');
		save_link.href = data;
		save_link.download = filename;

		var event = document.createEvent('MouseEvents');
		event.initMouseEvent('click', true, false, window, 0, 0, 0, 0, 0,
				false, false, false, false, 0, null);
		save_link.dispatchEvent(event);
	};
	// 下载后的文件名
	var filename = (new Date()).getTime() + '.' + type;
	// download
	saveFile(imgData, filename);
}