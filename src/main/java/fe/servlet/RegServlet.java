package fe.servlet;

import be.business.UserBusinessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "registerServlet",urlPatterns = "/user/register")
public class RegServlet extends HttpServlet {

    @Autowired
    private UserBusinessService ubs;

    private static final Logger LOG = LoggerFactory.getLogger(RegServlet.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());

    }

    @Override
    protected void doPost( HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        String address = req.getParameter("address");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        ubs.registration(login, password, address);
        LOG.info("USER "+login+" CREATED.");
        resp.sendRedirect("/flowershop/loginPage.jsp");
    }
}