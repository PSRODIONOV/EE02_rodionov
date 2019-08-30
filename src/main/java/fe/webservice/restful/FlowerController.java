package fe.webservice.restful;

import be.business.FlowerBusinessService;
import be.utils.FlowerFilter;
import be.utils.MapperService;
import be.utils.ServiceException;
import fe.dto.FlowerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Component
@Path("/flower")
public class FlowerController {

    @Autowired
    MapperService mapper;
    @Autowired
    FlowerBusinessService flowerBusinessService;

    public FlowerController(){}

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<FlowerDto> getAllFlowers(){
        return mapper.mapList(flowerBusinessService.getAllFlowers(), FlowerDto.class);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public FlowerDto getFlower(@PathParam("id") Long id){
        try{
            return mapper.map(flowerBusinessService.getFlowerById(id), FlowerDto.class);
        }
        catch (ServiceException e){
            return null;
        }
    }

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public List<FlowerDto> getFlowerByFilter(@QueryParam("name") String name, @QueryParam("minPrice") String minPrice, @QueryParam("maxPrice") String maxPrice){
        return mapper.mapList(flowerBusinessService.searchFilter(new FlowerFilter(minPrice, maxPrice, name)), FlowerDto.class);
    }

}
