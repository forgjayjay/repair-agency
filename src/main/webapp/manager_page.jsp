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
        <form action="manager_order_handler" method="post"> 
            <input type="submit" name="showorder" value="show all orders"><input type="checkbox" name="desc" value="desc"/>Descending?<br/><br/>
            <input type="submit" name="appoint" value="appoint a craftsman to an order" /><br/><br/>
            <input type="submit" name="price" value="assign price to an order" /><br/><br/>
            <br/><br/><input type="submit" name="craftsman" value="add a craftsman" /><br/><br/>
            
        </form>
        <div class="topcorner"><h2><a href = "Logout">Logout</a></h2></div>
    </body>
</html>
