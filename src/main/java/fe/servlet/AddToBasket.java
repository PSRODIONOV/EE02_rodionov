package fe.servlet;

import be.business.FlowerBusinessService;
import be.utils.ServiceException;
import be.utils.enums.SessionAttribute;
import fe.dto.FlowerDto;
import fe.dto.OrderDto;
import fe.dto.OrderPositionDto;
import org.dozer.Mapper;
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
    Mapper mapper;
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


        HttpSession session = req.getSession(false);
        OrderDto orderDto = (OrderDto) session.getAttribute(SessionAttribute.BASKET.toString());
        String idFlower = req.getParameter("idFlower");
        String quantity = req.getParameter("quantity");
        try {

            orderDto = addOrderPosition(orderDto, Long.parseLong(idFlower), Long.parseLong(quantity));
            /*Считаем общую стоимость корзины с учетом скидки*/
            orderDto.computePrice();

            session.setAttribute(SessionAttribute.BASKET.toString(), orderDto);
            req.setAttribute("err", "Item is added.");

        }catch (NumberFormatException e){
            req.setAttribute("err", ServiceException.ERROR_INVALIDATE_DATA);
        }
        catch (ServiceException e) {
            req.setAttribute("err", e.getMessage());
        } finally {
            req.getRequestDispatcher("/service/mainpage").forward(req, resp);
        }
    }

    public OrderDto addOrderPosition(OrderDto orderDto, Long idFlower, Long quantity) throws ServiceException {
        if (quantity > flowerBusinessService.getFlowerById(idFlower).getQuantity()) {
            throw new ServiceException(ServiceException.ERROR_FLOWERSTOCK);
        }
        for (OrderPositionDto orderPositionDto : orderDto.getOrderPositions()) {
            /*Если позиция уже существует, то увеличиваем её параметры*/
            if (idFlower.equals(orderPositionDto.getFlower().getIdFlower())) {
                Long sumQty = orderPositionDto.getQuantity() + quantity;
                if (sumQty > orderPositionDto.getFlower().getQuantity()) {
                    throw new ServiceException(ServiceException.ERROR_FLOWERSTOCK);
                }
                orderPositionDto.setOrder(orderDto);
                orderPositionDto.setQuantity(sumQty);
                orderPositionDto.computePrice();
                return orderDto;
            }
        }
        /*Если похожей позиции не было, то добавляем её*/
       OrderPositionDto newOrderPositionDto = new OrderPositionDto(
                orderDto, mapper.map(flowerBusinessService.getFlowerById(idFlower), FlowerDto.class), quantity);
        orderDto.getOrderPositions().add(newOrderPositionDto);

        return orderDto;
    }

}
