package webApp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webApp.beans.UserData;
import webApp.utils.*;

@WebServlet(urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public LoginServlet() {
        super();
    }
 
    // Show Login page
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
    	// Ford(redirect) to /WEB-INF/views/loginView.jsp
        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
 
        dispatcher.forward(request, response);
 
    }
 
    // Will be executed When user input userName and password and press Submit
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String rememberMeStr = request.getParameter("rememberMe");
        boolean remember = "Y".equals(rememberMeStr);
 
        UserData user = null;
        boolean hasError = false;
        String errorString = null;
 
        if (userName == null || password == null || userName.length() == 0 || password.length() == 0) {
            hasError = true;
            errorString = "Required username and password!";
        } 
        else {
            Connection conn = MyUtils.getStoredConnection(request);
            try {
                // Find user in DB
                user = DBUtils.findUser(conn, userName, password);
 
                if (user == null) {
                    hasError = true;
                    errorString = "User Name or password invalid";
                }
            } 
            catch (SQLException e) {
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            }
        }
        
        // In error forward to /WEB-INF/views/login.jsp
        if (hasError) {
            user = new UserData();
            user.setUserName(userName);
            user.setPassword(password);
            
            // Save Information in request attribute before forward
            request.setAttribute("errorString", errorString);
            request.setAttribute("user", user);
 
            // Forward to page /WEB-INF/views/login.jsp
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
 
            dispatcher.forward(request, response);
        }
        
        // In case there is no error.
        // Store user information in Session.
        // And redirect to userInfo page
        else {
            HttpSession session = request.getSession();
            MyUtils.storeLoginedUser(session, user);
 
            // if user choose func "Remember me"
            if (remember) {
                MyUtils.storeUserCookie(response, user);
            }
            // or delete Cookie
            else {
                MyUtils.deleteUserCookie(response);
            }
 
            // Redirect to page /userInfo
            response.sendRedirect(request.getContextPath() + "/userInfo");
        }
    }
 
}
