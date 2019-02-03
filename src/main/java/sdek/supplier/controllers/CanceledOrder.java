package sdek.supplier.controllers;

import java.util.Set;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import sdek.supplier.config.MyBatisConfig;
import sdek.supplier.mappers.CanceledOrdersMapper;
import sdek.supplier.models.CanceledOrders;

@Controller
public class CanceledOrder {

        @RequestMapping(value = "/canceledOrders",method = RequestMethod.GET)
        public String getAllCanceledOrders(Model model) {
            SqlSessionFactory sessionFactory = MyBatisConfig.getSessionFactory();
            SqlSession session = sessionFactory.openSession();

            CanceledOrdersMapper canceledOrdersMapper = session.getMapper(CanceledOrdersMapper.class);
            Set<CanceledOrders> canceledOrders = canceledOrdersMapper.getCanceledOrders();
            session.close();
            model.addAttribute("canceledOrders", canceledOrders);
            return "canceledOrders";
        }

}
