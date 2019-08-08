package fe.servlet;

import be.business.OrderBusinessService;
import be.business.UserBusinessService;
import be.utils.ServiceException;
import be.utils.enums.SessionAttribute;
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

@WebServlet(urlPatterns = "/service/payorder")
public class PayOrder extends HttpServlet {

    @Autowired
    private OrderBusinessService orderBusinessService;
    @Autowired
    private UserBusinessService userBusinessService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        String idOrder = req.getParameter("idOrder");
        UserDto userDto = (UserDto) session.getAttribute(SessionAttribute.USER.toString());

        try {
            orderBusinessService.payOrder(Long.parseLong(idOrder), userDto.getId());
        } catch (ServiceException e) {
            req.setAttribute("err", e.getMessage());
        }
        req.getRequestDispatcher("/service/mainpage").forward(req, resp);
    }
}
