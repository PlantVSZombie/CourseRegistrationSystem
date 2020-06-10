package cn.edu.jlu.ccst.glzz.system.Filter;



import cn.edu.jlu.ccst.glzz.system.Model.User;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter(urlPatterns = {"/*"}, filterName = "loginFilter")
@Order(1)
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("登陆过滤器");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)servletRequest;
//        //放行静态资源
        StringBuffer url=request.getRequestURL();
        if(url.indexOf("/css/")>=0||url.indexOf("/js/")>=0||url.indexOf("/images/")>=0||url.indexOf("/lib/")>=0||url.indexOf("404")>=0){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
//        //判断是否登陆
        HttpServletResponse response=(HttpServletResponse) servletResponse;
        User loginedUser= (User) request.getSession().getAttribute("user");
        if (loginedUser!=null){
            if (url.indexOf("login.html")>=0){
                System.out.println("已登录，页面重定向");
                response.sendRedirect("/index.html");
                return;
            }
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            if (url.indexOf("login")>=0){
                filterChain.doFilter(servletRequest,servletResponse);
                return;
            }
            System.out.println("拦截:"+request.getRequestURL());
            response.sendRedirect("/newpage/login.html");
        }
    }

    @Override
    public void destroy() {

    }
}
