<html>
    <head>
      <title>Registration Page</title>
      <style type="text/css">
        body{
            background-image: url(https://telair.com/wp-content/uploads/2020/02/telair-loading-without-compromise-repair-service.jpg);
            left: 40%;
            text-align: center;
        }
        h1{
            text-align: center;
        }
        .register{
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
      <div class="register"><h1>Register new account</h1>
      <form action="Registration" method="post"> 
        Login:<br/><input type="text" name="username" required/><br/><br/>  
        Password:<br/><input type="text" name="userpass" required/><br/><br/>  
        Repeat password:<br/><input type="text" name="userpass2" required/><br/><br/> 
        <input type="submit" value="Register" required/>   
      </form>
      <a href="Login">Login into an existing account</a></div>
    </body>
</html>