<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Model.BEAN.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Account Settings</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
  <style>
    * {
      margin: 0;
      padding: 0;
      box-sizing: border-box;
    }

    body {
      font-family: Arial, sans-serif;
      background-color: #f5f5f5;
      color: #333;
      display: flex;
      justify-content: center;
      align-items: center;
      padding: 20px;
      height: 100vh;
      overflow-y: auto;
    }

    .container {
      display: flex;
      width: 100%;
      max-width: 1000px;
      background: #fff;
      box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
      overflow: hidden;
    }

    .account-img {
      flex: 1;
      background-size: cover;
      background-position: center;
      min-height: 400px;
    }

    .account-container {
      flex: 2;
      padding: 40px;
      display: flex;
      flex-direction: column;
      justify-content: center;
    }

    h2 {
      font-size: 2em;
      color: #333;
      margin-bottom: 20px;
      text-align: center;
    }

    .account-info {
      display: flex;
      flex-direction: column;
      gap: 15px;
      text-align: left;
    }

    .account-info label {
      font-weight: bold;
      color: #444;
    }

    .account-info input[type="text"],
    .account-info input[type="email"],
    .account-info input[type="password"] {
      width: 100%;
      padding: 12px;
      border: 1px solid #ccc;
      border-radius: 8px;
      font-size: 1em;
    }

    .account-info input:focus {
      border-color: #333;
      outline: none;
    }

    .button-save {
      background-color: #333;
      color: #fff;
      padding: 12px;
      border: none;
      border-radius: 8px;
      font-size: 1.1em;
      cursor: pointer;
      margin-top: 20px;
      transition: background-color 0.3s;
      width: 100%;
    }

    .button-save:hover {
      background-color: #555;
    }

    .error-message {
      color: red;
      font-size: 0.9em;
      display: none;
    }
  </style>
</head>
<body>
  <div class="container">
    <div class="account-img" style="background-image: url('<%= request.getContextPath() %>/assets/img/account_background.jpg');"></div>

    <div class="account-container">
      <h2>Account Settings</h2>
      <form action="/LTM_BTH/ProfileController?action=update" method="post">
        <% User user = (User) session.getAttribute("user"); %>
        <div class="account-info">
          <label for="firstName">First Name</label>
          <input type="text" id="firstName" name="firstName" value="<%= user.getFirstname() %>" required>

          <label for="lastName">Last Name</label>
          <input type="text" id="lastName" name="lastName" value="<%= user.getLastname() %>" required>

          <label for="email">Email</label>
          <input type="email" id="email" name="email" value="<%= user.getEmail() %>" required>

          <label for="phoneNumber">Phone Number</label>
          <input type="text" id="phoneNumber" name="phoneNumber" value="<%= user.getPhoneNumber() %>" required>

          <label for="password">Password</label>
          <input type="password" id="password" name="password" value="<%= user.getPassword() %>" required>

          <label for="confirmPassword">Confirm Password</label>
          <input type="password" id="confirmPassword" name="confirmPassword" required>
          <div class="error-message" id="passwordError">Passwords do not match!</div>
        </div>

        <button type="submit" class="button-save" onclick="return validatePasswords()">Save</button>
      </form>
    </div>
  </div>

  <script>
    function validatePasswords() {
      var password = document.getElementById("password").value;
      var confirmPassword = document.getElementById("confirmPassword").value;

      if (password !== confirmPassword) {
        document.getElementById("passwordError").style.display = "block";
        return false; 
      } else {
        document.getElementById("passwordError").style.display = "none";
        return true; 
      }
    }
  </script>
</body>
</html>

