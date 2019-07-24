package be.business;

import be.access.OrderDAO;
import be.entity.Flower;
import be.entity.Order;
import be.entity.OrderPosition;
import be.entity.User;
import be.utils.ServiceException;
import be.utils.enums.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Service
public class OrderBusinessServiceImpl implements OrderBusinessService {

    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private UserBusinessService userBusinessService;
    @Autowired
    private FlowerBusinessService flowerBusinessService;

    private static final Logger LOG = LoggerFactory.getLogger(OrderBusinessServiceImpl.class);

    public OrderBusinessServiceImpl() {

        LOG.info(":::::::::"+this.getClass()+" IS CREATED:::::::::");
    }

    @Override
    public void addOrder(Order order) {
        order.setDateCreate(new Date(Calendar.getInstance().getTime().getTime()));
        order.setStatus(OrderStatus.CREATED);
        orderDAO.addOrder(order);
    }

    @Override
    public void updateOrder(Order order) {
        orderDAO.updateOrder(order);
    }

    @Override
    public Order getOrderById(Long id) {

        return orderDAO.getOrderById(id);
    }

    @Override
    public void delOrderById(Long id) {
        orderDAO.delOrderById(id);
    }

    @Override
    public List<Order> getAllOrders(User user) {
        if(user.getRole().equals("user")){
            return orderDAO.getAllMyOrders(user.getIdUser());
        }
        return orderDAO.getAllOrders();
    }

    @Override
    @Transactional
    public void payOrder(Long idOrder, Long idUser){
        Order order = orderDAO.getOrderById(idOrder);
        try {
            if (order.getStatus().equals("not paid")) {
                if(order.getUser().getIdUser() == idUser) {
                    userBusinessService.pay(idUser, order.getTotalPrice());//**вычет стоимости покупки
                }
                order.setStatus(OrderStatus.PAID);
                orderDAO.updateStatus(order.getIdOrder(), "paid");//**изменение статуса заказа на оплачено
                for (OrderPosition orderPosition : order.getOrderPositions()) {   //**изменение кол-ва цветов на складе
                    Flower flower = orderPosition.getFlower();
                    flowerBusinessService.setQuantity(flower.getIdFlower(), flower.getQuantity() - orderPosition.getQuantity());
                }
            }
        }
        catch (ServiceException se){

        }
    }

    @Override
    public void closeOrder(Long idOrder) {
        Order order = orderDAO.getOrderById(idOrder);
        order.setStatus(OrderStatus.CLOSED);
        order.setDateClose(new Date(Calendar.getInstance().getTime().getTime()));
        orderDAO.updateOrder(order);
    }
}
