<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign Up</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/style.css">

    <style>
        body {
            background-color: #f4f4f4;
        }

        .form-group, .form-label {
            text-align: left;
            width: 100%;
            display: block;
            margin-top: 7px;
            margin-bottom: 2px;
        }

        .container {
            max-width: 600px;
            margin: 0 auto;
        }

        .signup-form {
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .signup-form h1 {
            font-size: 2rem;
            margin-bottom: 20px;
            text-align: center;
            font-weight: bold;
        }

        .form-control {
            height: 45px;
            font-size: 1rem;
        }

        .btn-dark {
            width: 100%;
            font-size: 1.1rem;
            padding: 12px;
            border-radius: 8px;
        }

        .text-muted {
            text-align: center;
            font-size: 0.9rem;
        }

        .text-muted a {
            text-decoration: none;
            color: #007bff;
        }

        .text-muted a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body class="bg-light">

    <section class="vh-100 d-flex justify-content-center align-items-center">
        <div class="container">
            <div class="signup-form">
                <h1>Sign Up</h1>
                <form method="POST" action="CheckLoginServlet">
                    <div class="form-group">
                        <label class="form-label" for="register-fname">First Name</label>
                        <input type="text" class="form-control" id="register-fname" name="firstName" placeholder="Enter your first name" required>
                    </div>
                    <div class="form-group">
                        <label class="form-label" for="register-lname">Last Name</label>
                        <input type="text" class="form-control" id="register-lname" name="lastName" placeholder="Enter your last name" required>
                    </div>
                    <div class="form-group">
                        <label class="form-label" for="register-email">Email Address</label>
                        <input type="email" class="form-control" id="register-email" name="email" placeholder="name@email.com" required>
                    </div>
                    <div class="form-group">
                        <label class="form-label" for="register-password">Password</label>
                        <input type="password" class="form-control" id="register-password" name="password" placeholder="Enter your password" required>
                    </div>
                    <div class="form-group">
                        <label class="form-label" for="register-password-again">Confirm Password</label>
                        <input type="password" class="form-control" id="register-password-again" name="password" placeholder="Confirm your password" required>
                    </div>
                    <button type="submit" class="btn btn-dark">Sign Up</button>
                </form>
                <p class="text-muted mt-3">
                    Already registered? <a href="login.jsp">Login here</a>.
                </p>
            </div>
        </div>
    </section>

    <!-- JavaScript to validate password match -->
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            const form = document.querySelector("form");
            const password = document.getElementById("register-password");
            const confirmPassword = document.getElementById("register-password-again");

            form.addEventListener("submit", function (event) {
                if (password.value !== confirmPassword.value) {
                    event.preventDefault();
                    alert("Passwords do not match. Please try again.");
                    confirmPassword.focus();
                }
            });
        });
    </script>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
