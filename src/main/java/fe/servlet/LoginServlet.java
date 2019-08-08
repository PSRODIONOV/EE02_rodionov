package fe.servlet;

import be.business.FlowerBusinessService;
import be.business.OrderBusinessService;
import be.business.UserBusinessService;
import be.utils.ServiceException;
import be.utils.enums.SessionAttribute;
import fe.dto.UserDto;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
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

@WebServlet(name = "loginServlet", urlPatterns = "/user/login")
public class LoginServlet extends HttpServlet {

    @Autowired
    DozerBeanMapper mapper;
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        HttpSession session = req.getSession(true);
        try {
            UserDto currentUser = mapper.map(userBusinessService.login(login, password), UserDto.class);
            session.setAttribute(SessionAttribute.USER.toString(), currentUser);
            LOG.info("USER " + currentUser.getLogin() + " LOGGED IN.");
            req.getRequestDispatcher("/service/mainpage").forward(req, resp);
        } catch (ServiceException e) {
            req.setAttribute("err", e.getMessage());
            req.getRequestDispatcher("/loginPage.jsp").forward(req, resp);
        }

    }
}