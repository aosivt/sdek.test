package test.sdek.supplier;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sdek.supplier.config.MyBatisConfig;
import sdek.supplier.mappers.CanceledOrdersMapper;
import sdek.supplier.mappers.OrderMapper;
import sdek.supplier.models.CanceledOrders;
import sdek.supplier.models.Order;

public class TestCanceledOrderMapper {
    private SqlSession session;
    private CanceledOrdersMapper mapperCanceledOrder;
    private OrderMapper mapperOrder;

    @Before
    public void initSession(){
        SqlSessionFactory sessionFactory = MyBatisConfig.getSessionFactory();
        session = sessionFactory.openSession();
        mapperCanceledOrder = session.getMapper(CanceledOrdersMapper.class);
        mapperOrder = session.getMapper(OrderMapper.class);
    }
    @Test
    public void selectCanceledOrderByOrderId(){
        CanceledOrders canceledOrder = mapperCanceledOrder.getCanceledOrderByOrderId(1L);
        Assert.assertEquals(canceledOrder.getOrder().getOrderNum(),"test1");
    }
    @Test
    public void insertCanceledOrder(){

        Order order = mapperOrder.getOrderById(5L);
        CanceledOrders canceledOrder = new CanceledOrders(order);
        mapperCanceledOrder.insertCanceledOrders(canceledOrder);
        session.commit();
        CanceledOrders checkedCanceledOrder = mapperCanceledOrder.getCanceledOrderByOrderId(5L);
        Assert.assertEquals(checkedCanceledOrder.getOrder().getOrderNum(),"test5");
    }
    @Test
    public void deleteCanceledOrder(){

        Order order = mapperOrder.getOrderById(5L);
        CanceledOrders canceledOrder = new CanceledOrders(order);
        mapperCanceledOrder.deleteCanceledOrder(canceledOrder);
        session.commit();
        CanceledOrders checkedCanceledOrder = mapperCanceledOrder.getCanceledOrderByOrderId(5L);
        Assert.assertNull(checkedCanceledOrder);
    }
    @After
    public void stopSession(){
        session.close();
    }
}
