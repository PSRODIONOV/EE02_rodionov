package fe.webservice.restful;

import be.business.OrderBusinessService;
import be.utils.MapperService;
import be.utils.ServiceException;
import be.utils.annotation.Secured;
import fe.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Component
@Path("/test")
public class MyController {


    @Autowired
    MapperService mapper;
    @Autowired
    private OrderBusinessService orderBusinessService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/user/{id}/orders")
    @Secured
    public List<OrderDto> getUserOrders(@PathParam("id") Long id){
        return mapper.mapList(orderBusinessService.getUserOrders(id), OrderDto.class);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/user/orders")
    @Secured(onlyAdmin = true)
    public List<OrderDto> getAllOrders(){
        return mapper.mapList(orderBusinessService.getAllOrders(), OrderDto.class);
    }
}
