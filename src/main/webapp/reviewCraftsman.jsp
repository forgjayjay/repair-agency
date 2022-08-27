
<html>
    <head>
        <title>Account page</title>
    </head>
    <body>
        <form action="user_order_handler" method="post"> 
            <input type="hidden" name="id" value="${orderID}"/>
            <label for="rating">Choose a car:</label>
            <select name="rating" id="craftsman" required>
                <option value="1">Very Poor</option>
                <option value="2">Poor</option>
                <option value="3">Average</option>
                <option value="4">Good</option>
                <option value="5">Very Good</option>
            </select>
            <input type="submit" name="review" value="leave a review"/><br/><br/>
        </form>
    </body>
</html>
