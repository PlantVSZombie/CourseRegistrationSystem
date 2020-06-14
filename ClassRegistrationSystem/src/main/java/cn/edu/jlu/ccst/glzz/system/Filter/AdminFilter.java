package cn.edu.jlu.ccst.glzz.system.Filter;

import cn.edu.jlu.ccst.glzz.system.Model.User;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/newpage/*","/student/*","/professor/*","/admin/*"}, filterName = "AdminFilter")
@Order(2)
public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("权限过滤器");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)servletRequest;
         //放行静态资源
        StringBuffer url=request.getRequestURL();
        if(url.indexOf("/css/")>=0||url.indexOf("/js/")>=0||url.indexOf("/images/")>=0||url.indexOf("/login")>=0||url.indexOf("/lib/")>=0||url.indexOf("404")>=0){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        //判断是否有响应权限
        User loginedUser= (User) request.getSession().getAttribute("user");
        if(loginedUser==null){
            System.out.println("权限拦截:"+request.getRequestURL());
            HttpServletResponse response=(HttpServletResponse) servletResponse;
            response.sendRedirect("/newpage/login.html");
            return;
        }
        switch (loginedUser.getUserType()){
            case Professor:
                if(url.indexOf("/newpage/professor")>0||url.indexOf("/professor/")>0){
                    filterChain.doFilter(servletRequest,servletResponse);
                    return;
                }
                break;
            case Student:
                if(url.indexOf("/newpage/student")>0||url.indexOf("/student/")>0){
                    filterChain.doFilter(servletRequest,servletResponse);
                    return;
                }
                break;
            case Admin:
                if(url.indexOf("/newpage/admin")>0||url.indexOf("/admin/")>0){
                    filterChain.doFilter(servletRequest,servletResponse);
                    return;
                }

        }
        if(url.indexOf("/user_info.json")>0){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        if(url.indexOf("/user-")>0){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        System.out.println("权限拦截:"+request.getRequestURL());
        HttpServletResponse response=(HttpServletResponse) servletResponse;
        response.sendRedirect("/newpage/404.html");
    }

    @Override
    public void destroy() {

    }
}
