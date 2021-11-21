<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome to Cinema</title>
</head>
<body>
<b>Hello ${userName} <br><br></b>
    <b>You will need the API tool for the next steps <br><br></b>

    <b>Open API testing tool, for example you can use the Postman.<br><br></b>

 <p><ins>You are added with USER role.</ins><br><ul></p>
        <li> GET - /cinema-halls, <br>
                   /movies, <br>
                   /movie-sessions/available, <br>
                   /movie-sessions/id, <br>
                   /orders, <br>
                   /shopping-carts/by-user <br> </li>
        <li>POST - /register, <br>
                   /login, <br>
                   /orders/complete <br> </li>
        <li>PUT - /shopping-carts/movie-sessions?movieSessionId <br><br></li></ul>

<p><ins>Example for body params for POST and PUT requests:</ins> <br></p><ul>
        <li>POST /register: <br></li>
    {"password" : "12345678", "repeatPassword" : "12345678", "email" : "email@gmail.com"} <br>
        <li> POST /cinema-halls: <br></li>
    {"capacity" : 100, "description" : "description"} <br>
        <li> POST /movies: <br></li>
    {"title" : "title", "description" : "description"} <br>
        <li>POST /movie-sessions: <br> </li>
    {"movieId" : 1, "cinemaHallId" : 1, "showTime" : "19.11.2021"} <br></ul>

</body>
</html>
