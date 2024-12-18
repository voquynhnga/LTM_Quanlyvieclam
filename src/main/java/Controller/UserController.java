package Controller;

import Model.BEAN.User;
import Model.BO.UserBO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.io.IOException;
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
      
        List<User> userList = userBO.getAllUsers();

        if (userList == null || userList.isEmpty()) {
            System.out.println("No users found or user list is null.");
        } else {
            System.out.println("Number of users retrieved: " + userList.size());
        }
        request.setAttribute("userList", userList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/manage/manage_user.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            try {
                int userId = Integer.parseInt(request.getParameter("id"));
                userBO.deleteUser(userId);
                System.out.println("User with ID " + userId + " deleted successfully.");
            } catch (NumberFormatException e) {
                System.err.println("Invalid user ID format: " + request.getParameter("id"));
            } catch (Exception e) {
                System.err.println("Error deleting user: " + e.getMessage());
            }

            response.sendRedirect("UserController");
        } else {
            System.err.println("Unknown action: " + action);
        }
    }
}
