package fe.servlet;

import fe.dto.OrderDto;
import fe.dto.OrderPositionDto;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/service/addToBasket")
public class AddToBasket extends HttpServlet {


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id_flower = req.getParameter("button");
        String quantity = req.getParameter("quantity"+id_flower);
        OrderPositionDto orderPositionDto = new OrderPositionDto();
        orderPositionDto.setQuantity(Long.parseLong(quantity));
        orderPositionDto.setId_flower(Long.parseLong(id_flower));
        OrderDto orderDto = (OrderDto)req.getAttribute("order");
        if(orderDto == null) {
            orderDto = new OrderDto();
        }
        orderDto.getOrderPositions().add(orderPositionDto);
        req.setAttribute("order", orderDto);
        //req.getRequestDispatcher("").forward(req, resp);
    }
}
