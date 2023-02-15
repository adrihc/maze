<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <title>Title</title>
    <style>
        @font-face {
            font-family: 'maze';
            src: url('img/mazefont.ttf');
        }

        #explanation {
            position: absolute;
            font-family: 'maze';
        }

        #mazeCanvas {
            position: relative;
            left: 25%;
            border: 1px solid black;
        }
        a{
            font-family: 'maze';
            text-decoration: none;
            color: black;
            position: absolute;
            top: 30px;
            left: 90%;
        }
    </style>

</head>

<body onload="intervalStopwatch">
<a href="/start">PLAY <br> AGAIN</a>
<div style="display: none">
    ${myjson}
</div>

<div id="explanation">
    <img src="img/tree.png"> = Puerta
    <br>
    <img src="img/wall.png"> = Pared
    <br>
    <img src="img/key.png"> = Llaves
    <br>
    <img src="img/coin.png"> = Moneda
    <br>
    <img src="img/keys.png" width="100" height="70" > = Para moverte <br> y coger objetos
    <br>
    <img src="img/mouse.png" width="60" height="40"> = Para moverte, <br>coger objetos y <br>abrir puertas

</div>

<!-- Les direccions(N,S,W,E) poden tenir com a instance ("door","wall" i "")  -->
<!-- minimumCoin sén les monedes necessaries per a obrir les portes -->
<!-- La id de la key ha de correspondre amb la id de la porta a obrir  -->
<!-- Una vegada que agafes la key s'activarà el "got" de idKeys que tengui la id de la clau  -->
<!-- Tant key.state com cada coin poden estar en true o false  -->
<!--   -->

<script id="myData" type="application/json">
    ${myjson}
</script>

<script>
    let dataScript = document.getElementById("myData").textContent;
    let data = JSON.parse(dataScript);
    console.log(data);
</script>

<form>
    <input type="hidden" name="dir" value="">
    <input type="hidden" name="coin" value="">
    <input type="hidden" name="stopwatch" value="">
</form>


<canvas id="mazeCanvas" width="600" height="600" onload="animacion()"></canvas>
<script>
    //Canvas
    var canvas = document.getElementById("mazeCanvas");
    var ctx = canvas.getContext("2d");

    //Arrows
    var up,down,left,right;
    up =  new Image();
    down = new Image();
    left = new Image();
    right = new Image();
    up.src = "img/up.png";
    down.src = "img/down.png";
    left.src = "img/left.png";
    right.src = "img/right.png"

    //Room
    var currentIdRoom = 0;

    //Base
    var base = new Image();
    base.src = "img/Base_resized.png"

    //set interval in a variable
    var interval = setInterval(callDraw,35);

    //Character
    var redMini = new Image();
    redMini.src = "img/red.png";
    redMini.onload = interval;
    var movementSpeed = 15;

    //Window
    var windowMaze = new Image();
    windowMaze.src = "img/Dialogue.png"

    //Character position
    var sy = 0;
    var sx = 0;
    positionX = 275,
        positionY = 275;

    //Wall
    var wall = new Image();
    wall.src = "img/wall.png"

    //Wall horizontal
    var hWall = new Image();
    hWall.src = "img/horizontal_wall.png";

    //Wall vertical
    var vWall = new Image();
    vWall.src = "img/vertical_wall.png";

    //Door
    var door = new Image();
    door.src = "img/tree.png";

    //Key
    var contadorKey = 0;
    var mazeKey = new Image();
    mazeKey.src = "img/key.png";

    //Coin
    var contadorCoin = 0;
    var coin = new Image();
    coin.src = "img/coin.png";

    //End window
    var endWindow = new Image();
    endWindow.src = "img/end.png";

    //Canvas onclick
    canvas.addEventListener('mousedown', function (e) {
        const rect = canvas.getBoundingClientRect()
        const x = event.clientX - rect.left
        const y = event.clientY - rect.top
        console.log(x);
        console.log(y);
        getKey(x,y);
        getCoin1(x, y);
        getCoin2(x,y);
        getCoin3(x,y);
        openDoorN(x,y);
        openDoorS(x,y);
        openDoorE(x,y);
        openDoorW(x,y);
        goUp(x,y);
        goRight(x,y);
        goLeft(x,y);
        goDown(x,y);
        reset(x,y);
    });

    document.addEventListener('keydown', function (event){
        if (data.end == "end" && event.key === 'Enter'){
            window.location.assign("/endform");
        }
    })
    function sleep(ms) {
        return new Promise(resolve => setTimeout(resolve, ms));
    }

    var coinsNeed = data.items.coinsNeeded;
    var coinsObtained = data.player.coins;
    //Coger llaves
    async function getKey(x, y) {
        console.log(coinsNeed);
        console.log(coinsObtained);
        if (x >= 182 && x <= 214 && y >= 364 && y <= 396 && data.items.key == "true") {
            ctx.font = "15px maze";
            ctx.fillStyle = "black";
            if (coinsObtained >= coinsNeed){
                ctx.fillText("+1 llave", positionX-32, positionY-32);
            } else {
                ctx.fillText("Te faltan monedas", positionX-100, positionY-32);
            }
            clearInterval(interval);
            await new Promise(r => setTimeout(r, 200));
            window.location.assign("/getkey");
        }
    }

    //Coger monedas
    async function getCoin1(x, y) {
        if (x >= 384 && x <= 428 && y >= 358 && y <= 396 && data.items.coin1 == "true") {
            ctx.font = "15px maze";
            ctx.fillStyle = "black";
            ctx.fillText("+1 moneda", positionX-32, positionY-32);
            clearInterval(interval);
            await new Promise(r => setTimeout(r, 200));
            window.location.assign("/getcoin?coin=1");
        }
    }
    async function getCoin2(x, y) {
        if (x >= 384 && x <= 428 && y >= 182 && y <= 214 && data.items.coin2 == "true") {
            ctx.font = "15px maze";
            ctx.fillStyle = "black";
            ctx.fillText("+1 moneda", positionX-32, positionY-32);
            clearInterval(interval);
            await new Promise(r => setTimeout(r, 200));
            window.location.assign("/getcoin?coin=2");
        }
    }
    async function getCoin3(x, y) {
        if (x >= 182 && x <= 214 && y >= 182 && y <= 214 && data.items.coin3 == "true") {
            ctx.font = "15px maze";
            ctx.fillStyle = "black";
            ctx.fillText("+1 moneda", positionX-32, positionY-32);
            clearInterval(interval);
            await new Promise(r => setTimeout(r, 200));
            window.location.assign("/getcoin?coin=3");
        }
    }

    //Funciones para abrir puertas
    function openDoorN(x,y){
        if (x >= 270 && x <= 334 && y >= 110 && y <= 142 && data.walls.N.door == "door") {
            window.location.assign("/open?dir=N");
        }
    }
    function openDoorS(x,y){
        if (x >= 270 && x <= 334 && y >= 462 && y <= 494 && data.walls.S.door == "door") {
            window.location.assign("/open?dir=S");
        }
    }
    function openDoorW(x,y){
        if (x >= 110 && x <= 142 && y >= 270 && y <= 304 && data.walls.W.door == "door") {
                window.location.assign("/open?dir=W");
        }
    }
    function openDoorE(x,y){
        if (x >= 462 && x <= 494 && y >= 270 && y <= 336 && data.walls.E.door == "door") {
                window.location.assign("/open?dir=E");
        }
    }

    //Cambiar habitación
    function goUp(x,y){
        if (x >= 284 && x <= 316 && y >= 140 && y <= 172 && data.walls.N == "" && !(data.end == "end")){
            window.location.assign("/nav?dir=N");
            data.player.coorX = 275;
            data.player.coorY = 275;
        }
    }
    function goDown(x,y){
        if(x >= 250 && x <= 316 && y >= 400 && y <= 460 && data.walls.S == "" && !(data.end == "end")){
            window.location.assign("/nav?dir=S");
            data.player.coorX = 275;
            data.player.coorY = 275;
        }
    }
    function goLeft(x,y){
        if(x >= 140 && x <= 172 && y >= 238 && y <= 332 && data.walls.W == "" && !(data.end == "end")) {
            window.location.assign("/nav?dir=W");
            data.player.coorX = 275;
            data.player.coorY = 275;
        }
    }
    function goRight(x,y){
        if(x >= 420 && x <= 470 && y >= 268 && y <= 332 && data.walls.E == "" && !(data.end == "end")){
            window.location.assign("/nav?dir=E")
            data.player.coorX = 275;
            data.player.coorY = 275;
        }
    }

    //Set end
    async function isEnd(){
        //Set end
        if (data.end == "end"){
            ctx.drawImage(endWindow, 0, 0, 400, 100, 100, 120, 400, 100);
            ctx.fillText("Has llegado al final.", 140, 160);
            ctx.fillText("Pulsa enter.",190, 192);
            await new Promise(r => setTimeout(r, 200));
            clearInterval(interval);
        }
    }

    //Reset
    function reset(x,y){
        if(x >= 480 && x <= 600 && y >= 0 && y <= 45){
            window.location.assign("/reset")
        }
    }

    //Dibujar en el canvas
    function callDraw() {
        if (sy > 96) {
            sy = 0
        }
        clear();

        //Draw base
        ctx.drawImage(base, 0, 0, 600, 600, 0, 0, 600, 600);

        //Draw walls
        //up-left
        ctx.drawImage(hWall, 0, 0, 96, 32, 108, 108, 96, 32)
        ctx.drawImage(hWall, 0, 0, 96, 32, 170, 108, 96, 32)
        ctx.drawImage(vWall, 0, 0, 32, 96, 108, 108, 32, 96)
        ctx.drawImage(vWall, 0, 0, 32, 96, 108, 170, 32, 96)

        //up-right
        ctx.drawImage(hWall, 0, 0, 96, 32, 332, 108, 96, 32)
        ctx.drawImage(hWall, 0, 0, 96, 32, 396, 108, 96, 32)
        ctx.drawImage(vWall, 0, 0, 32, 96, 460, 108, 32, 96)
        ctx.drawImage(vWall, 0, 0, 32, 96, 460, 172, 32, 96)

        //down-left
        ctx.drawImage(hWall, 0, 0, 96, 32, 108, 460, 96, 32)
        ctx.drawImage(hWall, 0, 0, 96, 32, 170, 460, 96, 32)
        ctx.drawImage(vWall, 0, 0, 32, 96, 108, 332, 32, 96)
        ctx.drawImage(vWall, 0, 0, 32, 96, 108, 364, 32, 96)

        //down-right
        ctx.drawImage(hWall, 0, 0, 96, 32, 332, 460, 96, 32)
        ctx.drawImage(hWall, 0, 0, 96, 32, 396, 460, 96, 32)
        ctx.drawImage(vWall, 0, 0, 32, 96, 460, 332, 32, 96)
        ctx.drawImage(vWall, 0, 0, 32, 96, 460, 364, 32, 96)

        //Door
        //Door north
        console.log(data.N)
        if (data.walls.N == "wall") {
            ctx.drawImage(wall, 0, 0, 32, 32, 300, 108, 32, 32)
            ctx.drawImage(wall, 0, 0, 32, 32, 268, 108, 32, 32)
        } else if (data.walls.N.door == "door") {
            ctx.drawImage(door, 0, 0, 32, 32, 300, 108, 32, 32)
            ctx.drawImage(door, 0, 0, 32, 32, 268, 108, 32, 32)
        } else {
            ctx.drawImage(up,0,0,32,32,284,140,32,32)
        }
        //Door east
        if (data.walls.E == "wall") {
            ctx.drawImage(wall, 0, 0, 32, 32, 460, 300, 32, 32)
            ctx.drawImage(wall, 0, 0, 32, 32, 460, 268, 32, 32)
        } else if (data.walls.E.door == "door") {
            ctx.drawImage(door, 0, 0, 32, 32, 460, 300, 32, 32)
            ctx.drawImage(door, 0, 0, 32, 32, 460, 268, 32, 32)
        }else {
            ctx.drawImage(right,0,0,32,32,426,284,32,32)
        }
        //Door south
        if (data.walls.S == "wall") {
            ctx.drawImage(wall, 0, 0, 32, 32, 300, 460, 32, 32)
            ctx.drawImage(wall, 0, 0, 32, 32, 268, 460, 32, 32)
        } else if (data.walls.S.door == "door") {
            ctx.drawImage(door, 0, 0, 32, 32, 300, 460, 32, 32)
            ctx.drawImage(door, 0, 0, 32, 32, 268, 460, 32, 32)
        }else {
            ctx.drawImage(down,0,0,32,32,284,428,32,32)
        }

        //Door west
        if (data.walls.W == "wall") {
            ctx.drawImage(wall, 0, 0, 32, 32, 108, 300, 32, 32)
            ctx.drawImage(wall, 0, 0, 32, 32, 108, 268, 32, 32)
        } else if (data.walls.W.door == "door") {
            ctx.drawImage(door, 0, 0, 32, 32, 108, 300, 32, 32)
            ctx.drawImage(door, 0, 0, 32, 32, 108, 268, 32, 32)
        } else {
            ctx.drawImage(left,0,0,32,32,140,284,32,32)
        }


        //Draw key
        if (data.items.key == "true") {
            ctx.drawImage(mazeKey, 0, 0, 32, 32, 182, 364, 32, 32)
        }


        //Draw coin
        if (data.items.coin1 == "true") {
            ctx.drawImage(coin, 0, 0, 32, 32, 396, 364, 32, 32)
        }

        if (data.items.coin2 == "true") {
            ctx.drawImage(coin, 0, 0, 32, 32, 396, 182, 32, 32)
        }
        if (data.items.coin3 == "true") {
            ctx.drawImage(coin, 0, 0, 32, 32, 182, 182, 32, 32)
        }



        //Draw character
        ctx.drawImage(redMini, sy, sx, 32, 32, positionX, positionY, 32, 32)

        //Draw window
        ctx.drawImage(windowMaze, 0, 0, 150, 190, 0, 0, 150, 190);
        ctx.font = "15px maze";
        ctx.fillStyle = "black";
        ctx.fillText("Llaves", 32, 30);
        ctx.fillText("Monedas", 25, 80);
        ctx.fillText("Habit.", 40, 137)
        ctx.fillText(data.player.keys, 70, 55);
        ctx.fillText(data.player.coins, 70, 105);
        ctx.fillText(data.player.room, 70, 165);
        ctx.fillText("Reset", 510, 32);
        isEnd();
    }


    //Move character
    document.addEventListener("keydown", function (event) {
        // "ArrowRight", "ArrowLeft", "ArrowUp", or "ArrowDown"

        if (event.key === 'ArrowUp') {
            if (sx != 96) {
                sx = 96;
            }
            if (sx == 96) {
                sy = sy + 32;
            }

            if(positionY+movementSpeed >= 162){
                positionY = positionY - movementSpeed;
            }

        } else if (event.key === 'ArrowDown') {
            if (sx != 0) {
                sx = 0;
            }
            if (sx == 0) {
                sy = sy + 32;
            }
            if(positionY+movementSpeed <= 432){
                positionY = positionY + movementSpeed;
            }

        } else if (event.key === 'ArrowLeft') {
            if (sx != 32) {
                sx = 32;
            }
            if (sx == 32) {
                sy = sy + 32;
            }
            if(positionX+movementSpeed >= 162){
                positionX = positionX - movementSpeed;
            }

        } else if (event.key === 'ArrowRight') {
            if (sx != 64) {
                sx = 64;
            }
            if (sx == 64) {
                sy = sy + 32;
            }
            if(positionX+movementSpeed <= 432){
                positionX = positionX + movementSpeed;
            }
        }

        //Entrar puertas con el personaje
        if(positionX >= 250 && positionX <= 316 && positionY >= 140 && positionY <= 172 && data.walls.N == "" ){
            window.location.assign("/nav?dir=N");
        } else if(positionX >= 420 && positionX <= 470 && positionY >= 268 && positionY <= 332 && data.walls.E == "" ){
            window.location.assign("/nav?dir=E");
        } else if(positionX >= 250 && positionX <= 316 && positionY >= 400 && positionY <= 460 && data.walls.S == "" ){
            window.location.assign("/nav?dir=S");
        } else if(positionX >= 140 && positionX <= 172 && positionY >= 238 && positionY <= 332 && data.walls.W == "" ){
            window.location.assign("/nav?dir=W");
        }

        //Coger objetos con el personaje
        getKey(positionX,positionY);
        getCoin1(positionX,positionY);
        getCoin2(positionX,positionY);
        getCoin3(positionX,positionY);

    });

    //Clear canvas
    function clear() {
        ctx.clearRect(0, 0, canvas.getAttribute('width'), canvas.getAttribute('height'));
    }

</script>

</body>

</html>