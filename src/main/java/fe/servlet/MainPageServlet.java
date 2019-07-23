package fe.servlet;

import be.business.FlowerBusinessService;
import be.business.OrderBusinessService;
import be.business.UserBusinessService;
import be.utils.FlowerFilter;
import fe.dto.FlowerDto;
import fe.dto.Mapper;
import fe.dto.OrderDto;
import fe.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/mainpage")
public class MainPageServlet extends HttpServlet {

    @Autowired
    private UserBusinessService userBusinessService;
    @Autowired
    private FlowerBusinessService flowerBusinessService;
    @Autowired
    private OrderBusinessService orderBusinessService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        UserDto userDto = (UserDto)session.getAttribute("user");

        userDto = Mapper.map(userBusinessService.getUserById(userDto.getId()));
        session.setAttribute("user", userDto);

        List<OrderDto> ordersDto = Mapper.mapOrders(orderBusinessService.getAllOrders(Mapper.map(userDto)));
        req.setAttribute("orders", ordersDto);

        FlowerFilter filter = (FlowerFilter) req.getAttribute("filter");
        List<FlowerDto> flowersDto;
        if(filter != null){
            flowersDto = Mapper.mapFlowers(flowerBusinessService.searchFilter(filter));
        }
        else {
            flowersDto = Mapper.mapFlowers(flowerBusinessService.getAllFlowers());
        }
        req.setAttribute("flowers", flowersDto);

        if(userDto.getRole().equals("user")) {
            req.getRequestDispatcher("/mainPage.jsp").forward(req, resp);
        }
        else{
            req.getRequestDispatcher("/adminMainPage.jsp").forward(req, resp);
        }
    }
}
