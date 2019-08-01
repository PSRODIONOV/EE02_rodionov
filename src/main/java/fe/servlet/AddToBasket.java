package fe.servlet;

import be.business.FlowerBusinessService;
import be.business.OrderBusinessService;
import be.utils.Mapper;
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
import java.math.BigDecimal;

@WebServlet(urlPatterns = "/service/addToBasket")
public class AddToBasket extends HttpServlet {


    @Autowired
    private FlowerBusinessService flowerBusinessService;

    @Autowired
    private OrderBusinessService orderBusinessService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        try {
            HttpSession session = req.getSession(false);
            OrderDto orderDto = (OrderDto) session.getAttribute(SessionAttribute.BASKET.toString());

            Long idFlower = Long.parseLong(req.getParameter("idFlower"));
            Long quantity = Long.parseLong(req.getParameter("quantity"));
            if (quantity > flowerBusinessService.getFlowerById(idFlower).getQuantity()) {
                req.setAttribute("err", ServiceException.ERROR_FLOWERSTOCKSERVICE);
            }
            else {
                OrderPositionDto orderPositionDto = new OrderPositionDto();
                orderPositionDto.setQuantity(quantity);
                orderPositionDto.setFlowerDto(Mapper.map(flowerBusinessService.getFlowerById(idFlower)));

                BigDecimal totalPriceOP = orderPositionDto.getFlowerDto().getPrice().multiply(new BigDecimal(orderPositionDto.getQuantity()));
                orderPositionDto.setPrice(totalPriceOP);
                BigDecimal totalPriceWithDiscount = totalPriceOP.multiply(
                        new BigDecimal((100.0 - orderDto.getUserDto().getDiscount()) / 100.0));
                orderPositionDto.setPriceWithDiscount(totalPriceWithDiscount);

                orderDto = addOrderPosition(orderDto, orderPositionDto);
                BigDecimal totalPriceOrder = new BigDecimal(0);
                for(OrderPositionDto opd: orderDto.getOrderPositions()) {
                    totalPriceOrder = totalPriceOrder.add(opd.getPrice());
                }
                totalPriceOrder = totalPriceOrder.multiply(
                        new BigDecimal((100.0 - orderDto.getUserDto().getDiscount()) / 100.0));

                orderDto.setTotalPrice(totalPriceOrder.setScale(2, BigDecimal.ROUND_HALF_DOWN));

                session.setAttribute(SessionAttribute.BASKET.toString(), orderDto);
                req.setAttribute("err", "Item is added.");
            }
        }
        catch (NumberFormatException e){
            req.setAttribute("err", ServiceException.ERROR_FLOWERSTOCKSERVICE);
        }
        finally {
            req.getRequestDispatcher("/mainpage").forward(req, resp);
        }
    }

    public OrderDto addOrderPosition(OrderDto orderDto, OrderPositionDto newOrderPositionDto) {
        boolean isFind = false;
        for (OrderPositionDto orderPositionDto: orderDto.getOrderPositions()) {
            if(orderPositionDto.getFlowerDto().getIdFlower() == newOrderPositionDto.getFlowerDto().getIdFlower()){
                orderPositionDto.setQuantity(orderPositionDto.getQuantity() + newOrderPositionDto.getQuantity());
                orderPositionDto.setPrice(orderPositionDto.getPrice().add(newOrderPositionDto.getPrice()));
                orderPositionDto.setPriceWithDiscount(orderPositionDto.getPriceWithDiscount().add(newOrderPositionDto.getPriceWithDiscount()));
                isFind = true;
            }
        }
        if(!isFind) {
            orderDto.getOrderPositions().add(newOrderPositionDto);
        }

        return orderDto;
    }


}
