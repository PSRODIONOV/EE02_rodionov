package fe.servlet;

import be.utils.enums.SessionAttribute;
import fe.dto.UserDto;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/service/*")
public class FlowerShopFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpSession session = req.getSession(false);
        if(session != null) {
            UserDto user = (UserDto) session.getAttribute(SessionAttribute.USER.toString());
            if (user != null) {
                filterChain.doFilter(servletRequest, servletResponse);
            }
            else throw new ServletException("You shall not pass!");
        }
        else throw new ServletException("You shall not pass!");
    }

    @Override
    public void destroy(){

    }
}
