<%@page pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="css/style.css">
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>

    <title>Document</title>
    <style>
            ::-webkit-scrollbar {display:none}
    </style>
</head>
<body>
    <nav>
        <ul class="list">
            <a href="/Spider-Client/domestic.do"><li class="domestic">国内</li></a>
            <a href="/Spider-Client/culture.do"><li class="culture">文化</li></a>
            <a href="/Spider-Client/finance.do"><li class="finance">财经</li></a>
            <a href="/Spider-Client/war.do"><li class="war">军事</li></a>
            <a href="/Spider-Client/tech.do"><li class="tech">科技</li></a>
            <a href="/Spider-Client/sports.do"><li class="sports">体育</li></a>
            <a href="/Spider-Client/ent.do"><li class="ent">娱乐</li></a>
            <a href="/Spider-Client/learning.do"><li class="learning">教育</li></a>
            <a href="/Spider-Client/health.do"><li class="health">健康</li></a>
        </ul>
    </nav>
</body>
<script type="text/javascript">
        $(function(){
                var path = window.location.pathname;
                console.log(path);
                if ("/Spider-Client/health.do" == path){
                	$('.health').addClass("current");
                } else if ("/Spider-Client/culture.do" == path){
                	$('.culture').addClass("current");
                } else if ("/Spider-Client/finance.do" == path){
                	$('.finance').addClass("current");
                } else if ("/Spider-Client/war.do" == path){
                	$('.war').addClass("current");
                } else if ("/Spider-Client/tech.do" == path){
                	$('.tech').addClass("current");
                } else if ("/Spider-Client/sports.do" == path){
                	$('.sports').addClass("current");
                } else if ("/Spider-Client/ent.do" == path){
                	$('.ent').addClass("current");
                } else if ("/Spider-Client/learning.do" == path){
                	$('.learning').addClass("current");
                } else if ("/Spider-Client/domestic.do" == path){
                	$('.domestic').addClass("current");
                } 
        })
        

</script>
</html>