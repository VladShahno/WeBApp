package webApp.utils;

import java.sql.Connection;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import webApp.beans.UserData;

public class MyUtils {

	public static final String ATT_NAME_CONNECTION = "ATTRIBUTE_FOR_CONNECTION";
	
	private static final String ATT_NAME_USER_NAME = "ATTRIBUTE_FOR_STORE_USER_NAME_IN_COOKIE";
	
	/* Save Connection in attribute in request
	 * This storage information exists only at the time of the request.
	 * As long as the data is returned to the user's application.
	 */
	
	public static void storeConnection(ServletRequest request, Connection conn) {
		request.setAttribute(ATT_NAME_CONNECTION, conn);
	}
	
	// Return object saved in attribute in request 
	public static Connection getStoredConnection(ServletRequest request) {
		Connection conn = (Connection) request.getAttribute(ATT_NAME_CONNECTION);
		return conn;
	}
	
	// Save user's data login in Session
	public static void  storeLoginedUser(HttpSession session, UserData loginedUser) {
		
		session.setAttribute("loginedUser", loginedUser);
	}
	
	// Return user's data login in Session
	public static UserData getLoginedUser(HttpSession session) {
		UserData loginedUser = (UserData) session.getAttribute("loginedUser");
		return loginedUser;
	}
	
	// Save user's data in Cookie
	public static void storeUserCookie(HttpServletResponse responce, UserData user) {
		
		System.out.println("Store user cookie");
		Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, user.getUserName());
		// one day converted to seconds
		cookieUserName.setMaxAge(24*60*60);
		responce.addCookie(cookieUserName);
	}
	
	public static String getUserNameInCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (ATT_NAME_USER_NAME.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
 
    // Delete user's Cookie
    public static void deleteUserCookie(HttpServletResponse response) {
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, null);
        cookieUserName.setMaxAge(0);
        response.addCookie(cookieUserName);
    }
}
