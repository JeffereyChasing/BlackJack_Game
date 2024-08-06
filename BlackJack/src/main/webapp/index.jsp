<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>BlackJack Game Login</title>
    <style>
        body {
            background-color: lightgrey;
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .welcome-box {
            background-color: lightgrey;
            padding: 40px;
            border-radius: 8px;
            box-shadow: 0 4px 8px red;
            text-align: center;
        }

        .welcome-box h1 {
            font-size: 24px;
        }

        .inputbox {
            margin: 10px;
            width: 200px;
            height: 20px;
            border-radius: 8px;
        }

        .submit_button {
            margin: 20px;
            width: 150px;
            border-radius: 15px;
        }
        /*set attributes for different classes*/
        
    </style>
</head>
<body>
    <div>
        <div class="welcome-box">
            <h1>Welcome to the BlackJack Game</h1>
            <p>You will beat the dealer</p>
            <form action="login" method="post">
            
              <%
            	//I use a for-loop to loop through the two field and their accordign attributes
                String[][] fields = {
                    {"username", "Username", "Enter your username"},
                    {"passcode", "Password", "Enter your password"}
                };

                //generate form inputs dynamically
                for (int i = 0; i < fields.length; i++) {
                    String fieldId = fields[i][0];
                    String fieldLabel = fields[i][1];
                    String fieldPlaceholder = fields[i][2];
                
            	%>
            
            
                  <div class="input1">
                    <label for="<%= fieldId %>"><%= fieldLabel %>:</label>
                    <input required type="text" id="<%= fieldId %>" name="<%= fieldId %>"
                         class = "inputbox"  placeholder="<%= fieldPlaceholder %>">
                </div>
           			 <% } %>
            
          
                
                
                
                
                
                
                <button type="submit" class="submit_button">Login into Account</button>
            </form>
            <a href="createAccount.jsp">Create Account</a>
        </div>
    </div>
</body>
</html>
