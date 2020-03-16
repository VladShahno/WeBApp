package webApp.filter;

import java.io.IOException;
import java.sql.Connection;
import java.util.Collection;
import java.util.Map;
 
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import webApp.conn.ConnectionUtils;
import webApp.utils.MyUtils;

@WebFilter(filterName = "jdbcFilter", urlPatterns = { "/*" })
public class JDBCFilter implements Filter {
 
    public JDBCFilter() {
    }
 
    @Override
    public void init(FilterConfig fConfig) throws ServletException {
 
    }
 
    @Override
    public void destroy() {
 
    }
 
	// Check if Servlet is the target of the current request 
    private boolean needJDBC(HttpServletRequest request) {
        System.out.println("JDBC Filter");
        // 
        // Servlet Url-pattern: /spath/*
        //   
        // => /spath
        String servletPath = request.getServletPath();
        // => /abc/mnp
        String pathInfo = request.getPathInfo();
 
        String urlPattern = servletPath;
 
        if (pathInfo != null) {
            // => /spath/*
            urlPattern = servletPath + "/*";
        }
 
        // Key: servletName.
        // Value: ServletRegistration
        Map<String, ? extends ServletRegistration> servletRegistrations = request.getServletContext()
                .getServletRegistrations();
 
		//Collect all Servlet in webApp
        Collection<? extends ServletRegistration> values = servletRegistrations.values();
        for (ServletRegistration sr : values) {
            Collection<String> mappings = sr.getMappings();
            if (mappings.contains(urlPattern)) {
                return true;
            }
        }
        return false;
    }
 
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
 
        HttpServletRequest req = (HttpServletRequest) request;
 
        	// Open connection only for request with a special link.
     		// (For example, a link to servlet, jsp, ..)
     		// Avoid opening Connection for regular queries.
     		// (For example image, css, javascript, ...)
        if (this.needJDBC(req)) {
 
            System.out.println("Open Connection for: " + req.getServletPath());
 
            Connection conn = null;
            try {
            	// Create object connected to DB
				conn = ConnectionUtils.getConnection();
				// Set automatic commit false
				conn.setAutoCommit(false);
				
				// Save Object  Connection in attribute in request.
                MyUtils.storeConnection(request, conn);
                
                // Allow request to go further
                chain.doFilter(request, response);
                
               // Call the commit () method to complete the transaction with DB
                conn.commit();
            } 
            catch (Exception e) {
                e.printStackTrace();
                ConnectionUtils.rollbackQuietly(conn);
                throw new ServletException();
            } 
            
            finally {
                ConnectionUtils.closeQuietly(conn);
            }
        }
        else {
            // Allow request to go further
            chain.doFilter(request, response);
        }
    }
}
