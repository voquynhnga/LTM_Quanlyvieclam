<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Model.BEAN.Job" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import = "java.util.Date" %>
<%@ page import = "java.util.concurrent.TimeUnit" %>

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

     #upload-btn-container {
            display: flex;
            align-items: center;
            justify-content: flex-start; 
            position: absolute;
            top: 20px;
            right: 20px;
            background-color: green;
            border-radius: 10px;
            padding: 5px;
            cursor: pointer;
        }
        
        #upload-text {
            color: white;
        }

        #upload-img {
            margin-left: 10px;
            width: 36px; 
            height: 36px;
        }

    </style>
</head>
<body>
<section id="jobs" class="py-5 bg-light position-relative">
    <div id="upload-btn-container" data-bs-toggle="modal" data-bs-target="#uploadModal">
        <a id="upload-text"> Upload </a>
        <img id="upload-img" src="assets/img/upload.png" alt="Upload Image">
    </div>

    <div class="container">
        <div class="row justify-content-center">
            <% 
                ArrayList<Job> JobList = (ArrayList<Job>) session.getAttribute("JobArray");
                if (JobList != null) {
                    for (Job job : JobList) {
                        Date postedAt = job.getPostedAt();  
                        Date currentDate = new Date();
                        long diffInMillis = currentDate.getTime() - postedAt.getTime();
                        long daysAgo = TimeUnit.MILLISECONDS.toDays(diffInMillis);
            %>
            <div class="col-12 col-sm-6 col-md-4 col-lg-4 mb-4">
                <div class="card text-center shadow-sm h-100 position-relative">
                    <div class="card-body">
                        <h4 class="card-title mb-1"><%= job.getTitle() %></h4>
                        <p class="card-text text-muted mb-3"><%= job.getCompanyName() %></p>
                        <p class="card-text"><%= job.getLocation() %></p>
                        <p class="card-text"><%= job.getDeadline() %></p>
                        <p class="card-text"> Đăng <%= daysAgo %> ngày trước </p>
                    </div>
                    <div class="card-footer">
                        <button class="btn btn-primary" href = "/LTM_BTH/Controller" name = "delete">Xóa</button>
                         <button class="btn btn-primary" href = "/LTM_BTH/Controller" name = "update">Chỉnh sửa</button>
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

<!-- Modal -->
<div class="modal fade" id="uploadModal" tabindex="-1" aria-labelledby="uploadModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="uploadModalLabel">Upload New Job</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form action="/LTM_BTH/Controller" method="POST" name = "upload">
            <div class="mb-3">
                <label for="jobTitle" class="form-label">Job Title</label>
                <input type="text" class="form-control" id="jobTitle" name="title" required>
            </div>

             <div class="mb-3">
                <label for="jobCompany" class="form-label">Company</label>
                <input type="text" class="form-control" id="jobCompany" name="company" required>
            </div>
            <div class="mb-3">
                <label for="jobDescription" class="form-label">Job Description</label>
                <textarea class="form-control" id="jobDescription" name="description" rows="4" required></textarea>
            </div>
          <div class="mb-3">
			    <label for="jobSalary" class="form-label">Salary</label>
			    <select class="form-control" id="salarySelect" name="salary" required onchange="toggleSalaryInput()">
			        <option value="Thỏa thuận">Thỏa thuận</option>
			        <option value="1">5.000.000 - 10.000.000</option>
			        <option value="2">10.000.000 - 20.000.000</option>
			        <option value="3">20.000.000 - 30.000.000</option>
			        <option value="4">50.000.000 +</option>
			        <option value="Other">Khác </option>
			    </select>
			</div>
            
            <div class="mb-3">
                <label for="jobLocation" class="form-label">Location</label>
                <input type="text" class="form-control" id="jobLocation" name="location" required>
            </div>
            <div class="mb-3">
                <label for="jobDeadline" class="form-label">Deadline</label>
                <input type="date" class="form-control" id="jobDeadline" name="deadline" required>
            </div>
        
            <button type="submit" class="btn btn-primary">Upload</button>
        </form>
      </div>
    </div>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
