
<html>
    <head>
        <title>Account page</title>
    </head>
    <body>
        ${orderID}
        <form action="user_order_handler" method="post"> 
            <input type="hidden" name="id" value="${orderID}"/>
            <input type="hidden" name="cost" value="${orderID.getCost()}"/>
            <input type="submit" name="paid" value="Pay for order ${orderID}"/>   
        </form>
    </body>
</html>
