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
        <h2></h2>
        <form action="craftsman_order_handler" method="post"> 
            <input type="submit" name="show" value="show assigned orders" /><br/><br/>
        </form>
        <div class="topcorner"><h2><a href = "Logout">Logout</a></h2></div><br />
    </body>
</html>
