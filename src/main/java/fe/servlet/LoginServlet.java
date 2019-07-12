package fe.servlet;

import be.business.FlowerBusinessService;
import be.business.UserBusinessService;
import fe.dto.Mapper;
import fe.dto.OrderDto;
import fe.dto.UserDto;
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
import java.io.PrintWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "loginServlet",urlPatterns = "/user/login")
public class LoginServlet extends HttpServlet {

    @Autowired
    private UserBusinessService ubs;
    @Autowired
    private FlowerBusinessService fbs;

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
        PrintWriter pw = resp.getWriter();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        HttpSession session = req.getSession(true);
        UserDto currentUser;
        if((currentUser = Mapper.map(ubs.login(login, password))) != null) {
            session.setAttribute("user", currentUser);
        }
        else {
            throw new ServletException("You shall not pass!");
        }
        LOG.info("USER "+ session.getAttribute("user") + " LOGGED IN.");
        req.setAttribute("name", currentUser.getLogin());
        req.setAttribute("address", currentUser.getAddress());
        req.setAttribute("wallet_score", currentUser.getWallet_score());
        req.setAttribute("discount", currentUser.getDiscount());
        req.setAttribute("flowers", fbs.getAllFlowers());
        req.getRequestDispatcher("/mainPage.jsp").forward(req, resp);
    }
}