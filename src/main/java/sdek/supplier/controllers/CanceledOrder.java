package sdek.supplier.controllers;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.web.bind.annotation.*;
import sdek.supplier.config.MyBatisConfig;
import sdek.supplier.mappers.FileFormatMapper;
import sdek.supplier.models.FileFormat;

import java.util.Optional;
import java.util.Set;

@RestController
public class CanceledOrder {

        @RequestMapping(value = "/get/canceledOrders",method = RequestMethod.GET)
//        @JsonView(View.Summary.class)
        public String getCatalogSatelliteImageById() {
            SqlSessionFactory sessionFactory = MyBatisConfig.getSessionFactory();
            SqlSession session = sessionFactory.openSession();

            FileFormatMapper fileFormatMapper = session.getMapper(FileFormatMapper.class);
            String result = fileFormatMapper.getFileFormats().
            parallelStream().map(FileFormat::getTitle).reduce(String::concat).orElse("default");
            session.close();
            return result;
        }
}
