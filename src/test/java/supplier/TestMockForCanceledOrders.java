package supplier;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import sdek.supplier.mappers.CanceledOrdersMapper;
import sdek.supplier.models.CanceledOrders;
import static org.mockito.Mockito.*;
public class TestMockForCanceledOrders {


    private CanceledOrdersMapper canceledOrdersMapper;
    private CanceledOrders canceledOrder;
    @Before
    public void insertCanceledOrder(){
        canceledOrder = new CanceledOrders(1l);
        canceledOrdersMapper = mock(CanceledOrdersMapper.class);
        when(canceledOrdersMapper.getById(1l)).thenReturn(canceledOrder);
    }
    @Test
    public void testCanceledOrders(){
        CanceledOrders checkCanceledOrder = canceledOrdersMapper.getById(1l);
        Assert.notNull(checkCanceledOrder,"not null");
        Assert.isTrue(checkCanceledOrder.equals(canceledOrder),"is true");
    }
}
