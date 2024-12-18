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

import model.bean.Role;
import model.bo.RoleBO;

@WebServlet("/RoleControllerServlet")
public class RoleControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        RoleBO roleBO = new RoleBO();
        String destination = null;

        try {
            if ("list".equals(action)) {
                List<String> roles = roleBO.getPermissionsByRole(1); 
                request.setAttribute("roles", roles);
                destination = "/roleList.jsp";
            } else if ("add".equals(action)) {
                String roleName = request.getParameter("roleName");
                Role role = new Role(roleName);
                if (roleBO.addRole(role)) {
                    destination = "/RoleControllerServlet?action=list";
                }
            } else if ("delete".equals(action)) {
                int roleId = Integer.parseInt(request.getParameter("roleId"));
                if (roleBO.deleteRole(roleId)) {
                    destination = "/RoleControllerServlet?action=list";
                }
            }
            RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
            rd.forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
