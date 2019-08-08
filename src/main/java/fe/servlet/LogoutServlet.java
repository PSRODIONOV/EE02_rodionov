package fe.servlet;

import be.utils.enums.SessionAttribute;
import fe.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/user/logout")
public class LogoutServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(LogoutServlet.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        LOG.info("USER " + ((UserDto) session.getAttribute(SessionAttribute.USER.toString())).getLogin() + " LOGGED OUT");
        session.invalidate();
        resp.sendRedirect("/flowershop/loginPage.jsp");
    }
}
