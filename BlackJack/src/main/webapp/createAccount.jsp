<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	body {
		background-color: lightgrey; 
		font-family: Arial, sans-serif;
		font-size: 20px;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        margin: 0;
    }
    /*set attributes for the entire body (attribtues are pretty straightforward)*/
    

    .create_box {
    	background-color: lightgrey;
        padding: 40px;
        border:5px solid black;
        border-radius: 8px;
        box-shadow: 0 4px 8px red;
        text-align: center;
        box-shadow: 0 10px 10px "red";
        border-radius: 20px;
    }
    /*set attributes for the create box class*/
    
    
    .forms{
    	text-align: center;
    }
    /*set attributes for the forms class*/
    
    .input1{
    	margin:30px;
    	display:"flex";
    	flex-direction:column;	
    }
    .input1 input{
    	width:"300px";
    	font-size:20px;
    	border:2px solid black;
    	border-radius: 10px;
    }
    
     .good-message {
            color: green;
            margin-top: 20px;
            font-size: 18px;
            display: none;
            /*not showing the message*/
            
    }
    /*set attributes for different classes*/



</style>
<script>
	
</script>
</head>
<body>
<div>
			
			<div class ="create_box">
			
				<h1>Account Creation Page</h1>

				<form action="create" class="forms">
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
                           placeholder="<%= fieldPlaceholder %>">
                </div>
            <% } %>

            <button type="submit" class="submit_button">Create Account</button>

            <div id="good-message" class="good-message">
                You have successfully created an account!
            </div>
        </form>
			</div>	
		
		</div>
</body>
</html>




























