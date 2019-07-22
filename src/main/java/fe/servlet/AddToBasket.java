package fe.servlet;

import be.business.FlowerBusinessService;
import be.utils.ServiceException;
import fe.dto.Mapper;
import fe.dto.OrderDto;
import fe.dto.OrderPositionDto;
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        Long idFlower = Long.parseLong(req.getParameter("id_flower"));
        Long quantity = Long.parseLong(req.getParameter("quantity"));
        if(quantity > flowerBusinessService.getFlowerById(idFlower).getQuantity()){
            PrintWriter pw = resp.getWriter();
            pw.println("<p> Invalid value for 'quantity'! </p>");
            return;
        }
        OrderPositionDto orderPositionDto = new OrderPositionDto();
        orderPositionDto.setQuantity(quantity);
        orderPositionDto.setFlowerDto(Mapper.map(flowerBusinessService.getFlowerById(idFlower)));
        orderPositionDto.setPrice(orderPositionDto.getFlowerDto().getPrice()*orderPositionDto.getQuantity());

        HttpSession session = req.getSession(false);
        OrderDto orderDto = (OrderDto)session.getAttribute("order");

        UserDto userDto = (UserDto)session.getAttribute("user");
        if(orderDto == null) {
            orderDto = new OrderDto();
        }

        orderDto.setUserDto(userDto);
        orderDto.addOrderPosition(orderPositionDto);
        Double totalPrice = ((100.0 - orderDto.getUserDto().getDiscount())/100.0)*orderDto.getTotalPrice();
        orderDto.setTotalPrice(totalPrice);
        session.setAttribute("order", orderDto);
        req.getRequestDispatcher("/mainpage").forward(req, resp);
    }
}
