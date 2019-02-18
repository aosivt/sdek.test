package supplier;

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
    public void checkCanceledOrderByOrderId(){
        assertSelectedCanceledOrderByOrderId();
        assertInsertCanceledOrder();
        assertDeleteCanceledOrder();
    }
    private void assertSelectedCanceledOrderByOrderId(){
        CanceledOrders canceledOrder = mapperCanceledOrder.getCanceledOrderByOrderId(1L);
        Assert.assertEquals(canceledOrder.getOrder().getOrderNum(),"test1");
    }
    private void assertInsertCanceledOrder(){
        Order order = mapperOrder.getById(5L);
        CanceledOrders canceledOrder = new CanceledOrders(order);
        mapperCanceledOrder.insertCanceledOrders(canceledOrder);
        session.commit();
        CanceledOrders checkedCanceledOrder = mapperCanceledOrder.getCanceledOrderByOrderId(5L);
        Assert.assertEquals(checkedCanceledOrder.getOrder().getOrderNum(),"test5");
    }
    private void assertDeleteCanceledOrder(){
        Order order = mapperOrder.getById(5L);
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
