<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="model.UserModel" %>

<html>

<head>
	<link rel="stylesheet"
		href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
	<link rel="stylesheet"
		href="https://fonts.googleapis.com/css?family=Bitter:400,700">
	<link rel="stylesheet" href="assets/css/header.css?v=1.3">

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
		<!-- 		<a class="navbar-brand d-flex align-items-center" href="layouts/decorator.jsp">
					<img src="assets/img/logo.png" alt="Trang chủ"  height="80" width="80" class = "m-0">
				</a> -->
				<button class="navbar-toggler ml-auto" type="button" data-toggle="collapse" data-target="#navcol-1">
					<span class="sr-only">Toggle navigation</span>
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navcol-1">
					<c:if test="${user != null}">
						<ul class="nav navbar-nav">
							<li class="nav-item" role="presentation">
								<a class="nav-link" href=${user.roleID == 3 ? "/class" : "/teacher/class"}>Lớp học
									<div class="slider"></div>
								</a>
							</li>
							<c:if test="${user.roleID == 3}">
                                <li class="nav-item" role="presentation">
                                    <%--<a class="nav-link" href="/assignments">Bài tập
                                        <div class="slider"></div>
                                    </a>--%>
                                </li>
							</c:if>
						</ul>
                    </c:if>
					<form class="form-inline mr-auto" target="_self">
					
							<div class="form-group">
								<label for="search-field"><i class="fa fa-search"></i></label><input
									class="form-control search-field" type="search" name="search"
									id="search-field">
							</div>
					</form>

					<c:choose>
						<c:when test="${user == null}">
							<span class="navbar-text">
								  <a class="btn action-button login" role="button" href="pages/user/login.jsp">Log In
									<div class="slider"></div>
								  </a>
									<a class="btn action-button signup" role="button" href="pages/user/signup.jsp">Sign Up
									<div class="slider"></div>
									</a>
							</span>

						</c:when>
				
					</c:choose>

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

<%
    String userID = String.valueOf(request.getAttribute("userID"));
    String roleID = String.valueOf(request.getAttribute("roleID"));
%>

<script>
    const userID = "<%= userID %>";
    const roleID = "<%= roleID %>";
</script>


</body>
</html>