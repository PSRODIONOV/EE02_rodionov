package fe.servlet;

import be.business.OrderBusinessService;
import be.utils.ServiceException;
import be.utils.enums.SessionAttribute;
import fe.dto.OrderDto;
import fe.dto.OrderPositionDto;
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
import java.util.Iterator;

@WebServlet(urlPatterns = "/service/removeFromBasket")
public class RemoveFromBasket extends HttpServlet {

    @Autowired
    private OrderBusinessService orderBusinessService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        String idFlower = req.getParameter("idFlower");
        OrderDto orderDto = (OrderDto) session.getAttribute(SessionAttribute.BASKET.toString());
        try {
            orderDto = delOrderPosition(orderDto, Long.parseLong(idFlower));
            session.setAttribute(SessionAttribute.BASKET.toString(), orderDto);
            req.setAttribute("bskt_msg", "Item is removed");
        } catch (Exception e) {
            req.setAttribute("bskt_err", ServiceException.ERROR_INVALIDATE_DATA);
        } finally {
            req.getRequestDispatcher("/service/mainpage").forward(req, resp);
        }
    }


    public OrderDto delOrderPosition(OrderDto orderDto, Long idFlower) throws ServiceException {

        if (idFlower == null) {
            throw new ServiceException(ServiceException.ERROR_INVALIDATE_DATA);
        }
        if (orderDto != null) {
            Iterator<OrderPositionDto> iter = orderDto.getOrderPositions().iterator();
            while (iter.hasNext()) {
                OrderPositionDto orderPositionDto = iter.next();

                if (orderPositionDto.getFlower().getIdFlower() == idFlower) {
                    iter.remove();
                    orderDto.computePrice();
                }
            }
            return orderDto;
        }
        return null;
    }
}
