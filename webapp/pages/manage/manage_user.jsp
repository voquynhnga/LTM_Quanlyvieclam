<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Model.BEAN.User, java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Management</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h2 class="mb-4">User Management</h2>

    <div class="input-group mb-4">
        <input type="text" class="form-control" id="searchInput" placeholder="Search users...">
        <button class="btn btn-primary" type="button" onclick="filterUsers()">Search</button>
    </div>

    <table class="table table-striped" id="userTable">
        <thead>
            <tr>
            	<th>UserName</th>
                <th>Name</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
    <tr>
        <td>${user.username}</td>
        <td>${user.firstname} ${user.lastname}</td>
        <td>${user.email}</td>
        <td>${user.phoneNumber}</td>
        <td>
            <form action="UserController" method="post">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="id" value="${user.userId}">
                <button type="submit" class="btn btn-danger">Delete</button>
            </form>
        </td>
    </tr>
        
        </tbody>
    </table>
</div>

<script>

    function filterUsers() {
        const input = document.getElementById('searchInput').value.toLowerCase();
        const rows = document.querySelectorAll('#userTable tbody tr');

        rows.forEach(row => {
            const username = row.children[0].textContent.toLowerCase();
            const email = row.children[1].textContent.toLowerCase();
            const phone = row.children[2].textContent.toLowerCase();
            const matches = username.includes(input) || email.includes(input) || phone.includes(input);
            row.style.display = matches ? '' : 'none';
        });
    }
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
