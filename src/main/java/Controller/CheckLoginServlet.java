package Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.BEAN.Job;
import Model.BEAN.User;
import Model.BO.CheckLoginBO;
import Model.BO.UserBO;

@WebServlet("/CheckLoginServlet")
public class CheckLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String des = "/pages/user/login.jsp"; 
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        System.out.println(username);

        CheckLoginBO checkLoginBO = new CheckLoginBO();
        UserBO userBO = new UserBO();
        ArrayList<Job> JobArray = null;
        int RoleUser = -1;
        int UserId = -1;

        try {
 
        	if (checkLoginBO.isValidUser(username, password)) {
                JobArray = checkLoginBO.getAllJobList();
                RoleUser = checkLoginBO.getRole(username);
                UserId = checkLoginBO.getUserId(username);
                User user = userBO.getUserById(UserId); 
                
                HttpSession session = request.getSession();
                session.setAttribute("JobArray", JobArray);
                session.setAttribute("Role", RoleUser);
                session.setAttribute("UserId", UserId);
                session.setAttribute("user", user);


                
                System.out.println("Role " + RoleUser);
                des = "/layouts/decorator.jsp";
            } else {
                request.setAttribute("errorMessage", "Incorrect username or password.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            des = "/pages/error/error.jsp";
            request.setAttribute("errorMessage", "An error occurred during processing. Please try again later.");
        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher(des);
        rd.forward(request, response);
    }
}
