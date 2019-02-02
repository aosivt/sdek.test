package sdek.supplier.controllers;

import java.util.Objects;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import sdek.supplier.config.MyBatisConfig;

import sdek.supplier.mappers.CanceledOrdersMapper;
import sdek.supplier.mappers.OrderMapper;

import sdek.supplier.models.CanceledOrders;
import sdek.supplier.models.Order;



import sdek.supplier.exceptions.OrderNullPointerException;

@Controller
public class Supplier {

    @RequestMapping(value = "/supplier",method = RequestMethod.GET)
    public ModelAndView pageSupplier() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("order", new Order());
        modelAndView.setViewName("supplier");
        return modelAndView;
    }

    @MessageMapping("/canceledOrder")
    @SendTo("/topic/greetings")
    public CanceledOrders greeting(Order checkOrder) throws Exception {

        SqlSessionFactory sessionFactory = MyBatisConfig.getSessionFactory();
        SqlSession session = sessionFactory.openSession();
        OrderMapper orderMapper = session.getMapper(OrderMapper.class);
        Order order = orderMapper.getOrderByOrderNum(checkOrder.getOrderNum());
        if (Objects.isNull(order)){
            throw new OrderNullPointerException();
        }

        CanceledOrders canceledOrder = new CanceledOrders(order);
        CanceledOrdersMapper canceledOrdersMapper = session.getMapper(CanceledOrdersMapper.class);
        canceledOrdersMapper.insertCanceledOrders(canceledOrder);
        session.commit();
        session.close();
        return canceledOrder;
    }

}
