package fe.servlet;

import be.business.FlowerBusinessService;
import be.business.OrderBusinessService;
import be.business.UserBusinessService;
import fe.dto.Mapper;
import fe.dto.UserDto;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "loginServlet",urlPatterns = "/user/login")
public class LoginServlet extends HttpServlet {

    @Autowired
    private UserBusinessService userBusinessService;
    @Autowired
    private FlowerBusinessService flowerBusinessService;
    @Autowired
    private OrderBusinessService orderBusinessService;

    private static final Logger LOG = LoggerFactory.getLogger(LoginServlet.class);

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
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        HttpSession session = req.getSession(true);
        UserDto currentUser;
        if((currentUser = Mapper.map(userBusinessService.login(login, password))) != null) {
            session.setAttribute("user", currentUser);
            LOG.info("USER "+ currentUser.getLogin() + " LOGGED IN.");
            req.getRequestDispatcher("/mainpage").forward(req, resp);
        }
        else {
            req.setAttribute("err", "Invalid login or password.");
            req.getRequestDispatcher("/loginPage.jsp").forward(req, resp);
        }

    }
}