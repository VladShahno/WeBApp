package webApp.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import webApp.beans.UserData;
import webApp.utils.*;

@WebFilter(filterName = "cookieFilter", urlPatterns = {"/*"})
public class CookieFilter implements Filter {
	
	public CookieFilter() {
	}
	
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		
	}
	
	@Override
	public void destroy() {
		
	}
	
	@Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		UserData userSession = MyUtils.getLoginedUser(session);
		
		if(userSession != null) {
			session.setAttribute("COOKIE_CHECKED", "CHEKED");
			chain.doFilter(request, response);
			System.out.println("I'm not null");
			return;
		}
		
		// Connection created in JDBCFilter
		Connection conn = MyUtils.getStoredConnection(request);
		
		// Flag for Cookie check
		String checked = (String) session.getAttribute("COOKIE_CHEKED");
		if(checked == null && conn != null) {
			String userName = MyUtils.getUserNameInCookie(req);
			try {
				UserData user = DBUtils.findUser(conn, userName);
				MyUtils.storeLoginedUser(session, user);
			}
			catch(SQLException exp) {
				exp.printStackTrace();
			}
			// Mark checked Cookie
			session.setAttribute("COOKIE_CHECKED", "CHECKED");
		}
		
		chain.doFilter(request, response);
	}

}
