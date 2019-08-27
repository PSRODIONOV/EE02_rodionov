package be.utils.aspects;

import be.access.repositories.UserRepository;
import be.utils.ServiceException;
import be.utils.annotation.Secured;
import be.utils.enums.SessionAttribute;
import be.utils.enums.UserType;
import fe.dto.UserDto;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Aspect
@Component
public class SecurityAspect {
    @Autowired
    private UserRepository userRepository;

    @Before("@annotation(be.utils.annotation.Secured) && @annotation(securedAnnotation)")
    public void secure(final JoinPoint jp, Secured securedAnnotation) throws Throwable{

        RequestAttributes requestAttributes = RequestContextHolder
                .currentRequestAttributes();
        ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = attributes.getRequest();
        HttpSession session = request.getSession(false);
        Optional<UserDto> userO = Optional.ofNullable((UserDto)session.getAttribute(SessionAttribute.USER.toString()));
        if(userO.isPresent()){
            UserDto userDto = userO.get();
            if(securedAnnotation.onlyAdmin() && userDto.getRole() != UserType.ADMIN){
                throw new ServiceException("Access error 1.");
            }
            if(userDto.getRole() != UserType.ADMIN && !userDto.getIdUser().equals(jp.getArgs()[0])){
                throw new ServiceException("Access error 2.");
            }
        }
        else {
            throw new ServiceException("Access error 3.");
        }
    }
}
