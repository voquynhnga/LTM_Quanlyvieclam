<%--
  Created by IntelliJ IDEA.
  User: ngangungo
  Date: 11/3/2024
  Time: 3:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>


<style>
    body {
        height: 100vh;
        /* overflow-y: scroll;  */
        overflow-x: hidden;
        margin: 0;
        padding: 10px;
        box-sizing: border-box;
    }

    .sidebar {
        height: 100vh;
        background-color: #f1f1f1;
        padding-top: 20px;
    }

    .message-box {
        border: 1px solid #ddd;
        border-radius: 8px;
        padding: 10px;
        margin-bottom: 10px;
        background-color: #fff;
    }

    .message-header {
        font-weight: bold;
    }

    .custom-toolbar {
        border-bottom: 1px solid #ddd;
        background-color: #fff;
        padding: 10px 15px;
    }

    .toolbar-tabs {
        margin-left: 10px;
    }

    iframe {
        border: none;
        height: 80vh;
        width: 100%;
    }
</style>
<body>
<div class="col-md-9 col-lg-10">
    <!-- Post Section -->
    <div class="container">
        <!-- Message 1 -->
        <div class="message-box">
            <div class="message-header d-flex justify-content-between">
                <span>Võ Văn Tâm - 1/6/23 11:25</span>
                <span><a href="#">Le Tran Duc - Khoa CNTT</a></span>
            </div>
            <div class="message-body">
                <p>Scheduled a meeting</p>
                <div class="d-flex justify-content-between">
                    <span>Thứ Hai, 9 tháng 1, 2023 20:30</span>
                    <button class="btn btn-primary btn-sm">Tham gia</button>
                </div>
            </div>
        </div>

        <!-- Message 2 -->
        <div class="message-box">
            <div class="message-header">
                <span>Đồng Hoàng Anh - 9/1/23 21:51</span>
            </div>
            <div class="message-body">
                <p>gdctc 2 chưa đăng kí được thì năm 2 mới được đăng kí hả thầy?</p>
                <span>Cuộc họp đã kết thúc: 52 giây</span>
            </div>
        </div>

        <!-- Reply Section -->
        <div class="mb-3">
            <button class="btn btn-primary">Bắt đầu một bài đăng</button>
        </div>
    </div>
</div>

</body>
</html>
