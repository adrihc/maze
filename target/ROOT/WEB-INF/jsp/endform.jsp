<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        @font-face {
            font-family: 'maze';
            src: url('img/mazefont.ttf');
        }
        body{
            font-family: 'maze';
        }
        div{
            position: relative;
            left: 33%;
            top: 150px;
        }
        input{
            position: relative;
            left: 40px;
        }
        button{
            position: relative;
            left: 50px;
        }
        h2{
            position: relative;
            left: 10px;
        }
    </style>
</head>
<body>
<div>
    <h2>Escribe tu nombre</h2>
    <form method="post" action="/endform">
        <input type="text" name="user">
        <button>Submit</button>
    </form>
    <p>Has tardado ${hores} horas, ${minutes} minutos y <br> ${seconds} segundos(${result} ms)</p>
</div>

</body>
</html>
