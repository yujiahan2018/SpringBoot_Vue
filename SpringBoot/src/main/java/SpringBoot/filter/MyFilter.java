package SpringBoot.filter;

import SpringBoot.logger.MyLogger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "MyFilter", urlPatterns = { "/*" })
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        MyLogger.myLoggerDebug("Filter start...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

//
        HttpServletResponse hsrp = (HttpServletResponse) servletResponse;
        hsrp.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");//axio
        hsrp.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5500");//原生Ajax
//        hsrp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//        hsrp.setHeader("Access-Control-Max-Age", "3600");
//        hsrp.setHeader("Access-Control-Allow-Headers", "x-requested-with");

        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {
        MyLogger.myLoggerDebug("Filter end...");
    }
}
