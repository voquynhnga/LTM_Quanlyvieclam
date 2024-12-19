<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Model.BEAN.Job" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import = "java.util.Date" %>
<%@ page import = "java.util.concurrent.TimeUnit" %>

<!DOCTYPE html>
<html>
<head>
    <title>Job Listings</title>
<link rel="stylesheet"
		href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Bitter:400,700">   

</head>
<body>
 <link rel="stylesheet" href="assets/css/header.css?v=1.3"> 
    <link rel="stylesheet" href="assets/css/notification.css?v=1.1">
    
        <style>
		#jobs .card {
		    transition: transform 0.3s ease, box-shadow 0.3s ease;
		    border: 2px solid green;
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
		
		.custom-notification-container {
		    position: absolute;
		    top: 10px;
		    right: 10px;
		    z-index: 10;
		}
		
		#upload-btn-container {
		    display: flex;
		    align-items: center;
		    justify-content: flex-end;
		    width: 120px;
		    margin-left: auto;
		    background-color: green;
		    border-radius: 12px;
		    padding: 10px;
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
		

	
	.notification-container.custom-notification-container .dropdown-menu {
    z-index: 1050; /* Đảm bảo dropdown-menu ở trên các phần tử khác */
    position: absolute;
    top: 0;
    right: 0;
    transform: translateX(80%);
    background-color: #28a745; /* Màu xanh lá */
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15); /* Để tạo hiệu ứng bóng cho dropdown */
    min-width: 100px; /* Đảm bảo dropdown có chiều rộng phù hợp */
    padding: 5px 0;
}

.notification-container.custom-notification-container .dropdown-item {
    color: white; /* Màu chữ của các mục trong dropdown */
    font-size: 14px; /* Kích thước chữ */
    padding: 10px 15px;
    transition: background-color 0.2s ease;
    text-align: center;
}

.notification-container.custom-notification-container .dropdown-item:hover {
    background-color: #218838; /* Màu xanh lá đậm khi hover */
    cursor: pointer;
}

.notification-container.custom-notification-container .dropdown-item:focus {
    outline: none; 
    background-color: #218838;
}
	
		
		
	

    </style>

<div class="container mt-3">
    <div id="upload-btn-container" data-bs-toggle="modal" data-bs-target="#uploadModal">
        <span id = "upload-text">Upload</span>
        <img id="upload-img" src="assets/img/upload.png" alt="Upload Image">
    </div>
</div>

<section id="jobs" class="py-3 my-1 position-relative">
    <div class="container">
        <div class="row justify-content-start">
            <% 
                ArrayList<Job> JobList = (ArrayList<Job>) session.getAttribute("JobArray");
            	Integer UserId = (Integer) session.getAttribute("UserId");
                if (JobList != null) {
                    for (Job job : JobList) {
                        Date postedAt = job.getPostedAt();  
                        Date currentDate = new Date();
                        long diffInMillis = currentDate.getTime() - postedAt.getTime();
                        long daysAgo = TimeUnit.MILLISECONDS.toDays(diffInMillis);
                        int clientId = job.getClientId();
            %>
            <div class="col-12 col-sm-6 col-md-4 col-lg-4 mb-4">
					<div class="card text-center shadow-sm h-100 position-relative"
					     data-title="<%= job.getTitle() %>"
					     data-company="<%= job.getCompanyName() %>"
					     data-description="<%= job.getDescription() %>"
					     data-location="<%= job.getLocation() %>"
					     data-salary="<%= job.getSalary() %>"
					     data-deadline="<%= job.getDeadline() %>"
					     data-days-ago="<%= daysAgo %>">
                   
                    <div class="card-body">
                        <h4 class="card-title mb-1"><%= job.getTitle() %></h4>
                        <p class="card-text text-muted mb-3"><%= job.getCompanyName() %></p>
                        <p class="card-text"><%= job.getSalary() %></p>
                        <p class="card-text"><%= job.getLocation() %></p>
                        <p class="card-text"><%= job.getDeadline() %></p>
                        <p class="card-text"> Đăng <%= daysAgo %> ngày trước </p>
                    </div>
                </div>
				<% if (role != null && (role == 2 && UserId == clientId || role == 1)) { %>
				    <div class="notification-container custom-notification-container">
				        <span class="dropdown">
				            <a class="nav-link" data-toggle="dropdown" aria-expanded="false" href="#">
				                <img src="assets/img/three-dot.png" alt="User" height="35px" width="35px">
				            </a> 
				            <div class="dropdown-menu" role="menu" style="z-index: 1050; position: absolute; right: 50%; transform: translateX(50%);">
				                <% if (role != null && role == 2 && UserId == clientId) { %>
				                    <a class="dropdown-item" role="presentation" onclick="openEditJobModal('<%= job.getJobId() %>', '<%= job.getTitle() %>', '<%= job.getCompanyName() %>', '<%= job.getDescription() %>', '<%= job.getLocation() %>', '<%= job.getDeadline() %>', '<%= job.getSalary() %>')">Edit</a>
				                <% } %>
				                <% if (role != null && (role == 2 && UserId == clientId || role == 1)) { %>
				                    <a class="dropdown-item" role="presentation" onclick="deleteJob('<%= job.getJobId() %>')">Delete</a>
				                <% } %>
				            </div>
				        </span>
				    </div>
				<% } %>

            </div>
            <% 
                    }
                }
            %>
        </div>
    </div>
</section>

<div class="modal fade" id="uploadModal" tabindex="-1" aria-labelledby="uploadModalLabel" aria-hidden="true" accept-charset="UTF-8">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="uploadModalLabel">Upload New Job</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form action="/LTM_BTH/Controller?action=upload" method="POST" name="upload">
            <div class="mb-3">
                <label for="uploadJobTitle" class="form-label">Job Title</label>
                <input type="text" class="form-control" id="uploadJobTitle" name="title" required>
            </div>

             <div class="mb-3">
                <label for="uploadJobCompany" class="form-label">Company</label>
                <input type="text" class="form-control" id="uploadJobCompany" name="company" required>
            </div>
            <div class="mb-3">
                <label for="uploadJobDescription" class="form-label">Job Description</label>
                <textarea class="form-control" id="uploadJobDescription" name="description" rows="4" required></textarea>
            </div>
            <div class="mb-3">
                <label for="uploadJobSalary" class="form-label">Salary</label>
                <select class="form-control" id="uploadSalarySelect" name="salary" required>
                   <option value="Thỏa thuận">Thỏa thuận</option>
                    <option value="5.000.000 - 10.000.000">5.000.000 - 10.000.000</option>
                    <option value="10.000.000 - 20.000.000">10.000.000 - 20.000.000</option>
                    <option value="20.000.000 - 30.000.000">20.000.000 - 30.000.000</option>
                    <option value="50.000.000 +">50.000.000 +</option>
                    <option value="Khác">Khác </option>
                </select>
            </div>
            <div class="mb-3">
                <label for="uploadJobLocation" class="form-label">Location</label>
                <input type="text" class="form-control" id="uploadJobLocation" name="location" required>
            </div>
            <div class="mb-3">
                <label for="uploadJobDeadline" class="form-label">Deadline</label>
                <input type="date" class="form-control" id="uploadJobDeadline" name="deadline" required>
            </div>
            <button type="submit" class="btn btn-primary">Upload</button>
        </form>
      </div>
    </div>
  </div>
</div>

<div class="modal fade" id="updateModal" tabindex="-1" aria-labelledby="updateModalLabel" aria-hidden="true" >
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="updateModalLabel">Edit Job</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
 	<form id="editJobForm" action="/LTM_BTH/Controller?action=update" method="POST"> 
 	           <div class="mb-3">
                <label for="editJobTitle" class="form-label">Job Title</label>
                <input type="text" class="form-control" id="editJobTitle" name="title" required>
            </div>

             <div class="mb-3">
                <label for="editJobCompany" class="form-label">Company</label>
                <input type="text" class="form-control" id="editJobCompany" name="company" required>
            </div>
            <div class="mb-3">
                <label for="editJobDescription" class="form-label">Job Description</label>
                <textarea class="form-control" id="editJobDescription" name="description" rows="4" required></textarea>
            </div>
            <div class="mb-3">
                <label for="editJobSalary" class="form-label">Salary</label>
                <select class="form-control" id="editSalarySelect" name="salary" required>
                    <option value="Thỏa thuận">Thỏa thuận</option>
                    <option value="5.000.000 - 10.000.000">5.000.000 - 10.000.000</option>
                    <option value="10.000.000 - 20.000.000">10.000.000 - 20.000.000</option>
                    <option value="20.000.000 - 30.000.000">20.000.000 - 30.000.000</option>
                    <option value="50.000.000 +">50.000.000 +</option>
                    <option value="Khác">Khác </option>
                </select>
            </div>
            <div class="mb-3">
                <label for="editJobLocation" class="form-label">Location</label>
                <input type="text" class="form-control" id="editJobLocation" name="location" required>
            </div>
            <div class="mb-3">
                <label for="editJobDeadline" class="form-label">Deadline</label>
                <input type="date" class="form-control" id="editJobDeadline" name="deadline" required>
            </div>
            <button type="submit" class="btn btn-primary">Update</button>
        </form>
      </div>
    </div>
  </div>
</div>

<div class="modal fade" id="jobDetailModal" tabindex="-1" aria-labelledby="jobDetailModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="jobDetailTitle">Job Details</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <p><strong>Company:</strong> <span id="jobDetailCompany"></span></p>
        <p><strong>Description:</strong> <span id="jobDetailDescription"></span></p>
        <p><strong>Location:</strong> <span id="jobDetailLocation"></span></p>
        <p><strong>Salary:</strong> <span id="jobDetailSalary"></span></p>
        <p><strong>Deadline:</strong> <span id="jobDetailDeadline"></span></p>
        <p><strong>Posted:</strong> <span id="jobDetailPosted"></span> days ago</p>
      </div>
    </div>
  </div>
</div>

<script>
function deleteJob(jobId) {
    if (confirm("Are you sure you want to delete this job? This action cannot be undone")) {
        window.location.href = "/LTM_BTH/Controller?action=delete&jobId=" + jobId;
    }
}

function openEditJobModal(jobId, title, company, description, location, deadline, salary) {
    document.getElementById("editJobForm").action = "/LTM_BTH/Controller?action=update&jobId=" + jobId;
    document.getElementById("editJobTitle").value = title;
    document.getElementById("editJobCompany").value = company;
    document.getElementById("editJobDescription").value = description;
    document.getElementById("editJobLocation").value = location;
    document.getElementById("editJobDeadline").value = deadline;
    document.getElementById("editSalarySelect").value = salary;

    var updateModal = new bootstrap.Modal(document.getElementById("updateModal"));
    updateModal.show();
}

function openJobDetailModal(title, company, description, location, salary, deadline, daysAgo) {
    document.getElementById("jobDetailTitle").textContent = title;
    document.getElementById("jobDetailCompany").textContent = company;
    document.getElementById("jobDetailDescription").textContent = description;
    document.getElementById("jobDetailLocation").textContent = location;
    document.getElementById("jobDetailSalary").textContent = salary;
    document.getElementById("jobDetailDeadline").textContent = deadline;
    document.getElementById("jobDetailPosted").textContent = daysAgo;

    var jobDetailModal = new bootstrap.Modal(document.getElementById("jobDetailModal"));
    jobDetailModal.show();
}

document.querySelector('#jobs .row').addEventListener('click', function(event) {
    const card = event.target.closest('.card'); // Kiểm tra xem có click vào card hay không
    if (card) {
        // Lấy thông tin từ các data-attribute của card
        openJobDetailModal(
            card.dataset.title,
            card.dataset.company,
            card.dataset.description,
            card.dataset.location,
            card.dataset.salary,
            card.dataset.deadline,
            card.dataset.daysAgo
        );
    }
});

</script>




<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
