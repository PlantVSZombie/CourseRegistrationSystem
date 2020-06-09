package cn.edu.jlu.ccst.glzz.system.Filter;



import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//c d是为了指定过滤器顺序
@WebFilter(urlPatterns = {"/*"}, filterName = "loginFilter")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)servletRequest;
//        //放行静态资源
//        StringBuffer url=request.getRequestURL();
//        if(url.indexOf("layui")>=0||url.indexOf("jquery")>=0){
//            filterChain.doFilter(servletRequest,servletResponse);
//        }
//        //判断是否登陆
//        User loginedUser= (User) request.getSession().getAttribute("user");
//        System.out.println("拦截:"+request.getRequestURL());
//        if (loginedUser!=null){
            filterChain.doFilter(servletRequest,servletResponse);
//        }else {
//            HttpServletResponse response=(HttpServletResponse) servletResponse;
//            response.sendRedirect("/login?result=nologin");
//        }
    }

    @Override
    public void destroy() {

    }
}
