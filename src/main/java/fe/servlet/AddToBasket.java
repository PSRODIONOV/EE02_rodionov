package fe.servlet;

import be.access.FlowerDAO;
import be.business.FlowerBusinessService;
import fe.dto.*;
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

@WebServlet(urlPatterns = "/service/addToBasket")
public class AddToBasket extends HttpServlet {


    @Autowired
    private FlowerBusinessService flowerBusinessService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long idFlower = Long.parseLong(req.getParameter("id_flower"));
        String quantity = req.getParameter("quantity");
        OrderPositionDto orderPositionDto = new OrderPositionDto();
        orderPositionDto.setQuantity(Long.parseLong(quantity));
        orderPositionDto.setFlowerDto(Mapper.map(flowerBusinessService.getFlowerById(idFlower)));

        HttpSession session = req.getSession(false);
        OrderDto orderDto = (OrderDto)session.getAttribute("order");

        UserDto userDto = (UserDto)session.getAttribute("user");
        if(orderDto == null) {
            orderDto = new OrderDto();
        }

        orderPositionDto.setOrderDto(orderDto);

        orderDto.setUserDto(userDto);
        orderDto.addOrderPosition(orderPositionDto);
        session.setAttribute("order", orderDto);
    }
}
