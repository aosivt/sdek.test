package sdek.supplier.controllers;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sdek.supplier.config.MyBatisConfig;
import sdek.supplier.mappers.CanceledOrdersMapper;
import sdek.supplier.models.CanceledOrders;

import java.util.Set;

@RestController
public class RestCancaledOrder {
    /**
     * метод возращает удаленную сущность формата файла
     * @return CatalogHiveSQLSatelliteBands object hibernate объект соответствующей сущности
     * */
    @RequestMapping(value = "/getCanceledOrders",method = RequestMethod.GET)
    public Set<CanceledOrders> getCanceledOrders() {

        SqlSessionFactory sessionFactory = MyBatisConfig.getSessionFactory();
        SqlSession session = sessionFactory.openSession();

        CanceledOrdersMapper canceledOrdersMapper = session.getMapper(CanceledOrdersMapper.class);
        Set<CanceledOrders> canceledOrders = canceledOrdersMapper.getCanceledOrders();
        session.close();

        return canceledOrders;
    }
}
