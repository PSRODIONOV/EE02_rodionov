package be.utils.aspects;

import be.access.repositories.UserRepository;
import be.utils.annotation.Secured;
import be.utils.annotation.SessionId;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class SecurityAspect {
    @Autowired
    private UserRepository userRepository;

    @Before("@annotation(be.utils.annotation.Secured) && @annotation(securedAnnotation)")
    public void secure(final JoinPoint jp, Secured securedAnnotation) throws Throwable{
        MethodSignature methodSignature = (MethodSignature) jp.getSignature();
        Class<?> clazz = methodSignature.getDeclaringType();
        Method method = clazz.getDeclaredMethod(methodSignature.getName(), methodSignature.getParameterTypes());
        String sessionId = null;
        for(int i = 0; i < method.getParameterCount(); i++){
            if(method.getParameters()[i].getAnnotation(SessionId.class) != null){
                sessionId = (String)jp.getArgs()[i];
                break;
            }
        }

    }
}
