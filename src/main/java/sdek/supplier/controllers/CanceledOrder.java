package sdek.supplier.controllers;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import sdek.supplier.config.MyBatisConfig;
import sdek.supplier.mappers.FileFormatMapper;
import sdek.supplier.models.FileFormat;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Set;

@Controller
public class CanceledOrder {

        @RequestMapping(value = "/get/canceledOrders",method = RequestMethod.GET)
        public String getAllCanceledOrders() {

            return null;
        }
    @RequestMapping(value = "/fileFormatList",method = RequestMethod.GET)
    public String getTest(Model model) {
        SqlSessionFactory sessionFactory = MyBatisConfig.getSessionFactory();
        SqlSession session = sessionFactory.openSession();

        FileFormatMapper fileFormatMapper = session.getMapper(FileFormatMapper.class);
        Set<FileFormat> result = fileFormatMapper.getFileFormats();
        session.close();
        model.addAttribute("formats", result);
        return "fileFormatList";
    }


}
