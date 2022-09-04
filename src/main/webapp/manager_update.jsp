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
            input[type=text]{
                display: inline-block;
                width: 100%;
                padding: 10px;
                box-sizing: border-box;
                border-radius: 4px;
                border: .5px solid;
                margin: 10px 0;
                
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
        <div class="form"><form action="intermission" method="post"> 
            Login:<input type="text" name="username" required/><br/><br/>  
            <input type="submit" name="manager" value="Update"/>   
        </form>
        </div>
    </body>
</html>
