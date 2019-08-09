package fe.servlet;

import be.business.OrderBusinessService;
import be.business.UserBusinessService;
import be.entity.Order;
import be.utils.MapperService;
import be.utils.ServiceException;
import be.utils.enums.SessionAttribute;
import fe.dto.OrderDto;
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

@WebServlet(urlPatterns = "/service/createOrder")
public class CreateOrder extends HttpServlet {

    @Autowired
    MapperService mapper;
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
        OrderDto orderDto = (OrderDto) session.getAttribute(SessionAttribute.BASKET.toString());
        try {
            orderBusinessService.addOrder(mapper.map(orderDto, Order.class));
            session.removeAttribute(SessionAttribute.BASKET.toString());
        }
        catch(ServiceException e) {
            req.setAttribute("err", e.getMessage());
        }
        finally{
            req.getRequestDispatcher("/service/mainpage").forward(req, resp);
        }
    }
}
