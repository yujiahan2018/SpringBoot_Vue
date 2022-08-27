package SpringBoot.filter;

import SpringBoot.logger.MyLogger;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//测试用filter
@WebFilter(filterName = "MyFilterTest", urlPatterns = { "/*" })
public class MyFilterTest implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        MyLogger.myLoggerDebug("FilterTest start...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        filterChain.doFilter(servletRequest, servletResponse);

//        HttpServletResponse hsrp = (HttpServletResponse) servletResponse;
//        String header = hsrp.getHeader("Access-Control-Allow-Origin");
//        MyLogger.myLoggerDebug("Access-Control-Allow-Origin:" + header);
//        header = hsrp.getHeader("Content-Type");
//        MyLogger.myLoggerDebug("Content-Type:" + header);
//
//
//        hsrp.setHeader("Access-Control-Allow-Origin","1234");
//        hsrp.setHeader("Content-Type","application/x-www-form-urlencoded");
//        header = hsrp.getHeader("Access-Control-Allow-Origin");
//        MyLogger.myLoggerDebug("Access-Control-Allow-Origin:" + header);
//        MyLogger.loggerInfo("修改完成...");
//        header = hsrp.getHeader("Content-Type");
//        MyLogger.myLoggerDebug("Content-Type:" + header);
//

//        try {
//            MyLogger.loggerInfo("程序睡眠20s");
//            Thread.sleep(20000);
//            MyLogger.loggerInfo("程序激活...");
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        filterChain.doFilter(servletRequest,servletResponse);
//        try {
//            MyLogger.loggerInfo("程序睡眠20s");
//            Thread.sleep(20000);
//            MyLogger.loggerInfo("程序激活...");
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//        header = hsrp.getHeader("Access-Control-Allow-Origin");
//        MyLogger.myLoggerDebug("Access-Control-Allow-Origin:" + header);
//        header = hsrp.getHeader("Content-Type");
//        MyLogger.myLoggerDebug("Content-Type:" + header);
//
//        hsrp.setHeader("Access-Control-Allow-Origin","5678");
//        hsrp.setHeader("Content-Type","whh");
//        header = hsrp.getHeader("Access-Control-Allow-Origin");
//        MyLogger.myLoggerDebug("Access-Control-Allow-Origin:" + header);
//        header = hsrp.getHeader("Content-Type");
//        MyLogger.myLoggerDebug("Content-Type:" + header);

    }

    @Override
    public void destroy() {
        MyLogger.myLoggerDebug("FilterTest end...");
    }
}
