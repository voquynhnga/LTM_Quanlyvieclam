<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Bài thực hành LTM</title>
	<link rel="stylesheet" href="assets/css/bootstrap.min.css">
	<link rel="stylesheet" href="assets/css/style.css?v=1.2">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper/swiper-bundle.min.css">

	<style>
		body, html {
			min-height: 100%;
			display: flex;
			flex-direction: column;
		}

		main {
			flex-grow: 1;
			padding-bottom: 10px;
			min-height: 100vh;
			padding-top: 80px;
			padding-bottom: 50px;
		}

	</style>
</head>

<body>
<!-- Include the header -->
<%@ include file="../partials/header.jsp" %>

<!-- Main content area -->
<main>
<%@ include file="../pages/home/index.jsp" %>
</main>

<!-- Include the footer -->
<%@ include file="../partials/footer.jsp" %>

</body>
</html>
