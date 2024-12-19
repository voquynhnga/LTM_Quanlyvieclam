package Controller;

import Model.BEAN.Job;
import Model.BEAN.User;
import Model.BO.CheckLoginBO;
import Model.BO.UserBO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
@WebServlet("/ProfileController")
public class ProfileController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserBO userBO;

    @Override
    public void init() throws ServletException {
        userBO = new UserBO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	    	request.setCharacterEncoding("UTF-8");
    	        response.setContentType("text/html; charset=UTF-8");
    	        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        HttpSession session = request.getSession();

        
        String action = request.getParameter("action");
        
   
            if ("update".equals(action)) {
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String email = request.getParameter("email");
                String phoneNumber = request.getParameter("phoneNumber");
                String password = request.getParameter("password");

                User user = (User) session.getAttribute("user");

                user.setFirstname(firstName);
                user.setLastname(lastName);
                user.setEmail(email);
                user.setPhoneNumber(phoneNumber);
                user.setPassword(password);  

                boolean result;
				try {
					result = userBO.updateUser(user);
					if (result) {
					    session.setAttribute("user", user);

					    request.setAttribute("message", "Update successfully!");
					    RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/user/account.jsp");
					    rd.forward(request, response);
					} else {
					    request.setAttribute("message", "Update failed!");
					    RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/user/account.jsp");
					    rd.forward(request, response);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			
            }
        }
    
    
}
