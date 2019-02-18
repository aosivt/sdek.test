package sdek.supplier.controllers;

import java.util.Objects;
import java.util.logging.Logger;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import sdek.supplier.config.MyBatisConfig;

import sdek.supplier.mappers.CanceledOrdersMapper;
import sdek.supplier.mappers.OrderMapper;

import sdek.supplier.models.CanceledOrders;
import sdek.supplier.models.Order;


import sdek.supplier.utils.Actions;
import sdek.supplier.utils.RequestSocket;
import sdek.supplier.utils.ResponseSocket;
import sdek.supplier.utils.ResponseSocketBuilder;

@Controller
public class Supplier {

    @MessageMapping("/canceledOrder")
    @SendTo("/topic/canceled")
    public ResponseSocket canceledOrder(RequestSocket requestSocket) throws Exception {
        return ResponseSocketBuilder.build(requestSocket);
    }

}
