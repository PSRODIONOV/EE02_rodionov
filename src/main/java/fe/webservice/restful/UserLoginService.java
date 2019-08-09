package fe.webservice.restful;

import be.business.UserBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Component
@Path("/user")
public class UserLoginService {

    @Autowired
    private UserBusinessService userBusinessService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/login/{login}")
    public Boolean checkLogin(@PathParam("login") String login){
        return userBusinessService.checkLogin(login);
    }
}
