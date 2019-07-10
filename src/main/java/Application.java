import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application{

    public static void main(String[] args){
        //SpringApplication.run(Application.class, args);
        ApplicationContext context = new AnnotationConfigApplicationContext("config/application-context-xml");

    }

}
