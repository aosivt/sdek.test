package sdek.supplier.controllers;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;
import sdek.supplier.config.MyBatisConfig;
import sdek.supplier.mappers.Dictionary;
import sdek.supplier.mappers.FileFormatMapper;
import sdek.supplier.models.FileFormat;
import sdek.supplier.models.Order;


@Controller
public class Supplier {



    @RequestMapping(value = "/supplier",method = RequestMethod.GET)
    public ModelAndView pageSupplier() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("order", new Order());
        modelAndView.setViewName("supplier");
        return modelAndView;
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
