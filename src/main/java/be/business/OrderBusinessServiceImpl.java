package be.business;

import be.access.repositories.OrderRepository;
import be.entity.Flower;
import be.entity.Order;
import be.entity.OrderPosition;
import be.entity.User;
import be.utils.ServiceException;
import be.utils.annotation.Secured;
import be.utils.annotation.SessionId;
import be.utils.enums.OrderStatus;
import be.utils.enums.UserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderBusinessServiceImpl implements OrderBusinessService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserBusinessService userBusinessService;
    @Autowired
    private FlowerBusinessService flowerBusinessService;

    private static final Logger LOG = LoggerFactory.getLogger(OrderBusinessServiceImpl.class);

    public OrderBusinessServiceImpl() {

        LOG.info(":::::::::" + this.getClass() + " IS CREATED:::::::::");
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void addOrder(Order order) throws ServiceException {
        order.setStatus(OrderStatus.CREATED);
        if (order.getOrderPositions().isEmpty()) {
            throw new ServiceException(ServiceException.ERROR_BASKET);
        }
        try {
            orderRepository.saveAndFlush(order);
        } catch (Exception e) {
            throw new ServiceException(ServiceException.ERROR_INSERT);
        }

    }


    @Override
    @Secured
    public List<Order> getAllOrders(@SessionId User user) {
        if (user.getRole() == UserType.USER) {
            return orderRepository.getOrdersByUser(user);
        }
        return orderRepository.findAll(Sort.by("dateCreate").and(Sort.by("status")));
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void payOrder(Long idOrder, Long idUser) throws ServiceException {
        Order order = orderRepository.findById(idOrder)
                .orElseThrow(() -> new ServiceException(ServiceException.ERROR_FIND_ORDER));
        if (idUser == null) {
            throw new ServiceException(ServiceException.ERROR_INVALIDATE_DATA);
        }
        if (!order.getUser().getId().equals(idUser)) {
            throw new RuntimeException("Order not found for user");
        }

        if (order.getStatus() != OrderStatus.CREATED) {
            throw new ServiceException(ServiceException.ERROR_FIND_ORDER);
        }

        userBusinessService.pay(idUser, order.getTotalPrice());//**вычет стоимости покупки
        order.setStatus(OrderStatus.PAID);
        for (OrderPosition orderPosition : order.getOrderPositions()) {   //**изменение кол-ва цветов на складе
            Flower flower = orderPosition.getFlower();
            if (flower.getQuantity() < orderPosition.getQuantity()) {
                throw new ServiceException(ServiceException.ERROR_FLOWERSTOCK);
            }
            flower.setQuantity(flower.getQuantity() - orderPosition.getQuantity());
        }

    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void closeOrder(Long idOrder) throws ServiceException {
        Order order = orderRepository.findById(idOrder).
                orElseThrow(() -> new ServiceException(ServiceException.ERROR_FIND_ORDER));
        order.close();
    }
}
