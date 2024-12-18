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

import model.bean.User;
import model.bo.UserBO;

@WebServlet("/UserControllerServlet")
public class UserControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        UserBO userBO = new UserBO();
        String destination = null;

        try {
            if ("list".equals(action)) {
                List<User> users = userBO.getAllUsers();
                request.setAttribute("users", users);
                destination = "/userList.jsp";
            } else if ("delete".equals(action)) {
                int userId = Integer.parseInt(request.getParameter("userId"));
                if (userBO.deleteUser(userId)) {
                    destination = "/UserControllerServlet?action=list";
                }
            } else if ("view".equals(action)) {
                int userId = Integer.parseInt(request.getParameter("userId"));
                User user = userBO.getUserById(userId);
                request.setAttribute("user", user);
                destination = "/userDetails.jsp";
            }
            RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
            rd.forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
