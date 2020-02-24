<%@page pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="css/style2.css">
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>

    <title>Document</title>
    <style>

            ::-webkit-scrollbar {display:none}
 
        </style>
</head>
<body>
    <div class="foripic">
        <ul>
            <li>
                <a  href="/Spider-Client/america-zh.do"><h3>美国(USA)</h3>
                <img src="pic/USA.jpg" alt=""></a>
                
            </li>
            <li>
                <a href="/Spider-Client/japan-zh.do"><h3>日本(Japan)</h3>
                <img src="pic/Japan.jpg" alt=""></a>
                
            </li>
        </ul>
    </div>
    
        
    <div id="mid">
        <c:forEach items="${data}" var="i" begin="1" end="${length }" step="1">
    	<a style="color:black" href="${i.address }">
            <div class="news">
                <p>${i.cn_title }</p>
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
        })

</script>
</html>