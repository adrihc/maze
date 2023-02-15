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
        h1{
            position: relative;
            left: 39%;
            top: 100px;
        }
        div{
            position: relative;
            top: 150px;
            left: 40%;
        }
        img{
            position: absolute;
            left: 36%;
            top: 200px;
        }

    </style>
</head>
<body>
    <h1>MAZE GAME</h1>

    <img src="img/end.png">
    <div>
    <h3>Elige un mapa</h3>
    <form method="post" action="/start" target="/nav">
        <select name="joc">
            <option value="1">Maze 1</option>
            <option value="2">Maze 2</option>
            <option value="3">Maze 3</option>
        </select>
        <input type="submit" value="PLAY">
    </form>
</div>

</script>
</body>
</html>
