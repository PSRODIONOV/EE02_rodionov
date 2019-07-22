package be.business;

import be.access.OrderDAO;
import be.entity.Flower;
import be.entity.Order;
import be.entity.OrderPosition;
import be.entity.User;
import be.utils.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<Order> getAllMyOrders(User user) {
        return orderDAO.getAllMyOrders(user);
    }

    @Override
    @Transactional
    public void payOrder(Long idOrder, Long idUser){
        Order order = orderDAO.getOrderById(idOrder);
        try {
            if (order.getStatus().equals("not paid")) {
                userBusinessService.pay(idUser, order.getTotalPrice());//**изменение баланса юзера
                order.setStatus("paid");        //**изменение статуса заказа
                orderDAO.updateOrder(order);    //**на оплачено
                for (OrderPosition orderPosition : order.getOrderPositions()) {   //**изменение кол-ва цветов на складе
                    Flower flower = orderPosition.getFlower();
                    flowerBusinessService.setQuantity(flower.getId_flower(), flower.getQuantity() - orderPosition.getQuantity());
                }
            }
        }
        catch (ServiceException se){

        }
    }

}
