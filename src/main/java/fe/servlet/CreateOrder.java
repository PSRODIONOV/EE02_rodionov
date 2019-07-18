package fe.servlet;

import be.business.OrderBusinessService;
import be.business.UserBusinessService;
import be.entity.Order;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/service/createOrder")
public class CreateOrder extends HttpServlet {

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
        OrderDto orderDto = (OrderDto) session.getAttribute("order");
        UserDto currentUser = (UserDto) session.getAttribute("user");
        orderBusinessService.addOrder(Mapper.map(orderDto));
        List<OrderDto> list = Mapper.mapOrders(orderBusinessService.getAllMyOrders(Mapper.map(currentUser)));
        list.add(orderDto);
        orderDto.setStatus("not paid");
        req.setAttribute("orders", Mapper.mapOrders(orderBusinessService.getAllMyOrders(Mapper.map(currentUser))).add(orderDto));
        session.removeAttribute("order");
    }
}
