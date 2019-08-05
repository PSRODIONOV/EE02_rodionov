package fe.servlet;

import be.business.UserBusinessService;
import be.utils.MessageService;
import be.utils.ServiceException;
import be.utils.MarshallingServiceImpl;
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
    @Autowired
    private MarshallingServiceImpl userMarshallingService;
    @Autowired
    private MessageService messageService;

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
        try{
            ubs.registration(login, password, address);

            userMarshallingService.doMarshaling(login, ubs.getUserByLogin(login));
            messageService.sendUserXml(login);//Send xml of user file in OUT_QUEUE

            LOG.info("USER " + login + " CREATED.");
            resp.sendRedirect("/flowershop/loginPage.jsp");
        }
        catch(ServiceException e){
            req.setAttribute("err", e.getMessage());
            req.getRequestDispatcher("/registerPage.jsp").forward(req, resp);
        }

    }
}