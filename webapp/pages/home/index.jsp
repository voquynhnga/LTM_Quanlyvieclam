<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Job Listings</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <style>
        #jobs .card {
            transition: transform 0.3s ease, box-shadow 0.3s ease;
            border: none;
            border-radius: 20px;
            overflow: hidden;
            cursor: pointer;
            position: relative;
            height: 350px;
        }

        #jobs .card .card-body {
            padding: 20px;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
            height: 100%;
        }

        #jobs .card:hover {
            transform: translateY(-10px);
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.2);
        }

        #upload-btn {
            position: absolute;
            top: 20px;
            right: 20px;
        }
    </style>
</head>
<body>
<section id="jobs" class="py-5 bg-light position-relative">
    <a href="Controller" id="upload-btn" class="btn btn-success">Upload</a>

    <div class="container">
        <div class="row justify-content-center">
            <% 
                List<Card> cardList = (List<Card>) request.getAttribute("CardList");
                if (cardList != null) {
                    for (Card card : cardList) {
            %>
            <div class="col-12 col-sm-6 col-md-4 col-lg-4 mb-4">
                <div class="card text-center shadow-sm h-100 position-relative">
                    <div class="card-body">
                        <h4 class="card-title mb-1"><%= card.getTitle() %></h4>
                        <p class="card-text text-muted mb-3"><%= card.getCompany() %></p>
                        <p class="card-text text-muted">Lương: <%= card.getSalary() %></p>
                        <p class="card-text">Mô tả công việc: <%= card.getDescription() %></p>
                        <p class="card-text">Địa điểm: <%= card.getLocation() %></p>
                        <p class="card-text">Hạn nộp: <%= card.getDeadline() %></p>
                    </div>
                    <div class="card-footer">
                        <button class="btn btn-primary">Xóa</button>
                    </div>
                </div>
            </div>
            <% 
                    }
                }
            %>
        </div>
    </div>
</section>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
