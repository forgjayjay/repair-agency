<html>
    <head>
        <title>Login Page</title>
        <style type="text/css">
            .center{
                position:absolute;
                left: 40%;
                text-align: center;
            }
        </style>
    </head>
    <body>
        
        <h2 style="font-family: Monaco;">Login into existing account</h2>
       
        <form style=" width: 50vw; margin-left : 40vw; margin-top: 8vw;" action="Login" method="post"> 
            Login:<br/><input type="text" name="username" required/><br/><br/>  
            Password:<br/><input type="text" name="userpass" required/><br/><br/>  
            <input type="submit" value="Login"/>   
        </form>
        <div class="center"><a href="Registration">Register new account</a></div>
        
        
    </body>
</html>
