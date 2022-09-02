<html>
    <head>
        <title>Account page</title>
        <style type="text/css">
            .topcorner{
                position:absolute;
                top:10;
                right:10;
            }
        </style>
    </head>
    <body>
        <h2></h2>
        <form action="user_order_handler" method="post"> 
            <input type="submit" name="neworder" value="place new order" /><br/><br/>
            <input type="submit" name="showorder" value="show my orders" /><br/><br/>
            <input type="submit" name="showorder_payment" value="show not paid orders" /><br/><br/>
        </form>
        <div class="topcorner"><h2><a href = "Logout">Logout</a></h2></div><br />
    </body>
</html>
