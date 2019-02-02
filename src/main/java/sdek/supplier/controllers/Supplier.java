package sdek.supplier.controllers;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;
import sdek.supplier.config.MyBatisConfig;
import sdek.supplier.mappers.FileFormatMapper;
import sdek.supplier.models.CanceledOrders;
import sdek.supplier.models.FileFormat;
import sdek.supplier.models.Order;
import sdek.supplier.test.Greeting;


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
    public CanceledOrders greeting(Order order) throws Exception {
        Thread.sleep(1000); // simulated delay
//        return new Greeting("Hello, " + HtmlUtils.htmlEscape(order.getOrderNum()+ "!"));
        return new CanceledOrders(order);
    }


    @RequestMapping(value = "/add/FileFormat",method = RequestMethod.POST)
    public String addFileFormat(@ModelAttribute("order") Order order, Model model) {
        SqlSessionFactory sessionFactory = MyBatisConfig.getSessionFactory();
        SqlSession session = sessionFactory.openSession();

        FileFormatMapper fileFormatMapper = session.getMapper(FileFormatMapper.class);
        FileFormat fileFormat = new FileFormat();
        fileFormat.setShortName("shortName");
        fileFormat.setTitle("title");
        fileFormatMapper.insertFileFormat(fileFormat);
        session.commit();
        session.close();
        return "supplier";
    }



}
