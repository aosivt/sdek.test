package sdek.supplier.controllers;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sdek.supplier.config.MyBatisConfig;
import sdek.supplier.mappers.CanceledOrdersMapper;
import sdek.supplier.models.CanceledOrders;
import sdek.supplier.models.Dictionary;
import sdek.supplier.utils.MyBatisUtils;

import java.util.Set;

@RestController
public class RestCancaledOrder {
    /**
     * method for get canceled orders
     * @return Set<CanceledOrders> object mapper
     * */
    @RequestMapping(value = "/getCanceledOrders",method = {RequestMethod.GET, RequestMethod.POST})
    public Set<Dictionary> getCanceledOrders() {
        return MyBatisUtils.getDataMappers(CanceledOrdersMapper.class);
    }
}
