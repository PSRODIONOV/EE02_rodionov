package fe.webservice.restful;

import be.business.OrderBusinessService;
import be.entity.Order;
import be.utils.MapperService;
import be.utils.ServiceException;
import be.utils.annotation.Secured;
import fe.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Component
@Path("/order")
public class OrderController {

    @Autowired
    MapperService mapper;
    @Autowired
    OrderBusinessService orderBusinessService;

    public OrderController(){}

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/user/orders")
    @Secured(onlyAdmin = true)
    public List<OrderDto> getAllOrders(){
        return mapper.mapList(orderBusinessService.getAllOrders(), OrderDto.class);
    }

    @GET
    @Path("/pay/{id}")
    @Secured
    public String payOrder(@PathParam("id") Long id){
        try{
            orderBusinessService.payOrder(id);
        }
        catch (ServiceException e){
            return e.getMessage();
        }
        return "Order is payed";
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Secured
    public OrderDto getOrderById(@PathParam("id") Long id){
        try{
            return mapper.map(orderBusinessService.getOrderById(id), OrderDto.class);
        }
        catch (ServiceException e){
            return null;
        }
    }

    @GET
    @Path("/close/{id}")
    @Secured(onlyAdmin = true)
    public String closeOrder(@PathParam("id") Long id){
        try {
            orderBusinessService.closeOrder(id);
        }
        catch (ServiceException e){
            return e.getMessage();
        }
        return "Order is closed";
    }

    @POST
    @Path("/create")
    @Secured
    public String createOrder(OrderDto orderDto){
        try{
            orderBusinessService.addOrder(mapper.map(orderDto, Order.class));
        }
        catch (ServiceException e){
            return e.getMessage();
        }
        return "Order is created";
    }

}
