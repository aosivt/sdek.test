package sdek.supplier.controllers;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Controller;

import sdek.supplier.config.MyBatisConfig;
import sdek.supplier.mappers.CanceledOrdersMapper;
import sdek.supplier.mappers.FileFormatMapper;
import sdek.supplier.models.CanceledOrders;
import sdek.supplier.models.FileFormat;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sdek.supplier.models.Order;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class CanceledOrder {

        @RequestMapping(value = "/canceledOrders",method = RequestMethod.GET)
        public String getAllCanceledOrders(Model model) {
            SqlSessionFactory sessionFactory = MyBatisConfig.getSessionFactory();
            SqlSession session = sessionFactory.openSession();

            CanceledOrdersMapper canceledOrdersMapper = session.getMapper(CanceledOrdersMapper.class);
            Set<CanceledOrders> canceledOrders = canceledOrdersMapper.getCanceledOrders();
            session.close();
//            Set<CanceledOrders> canceledOrders = getTestCollection();
//            model.addAttribute("canceledOrders", canceledOrders);
            return "canceledOrders";
        }
//    @RequestMapping(value = "/fileFormatList",method = RequestMethod.GET)
//    public String getTest(Model model) {
//        SqlSessionFactory sessionFactory = MyBatisConfig.getSessionFactory();
//        SqlSession session = sessionFactory.openSession();
//
//        FileFormatMapper fileFormatMapper = session.getMapper(FileFormatMapper.class);
//        Set<FileFormat> result = fileFormatMapper.getFileFormats();
//        session.close();
//        model.addAttribute("formats", result);
//        return "fileFormatList";
//    }


//    private Set<CanceledOrders> getTestCollection(){
//        return IntStream.range(0,10).boxed().
//                map(i->new Order(new Long(i), String.valueOf(Math.random()))).
//                map(CanceledOrders::new).
//                collect(Collectors.toSet());
//    }
}
