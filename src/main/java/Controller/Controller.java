package Controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.BEAN.Job;
import Model.BO.bo;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
    	
    	request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        HttpSession session = request.getSession();

        
        String action = request.getParameter("action");
        String des = "/pages/error/error.jsp"; 
        bo jobBO = new bo();

        if ("upload".equals(action)) {
            int jobId = 0;
            try {
                jobId = jobBO.getMaxId();
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                request.setAttribute("message", "Lỗi khi lấy Job ID: " + e.getMessage());
                des = "/error.jsp";
                RequestDispatcher rd = getServletContext().getRequestDispatcher(des);
                rd.forward(request, response);
                return;
            }

            String title = request.getParameter("title");
            String company = request.getParameter("company");
            String salary = request.getParameter("salary");
            String description = request.getParameter("description");
            String location = request.getParameter("location");
            String deadlineStr = request.getParameter("deadline");
            int clientId = (Integer) session.getAttribute("UserId");
            
            System.out.println(title +company+ salary+description+location+deadlineStr);

            if (title == null || company == null || salary == null || description == null || location == null || deadlineStr == null) {
                request.setAttribute("message", "Vui lòng nhập đầy đủ thông tin!");
                des = "/layouts/decorator.jsp";
                RequestDispatcher rd = getServletContext().getRequestDispatcher(des);
                rd.forward(request, response);
                return;
            }

            LocalDate now = LocalDate.now();
            Date postedAtDate = Date.valueOf(now);
            Date deadlineDate;
            try {
                deadlineDate = Date.valueOf(deadlineStr);
            } catch (IllegalArgumentException e) {
                request.setAttribute("message", "Ngày deadline không hợp lệ!");
                des = "/layouts/decorator.jsp";
                RequestDispatcher rd = getServletContext().getRequestDispatcher(des);
                rd.forward(request, response);
                return;
            }

            Job job = new Job(
                jobId,
                title,
                description,
                company,
                location,
                salary,
                deadlineDate,
                postedAtDate,
                clientId
                
            );

            try {
                boolean success = jobBO.addNew(job);
                if (success) {
                    ArrayList<Job> jobList = jobBO.getAll("", "");
                    session.setAttribute("JobArray", jobList);

                    des = "/layouts/decorator.jsp";
                    RequestDispatcher rd = getServletContext().getRequestDispatcher(des);
                    rd.forward(request, response);
                } else {
                    request.setAttribute("message", "Thêm Job thất bại!");
                    des = "/layouts/decorator.jsp";
                    RequestDispatcher rd = getServletContext().getRequestDispatcher(des);
                    rd.forward(request, response);
                }
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("message", "Lỗi cơ sở dữ liệu: " + e.getMessage());
                des = "/error.jsp"; // Chuyển hướng tới trang lỗi
                RequestDispatcher rd = getServletContext().getRequestDispatcher(des);
                rd.forward(request, response);
            }
        }


        if ("checkID".equals(action)) {
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String company = request.getParameter("company");

            try {
                boolean exists = jobBO.isExist(title, description, company);
                response.getWriter().write(exists ? "exists" : "not_exists");
                return;
            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().write("error");
                return;
            }
        }

        if ("getAll".equals(action)) {
            try {
                ArrayList<Job> jobList = jobBO.getAll("", "");
                request.setAttribute("JobArray", jobList);
                des = "/layouts/decorator.jsp";
                RequestDispatcher rd = getServletContext().getRequestDispatcher(des);
                rd.forward(request, response);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                request.setAttribute("message", "Lỗi cơ sở dữ liệu: " + e.getMessage());
            }
        }

        if ("getByID".equals(action)) {
            String jobId = request.getParameter("jobId");

            try {
                Job job = jobBO.getByID(Integer.parseInt(jobId));
                request.setAttribute("job", job);
                des = "/layouts/decorator.jsp";
                RequestDispatcher rd = getServletContext().getRequestDispatcher(des);
                rd.forward(request, response);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                request.setAttribute("message", "Lỗi cơ sở dữ liệu: " + e.getMessage());
            }
        }

        if ("update".equals(action)) {
            String jobIdStr = request.getParameter("jobId");
            int jobId = Integer.parseInt(jobIdStr); 
            String title = request.getParameter("title");
            String company = request.getParameter("company");
            String salary = request.getParameter("salary");
            String description = request.getParameter("description");
            String location = request.getParameter("location");
            String deadlineStr = request.getParameter("deadline");

            try {
                Date deadline = Date.valueOf(deadlineStr);

                boolean result = jobBO.update(jobId, title, company, salary, description, location, deadline);

                if (result) {
                	 ArrayList<Job> jobList = jobBO.getAll("", "");
                	 session.setAttribute("JobArray", jobList);
                     des = "/layouts/decorator.jsp";
                     RequestDispatcher rd = getServletContext().getRequestDispatcher(des);
                     rd.forward(request, response);
                } else {
                    request.setAttribute("message", "Cập nhật thất bại!");
                }
            } catch (SQLException | ClassNotFoundException | IllegalArgumentException e) {
                e.printStackTrace();
                request.setAttribute("message", "Lỗi cập nhật: " + e.getMessage());
            }
        }


        if ("search".equals(action)) {
            String searchKeyword = request.getParameter("searchKeyword");
            String searchType = request.getParameter("searchType");

            try {
                ArrayList<Job> jobList = jobBO.getAll(searchType, searchKeyword);
                session.setAttribute("JobArray", jobList);
                des = "/layouts/decorator.jsp";
                RequestDispatcher rd = getServletContext().getRequestDispatcher(des);
                rd.forward(request, response);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }

        if ("delete".equals(action)) {
            String jobId = request.getParameter("jobId");

            try {
                boolean isDeleted = jobBO.delete(Integer.parseInt(jobId));
                if (isDeleted) {
                	   ArrayList<Job> jobList = jobBO.getAll("", "");
                       session.setAttribute("JobArray", jobList);
                       des = "/layouts/decorator.jsp";
                       RequestDispatcher rd = getServletContext().getRequestDispatcher(des);
                       rd.forward(request, response);
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                request.setAttribute("message", "Lỗi cơ sở dữ liệu: " + e.getMessage());
            }
        }

  
    }
}
