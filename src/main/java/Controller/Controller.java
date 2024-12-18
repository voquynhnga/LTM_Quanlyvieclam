package Controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.BEAN.Job;
import Model.BO.bo;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("upload".equals(action)) {
            String title = request.getParameter("title");
            String company = request.getParameter("company");
            String salary = request.getParameter("salary");
            String description = request.getParameter("description");
            String location = request.getParameter("location");
            String deadlineStr = request.getParameter("deadline");
            LocalDate now = LocalDate.now();

            Date postedAtDate = Date.valueOf(now);
            
            String username = request.getParameter("username");
            

            Job job = new Job();
            job.setTitle(title);
            job.setCompanyName(company);
            job.setSalary(salary);
            job.setDescription(description);
            job.setLocation(location);
            job.setDeadline(Date.valueOf(deadlineStr));
            job.setPostedAt(postedAtDate);

            bo jobBO = new bo();
            try {
                boolean success = jobBO.addNew(job);

                if (success) {
                    request.setAttribute("message", "Thêm công việc thành công!");
                    ArrayList<Job> jobList = jobBO.getAll("", "");
                    request.setAttribute("List", jobList);
                    request.getRequestDispatcher("/layouts/decorator.jsp").forward(request, response);
                } else {
                    request.setAttribute("message", "Có lỗi xảy ra khi thêm công việc.");
                    request.getRequestDispatcher("/pages/error/error.jsp").forward(request, response);
                }
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("message", "Lỗi cơ sở dữ liệu: " + e.getMessage());
                request.getRequestDispatcher("/pages/error/error.jsp").forward(request, response);
            }
        }

        if ("checkID".equals(action)) {
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String company = request.getParameter("company");

            bo jobBO = new bo();
            try {
                boolean exists = jobBO.isExist(title, description, company);

                if (exists) {
                    response.getWriter().write("exists");
                } else {
                    response.getWriter().write("not_exists");
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().write("error");
            }
        }

        if ("getAll".equals(action)) {
            bo jobBO = new bo();
            try {
                ArrayList<Job> jobList = jobBO.getAll("", "");
                request.setAttribute("List", jobList);
                request.getRequestDispatcher("/layouts/decorator.jsp").forward(request, response);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                request.setAttribute("message", "Lỗi cơ sở dữ liệu: " + e.getMessage());
                request.getRequestDispatcher("/pages/error/error.jsp").forward(request, response);
            }
        }

        if ("getByID".equals(action)) {
            String jobId = request.getParameter("jobId");

            bo jobBO = new bo();
            try {
                Job job = jobBO.getByID(Integer.parseInt(jobId));
                request.setAttribute("job", job);
                request.getRequestDispatcher("/layouts/decorator.jsp").forward(request, response);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                request.setAttribute("message", "Lỗi cơ sở dữ liệu: " + e.getMessage());
                request.getRequestDispatcher("/pages/error/error.jsp").forward(request, response);
            }
        }

        if ("update".equals(action)) {
            String jobId = request.getParameter("jobId");
            String title = request.getParameter("title");
            String company = request.getParameter("company");
            String salary = request.getParameter("salary");
            String description = request.getParameter("description");
            String location = request.getParameter("location");
            String deadlineStr = request.getParameter("deadline");

            Job job = new Job();
            job.setJobId(Integer.parseInt(jobId));
            job.setTitle(title);
            job.setCompanyName(company);
            job.setSalary(salary);
            job.setDescription(description);
            job.setLocation(location);
            job.setDeadline(Date.valueOf(deadlineStr));
            

            bo jobBO = new bo();
            try {
                boolean result = jobBO.update(job);
                if (result) {
                    response.sendRedirect("Controller?action=getAll");
                } else {
                    request.setAttribute("message", "Cập nhật thất bại!");
                    request.getRequestDispatcher("pages/error/error.jsp").forward(request, response);
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                request.setAttribute("message", "Lỗi cập nhật: " + e.getMessage());
                request.getRequestDispatcher("pages/error/error.jsp").forward(request, response);
            }
        }

        if ("search".equals(action)) {
            String searchKeyword = request.getParameter("searchKeyword");
            String searchType = request.getParameter("searchType");

            bo jobBO = new bo();
            ArrayList<Job> jobList = new ArrayList<>();

            try {
                jobList = jobBO.getAll(searchType, searchKeyword);
                request.setAttribute("JobArray", jobList);
                request.getRequestDispatcher("/layouts/decorator.jsp").forward(request, response);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }

        if ("delete".equals(action)) {
            String jobId = request.getParameter("jobId");
            bo jobBO = new bo();

            try {
                boolean isDeleted = jobBO.delete(Integer.parseInt(jobId));

                if (isDeleted) {
                    ArrayList<Job> jobList = jobBO.getAll("", "");
                    request.setAttribute("List", jobList);
                    request.getRequestDispatcher("/layouts/decorator.jsp").forward(request, response);
                } else {
                    request.setAttribute("message", "Không thể xóa công việc.");
                    request.getRequestDispatcher("pages/error/error.jsp").forward(request, response);
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                request.setAttribute("message", "Lỗi cơ sở dữ liệu: " + e.getMessage());
                request.getRequestDispatcher("pages/error/error.jsp").forward(request, response);
            }
        }
    }
}
