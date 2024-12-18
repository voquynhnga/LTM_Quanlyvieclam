package Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Job;
import model.bo.JobBO;

@WebServlet("/JobControllerServlet")
public class JobControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        JobBO jobBO = new JobBO();
        String destination = null;

        try {
            if ("list".equals(action)) {
                List<Job> jobs = jobBO.searchJobs("");
                request.setAttribute("jobs", jobs);
                destination = "/jobList.jsp";
            } else if ("add".equals(action)) {
                String title = request.getParameter("title");
                String description = request.getParameter("description");
                Job job = new Job(title, description);
                if (jobBO.addJob(job)) {
                    destination = "/JobControllerServlet?action=list";
                }
            } else if ("update".equals(action)) {
                int jobId = Integer.parseInt(request.getParameter("jobId"));
                String title = request.getParameter("title");
                String description = request.getParameter("description");
                Job updatedJob = new Job(title, description);
                if (jobBO.updateJob(jobId, updatedJob)) {
                    destination = "/JobControllerServlet?action=list";
                }
            } else if ("delete".equals(action)) {
                int jobId = Integer.parseInt(request.getParameter("jobId"));
                if (jobBO.deleteJob(jobId)) {
                    destination = "/JobControllerServlet?action=list";
                }
            }
            RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
            rd.forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
