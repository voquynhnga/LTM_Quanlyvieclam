<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Model.BEAN.User, java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Management</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>

<body>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="/views/clients/assets/css/class_member.css">

<div class="container mt-5">

    <div class="input-group mb-4">
        <input type="text" class="form-control" id="searchInput" placeholder="Search users...">
        <button class="btn btn-primary" type="button" onclick="filterUsers()">Search</button>
    </div>

    <table class="table table-striped" id="userTable">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
        <%
            List<User> UserArray = (List<User>) session.getAttribute("UserArray");
            if (UserArray != null) {
                for (User user : UserArray) {
        %>
            <tr>
                <td><%= user.getUserId() %></td>
                <td><%= user.getLastname()+ " " + user.getFirstname() %></td>
                <td><%= user.getEmail() %></td>
                <td><%= user.getPhoneNumber() %></td>
             <td>
			   <form action="/LTM_BTH/UserController?action=delete" method="post" onsubmit="return confirmDelete();">
				    <input type="hidden" name="id" value="<%= user.getUserId() %>">
				    <button type="submit" class="fas fa-trash"></button>
				</form>

			</td>

            </tr>
        <%
                }
            } else {
        %>
            <tr>
                <td colspan="5" class="text-center">No users found.</td>
            </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>

<script>
    function filterUsers() {
        const input = document.getElementById('searchInput').value.toLowerCase();
        const rows = document.querySelectorAll('#userTable tbody tr');

        rows.forEach(row => {
            const columns = row.querySelectorAll('td');
            const matches = Array.from(columns).some(column => 
                column.textContent.toLowerCase().includes(input)
            );
            row.style.display = matches ? '' : 'none';
        });
    }
    
    function confirmDelete() {
        return confirm("Are you sure you want to delete this user?");
    }

</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
