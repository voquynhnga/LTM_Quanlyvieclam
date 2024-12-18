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
import Model.BO.CheckLoginBO;

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
        ArrayList<Job> JobArray = null;
        int RoleUser = -1;

        try {

//
//            if (username == null || username.isEmpty() || password == null || password.isEmpty() || em) {
//                request.setAttribute("errorMessage", "Tên đăng nhập và mật khẩu không được để trống.");
//            } else 
        	if (checkLoginBO.isValidUser(username, password)) {
                JobArray = checkLoginBO.getAllJobList();
                RoleUser = checkLoginBO.getRole(username);
                
                HttpSession session = request.getSession();
                session.setAttribute("JobArray", JobArray);
                session.setAttribute("Role", RoleUser);
                
                System.out.println("Role " + RoleUser);
                des = "/layouts/decorator.jsp";
            } else {
                request.setAttribute("errorMessage", "Sai tên đăng nhập hoặc mật khẩu.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            des = "/pages/error/error.jsp";
            request.setAttribute("errorMessage", "Có lỗi xảy ra trong quá trình xử lý. Vui lòng thử lại sau.");
        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher(des);
        rd.forward(request, response);
    }
}
