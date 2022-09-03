<html>
    <head>
        <title>Account page</title>
        <style type="text/css">
            .topcorner{
                position:absolute;
                top:10;
                right:10;
            }
            body{
                background-image: url(https://telair.com/wp-content/uploads/2020/02/telair-loading-without-compromise-repair-service.jpg);
                left: 40%;
                text-align: center;
            }
            h1{
                text-align: center;
            }
            .login{
                margin: auto;
                background: white;
                max-width: 350px;
                padding: 10px;
                border-radius: 4px;
            }
            input[type=submit]{
                width: 100%;
                padding: 10px;
                margin: 10px 0;
                border-radius: 4px;
                border: none;
                background-color: orange;
                font-size: 20;
                cursor: pointer;
            }
        </style>
    </head>
    <body>
        
        <form action="placeholder" method="post"> 
            Is new user?:<input type="checkbox" name="newuser" value="newuser"/><br/><br/>
            <input type="submit" name="manager" value="add a manager" /><br/><br/>
            <input type="submit" name="craftsman" value="add a craftsman" />
        </form>
        <div class="topcorner"><h2><a href = "Logout">Logout</a></h2></div>
    </body>
</html>
