<html>
    <head>
        <title>Account page</title>
    </head>
    <body>
        <h2></h2>
        <form action="craftsman_order_handler" method="post"> 
            <input type="hidden" name="orderID" value="${orderID}" /><br/><br/>
            <input type="submit" name="progress" value="order in progress" /><br/><br/>
            <input type="submit" name="finished" value="order finished" /><br/><br/>
        </form>
    </body>
</html>
