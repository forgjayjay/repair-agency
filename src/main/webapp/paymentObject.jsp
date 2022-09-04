
<html>
    <head>
        <title>Account page</title>
        <style type="text/css">
            body{
                left: 40%;
                text-align: center;
            }
            h1{
                text-align: center;
            }
            .form{
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
        <div class="form"><form action="user_order_handler" method="post"> 
            <input type="hidden" name="id" value="${orderID}"/>
            <input type="hidden" name="cost" value="${orderID.getCost()}"/>
            <input type="submit" name="paid" value="Pay for order ${orderID}"/>   
        </form>
        </div>
    </body>
</html>
