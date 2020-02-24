<%@page pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="title.jsp" %>
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

    
    <div id="word"></div>
    <div id="container">
        <div id="photos">
        	<img src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1520351817705&di=408a36864e76e397a65c48b465af1fa1&imgtype=0&src=http%3A%2F%2Fimg.mp.sohu.com%2Fupload%2F20170518%2F2de1515f1f7d4514aafa93580b21b6f6.gif">
        	<img src="https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2557898900,1638618235&fm=27&gp=0.jpg">
        	<img src="http://pic-bucket.nosdn.127.net/photo/0001/2018-03-06/DC7NUCJ700AN0001NOS.jpg?imageView&thumbnail=900y600">
        	<img src="http://cms-bucket.nosdn.127.net/bfae522f37f44fbda653dd32a632d99820180305132648.jpeg?imageView&thumbnail=550x0">
        	<img src="http://pic-bucket.nosdn.127.net/photo/0001/2018-03-06/DC76BCC04T8E0001NOS.jpg?imageView&thumbnail=900y600">
        	<img src="http://pic-bucket.nosdn.127.net/photo/0001/2018-03-06/DC83DF7U00AP0001NOS.jpg?imageView&thumbnail=900y600">
        </div>
    </div>

    
        
    <div id="mid">
    	<c:forEach items="${data}" var="i" begin="1" end="${length }" step="1">
    	<a style="color:black" href="${i.address }">
            <div class="news">
                <p>${i.title }</p>
                <div class="pic"><img src="${i.imgurl }" alt=""></div>
                <div id="from">${i.from }</div>
            </div>
            </a>
       </c:forEach>
        
        </div>
        <div class="top">
                <img src="top.png" alt="">
        </div>
    
</body>
<script type="text/javascript">
        $(function(){
                $('.top').click(function(){
                        $('html,body').stop();
                        $('html,body').animate({
                                scrollTop:'0px'
                        },150);
                });
                $('.list').on('click','.start',function(){  
                    $(this).addClass("current").parent().siblings().find(".start").removeClass("current");
                });  
        })
        

</script>
</html>