package fe.webservice.restful;

import be.business.OrderBusinessService;
import be.business.UserBusinessService;
import be.utils.MapperService;
import be.utils.ServiceException;
import be.utils.annotation.Secured;
import fe.dto.OrderDto;
import fe.dto.UserDto;
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
    @Autowired
    private UserBusinessService userBusinessService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/user/{id}/orders")
    @Secured
    public List<OrderDto> getUserOrders(@PathParam("id") Long id){
        try {
            return mapper.mapList(orderBusinessService.getUserOrders(id), OrderDto.class);
        }
        catch (ServiceException e){
            return null;
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/user/orders")
    @Secured(onlyAdmin = true)
    public List<OrderDto> getAllOrders(){
        return mapper.mapList(orderBusinessService.getAllOrders(), OrderDto.class);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/user/{id}")
    @Secured(onlyAdmin = true)
    public UserDto getUserInfo(@PathParam("id") Long idUser){
        try {
            return mapper.map(userBusinessService.getUserById(idUser), UserDto.class);
        }
        catch (ServiceException e){
            return null;
        }
    }
}
