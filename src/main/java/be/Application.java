package be;
import be.business.OrderBusinessService;
import be.business.UserBusinessService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//@SpringBootApplication( scanBasePackages = "be")
public class Application /*extends SpringBootServletInitializer */{

    public static void main(String[] args){
        //SpringApplication.run(Application.class, args);
        //ApplicationContext context = new AnnotationConfigApplicationContext("/config/application-context-xml");
        ApplicationContext context = new ClassPathXmlApplicationContext("config/application-context.xml");
        //UserBusinessService ubs = (UserBusinessService)context.getBean("UserBusinessService");

    }

}
