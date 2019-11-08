<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>成绩展示</title>
    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <style>
        span {
            font: 20px 'Arial Black';
        }
    </style>
    <script>
        $(function (){
            var num=1;
            var num2=1;
            var d2 =$("#d2");
            var d3 =$("#d3");
            var str1 ='';
            var str2 ='';
            <c:forEach items="${errors}" var="obj" varStatus="i">
            <c:if test="${obj.type eq 1}">
            str1+='<li style="list-style:none">';
            str1+='<p>'+'('+num+')'+'${obj.item}</p>';
            str1+="<div>(A)${obj.optionA}</div>";
            str1+="<div>(B)${obj.optionB}</div>";
            str1+="<div>(C)${obj.optionC}</div>";
            str1+="<div>(D)${obj.optionD}</div>";
            str1+='<p>答案:${obj.answer}</p>';
            str1+='</li>';
            num++;
            </c:if>
            <c:if test="${obj.type eq 2}">
            str2+='<li style="list-style:none">';
            str2+='<p>'+'('+num2+')'+'${obj.item}</p>';
            str2+="<div>(A)${obj.optionA}</div>";
            str2+="<div>(B)${obj.optionB}</div>";
            str2+="<div>(C)${obj.optionC}</div>";
            str2+="<div>(D)${obj.optionD}</div>";
            str2+='<p>答案:${obj.answer}</p>';
            str2+='</li>';
            num2++;
            </c:if>
            </c:forEach>
            d2.html(str1);
            d3.html(str2);
        });

    </script>
</head>
<body>
<p style="font:bold 24px 'Arial Black';text-align: center;margin-top:20px" >最终成绩为${score}分</p>
<hr>
<form>
    <br><br>
    <span style="color:red">错题回顾</span><br><br>
    <section class="panel panel-default">
        <div class="panel-heading"><span>单选题</span></div>
        <div id="d2" class="panel-body"></div>
    </section>

    <section class="panel panel-default">
        <div class="panel-heading"><span>多选题</span></div>
        <div id="d3" class="panel-body"></div>
    </section>

</form>
</body>
</html>
