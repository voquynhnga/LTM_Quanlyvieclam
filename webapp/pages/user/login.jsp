<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Link to Custom CSS -->
    <link rel="stylesheet" href="assets/css/style.css">
 
</head>

<body class="mt-0 overflow-hidden vh-100 d-flex justify-content-center align-items-center p-4">
 
    <div class="col col-md-8 col-lg-6 col-xxl-5">

        <div class="shadow-xl p-4 p-lg-5 bg-white">
            <h1 class="text-center fw-bold mb-5 fs-2">Login</h1>            
            <form action="/LTM_BTH/CheckLoginServlet" method="POST"> 
                <div class="form-group">
                    <label class="form-label" for="login-email">Username or email</label>
                    <input type="text" class="form-control" id="login-email" name="username" placeholder="Enter username or email" required>
                </div>
                <div class="form-group">
                    <label for="login-password" class="form-label d-flex justify-content-between align-items-center">
                        Password
                        <a href="forgotten_password.jsp" class="text-muted small">Forgot your password?</a>
                    </label>
                    <input type="password" class="form-control" id="login-password" name="password" placeholder="Enter your password"
                        required>
                </div>
                <button type="submit" class="btn btn-dark d-block w-100 my-4">Login</button>
            </form>
            <a class="text-muted" href="signup.jsp">Sign up</a>
        </div>
    </div>
</body>

</html>
