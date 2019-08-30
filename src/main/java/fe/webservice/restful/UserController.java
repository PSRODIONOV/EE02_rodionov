package fe.webservice.restful;

import be.business.UserBusinessService;
import be.entity.User;
import be.utils.CustomSessionFactory;
import be.utils.MapperService;
import be.utils.ServiceException;
import be.utils.enums.SessionAttribute;
import fe.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Component
@Path("/user")
public class UserController {

    @Autowired
    MapperService mapper;
    @Autowired
    UserBusinessService userBusinessService;

    public UserController(){}

    @POST
    @Path("/login")
    public String login(User user){
        try {
            UserDto userDto = mapper.map(userBusinessService.login(user.getLogin(), user.getPassword()), UserDto.class);
            HttpSession session = CustomSessionFactory.getSession(true);
            session.setAttribute(SessionAttribute.USER.toString(), userDto);
        }
        catch (ServiceException e){
            return e.getMessage();
        }
        return "You logged.";
    }

    @POST
    @Path("/register")
    public String register(User user){
        try{
            userBusinessService.registration(user);
        }
        catch (ServiceException e){
            return e.getMessage();
        }
        return "User is registered.";
    }

    @GET
    @Path("/logout")
    public String logout(){
        HttpSession session = CustomSessionFactory.getSession(false);
        session.invalidate();
        return "Session clearer";
    }

}
