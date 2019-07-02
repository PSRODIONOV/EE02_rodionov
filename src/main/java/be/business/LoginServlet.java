package be.business;

import be.entity.User;
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
    private UserBusinessServiceImpl ubs;

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
        if(ubs.login(login, password)) {
            User currentUser = (User) session.getAttribute("user");
            try {
                if (currentUser == null) {
                    currentUser = ubs.getCurrentUser();
                    session.setAttribute("user", currentUser);
                }
                else {
                    session.removeAttribute("user");
                    throw new ServletException("You shall not pass!");
                }

            }
            finally {

            }
        }
        else
            pw.println("<h1> Invalid login or password.</h1>");
        LOG.info("USER "+ session.getAttribute("user")+" LOGGED IN.");
        req.setAttribute("name", ubs.getCurrentUser().getLogin());
        req.setAttribute("address", ubs.getCurrentUser().getAddress());
        req.setAttribute("wallet_score", ubs.getCurrentUser().getWallet_score());
        req.setAttribute("discount", ubs.getCurrentUser().getDiscount());
        req.getRequestDispatcher("/mainPage.jsp").forward(req, resp);

    }
}