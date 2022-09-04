
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
            select{
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
        </div>
    </body>
</html>
