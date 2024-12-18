<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Model.UserModel" %>

<html>

<head>
	<link rel="stylesheet"
		href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Bitter:400,700">
	<link rel="stylesheet" href="assets/css/header.css?v=1.3">
	<link rel="stylesheet" href="assets/css/notification.css?v=1.1">
	

	<style>
		.header-dark .navbar-nav .nav-item:hover{
			background: none;
		}
		.header-dark .navbar-nav .nav-link:hover{
			color:white;
		}
	</style>
</head>


<body>
	<div class="header-dark">
		<nav class="navbar navbar-dark navbar-expand-md navigation-clean-search">
			<div class="container d-flex align-items-center">
				<button class="navbar-toggler ml-auto" type="button" data-toggle="collapse" data-target="#navcol-1">
					<span class="sr-only">Toggle navigation</span>
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navcol-1">
				
	              <% 
                        Integer role = (Integer) session.getAttribute("Role");
                        if (role != null && role == 1) { 
                    %>
                        <ul class="nav navbar-nav">
                            <li class="nav-item" role="presentation">
                                <a class="nav-link"> Người dùng
                                    <div class="slider"></div>
                                </a>
                            </li>
                        </ul>
                    <% } %>
					<form class="form-inline mr-auto" method="POST" action="/LTM_BTH/Controller" action = "search">
					    <div class="form-group">
					        <label for="search-field"><i class="fa fa-search"></i></label>
					        <input class="form-control search-field" type="search" name="searchKeyword" id="search-field">
					    </div>
					
					    <div class="form-group ml-2">
					        <select name="searchType" id="search-option" class="form-control">
					            <option value="title">Title</option>
					            <option value="company">Company</option>
					            <option value="location">Location</option>
					        </select>
					    </div>
					
					    <button type="submit" class="btn btn-primary ml-2">Tìm kiếm</button>
					</form>

					
				<div class='notification-container'>
         
                                <span class="dropdown">
                                    <a class="nav-link" data-toggle="dropdown" aria-expanded="false" href="#"><img
                                        src="assets/img/user.png" alt="User"
                                        height="35px" width="35px">
                                    </a>
                                    <div class="dropdown-menu" role="menu" style="left: 50%; transform: translateX(-40%);">
                                        <a class="dropdown-item" role="presentation" href="pages/user/account.jsp">Tài khoản</a>
                                        <a class="dropdown-item" role="presentation" href="pages/user/login.jsp">Đăng xuất</a>
                                    </div>
                                </span>
						    </div>

				</div>
			</div>
		</nav>
	</div>
	<button style="display: none;" id="volume-button"></button>


<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/js/bootstrap.bundle.min.js"></script>
	<script>
		document.addEventListener("scroll", function() {
			const header = document.querySelector(".header-dark");
			if (window.scrollY > 50) {
				header.classList.add("transparent");
			} else {
				header.classList.remove("transparent");
			}
		});
	</script>
	
	<script>
    document.getElementById("search-field").addEventListener("input", function() {
        var searchQuery = document.getElementById("search-field").value;
        var searchOption = document.getElementById("search-option").value;

        fetch('/Controller?search=' + searchQuery + '&searchOption=' + searchOption)
            .then(response => response.json())
            .then(data => {
                console.log(data);
            });
    });
</script>
	



</body>
</html>