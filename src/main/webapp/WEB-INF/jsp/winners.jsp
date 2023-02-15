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
      top: 150px;
      left: 25%;
    }
    table{
      position: relative;
      border-collapse: collapse;
      left: 50px;
    }
    th, td {
      border-bottom: 2px solid;
      padding-top: 10px;
      padding-bottom: 20px;
      padding-left: 30px;
      padding-right: 40px;
    }
    a{
      text-decoration: none;
      color: black;
      position: absolute;
      top: 30px;
      left: 90%;
    }
  </style>
</head>
<body>
<a href="/start">PLAY <br> AGAIN</a>
<div>
  <h1>TABLA DE PUNTUACIONES</h1>
  <table>
    <tr>
      <th>ID</th>
      <th>Usuario</th>
      <th>Mapa</th>
      <th>Tiempo</th>
    </tr>
    <c:forEach items="${winners}" var="contador">
      <tr>
        <td>${contador.id}</td>
        <td>${contador.name}</td>
        <td>${contador.mapid}</td>
        <td>${contador.time}</td>
      </tr>
    </c:forEach>
  </table>
</div>

</body>
</html>
