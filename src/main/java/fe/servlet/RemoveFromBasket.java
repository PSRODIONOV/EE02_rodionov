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
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/service/removeFromBasket")
public class RemoveFromBasket extends HttpServlet {


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        String id_flower = req.getParameter("idFlower");
        OrderDto orderDto = (OrderDto) session.getAttribute("order");
        orderDto.removeOrderPosition(Long.parseLong(id_flower));
        session.setAttribute("order", orderDto);
        req.getRequestDispatcher("/mainpage").forward(req, resp);
    }
}
