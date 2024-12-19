package Controller;

import Model.BEAN.Job;
import Model.BEAN.User;
import Model.BO.UserBO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/UserController")
public class UserController extends HttpServlet {
    private UserBO userBO;

    @Override
    public void init() {
        userBO = new UserBO(); 
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        
        String des = "/pages/error/error.jsp"; 
      
        if ("delete".equals(action)) {
            try {
                int userId = Integer.parseInt(request.getParameter("id"));
                userBO.deleteUser(userId);
                
                ArrayList<User> userList = (ArrayList<User>) userBO.getAllUsers();
                session.setAttribute("UserArray", userList);
                
                System.out.println("userList " + userList);
                des = "/pages/manage/manage_user.jsp";
                RequestDispatcher rd = getServletContext().getRequestDispatcher(des);
                rd.forward(request, response);                
            } catch (NumberFormatException e) {
                System.err.println("Invalid user ID format: " + request.getParameter("id"));
            } catch (Exception e) {
                System.err.println("Error deleting user: " + e.getMessage());
            }

        } else {
            System.err.println("Unknown action: " + action);
        }
   
    }
}
