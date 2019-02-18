package supplier;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.aop.TargetClassAware;
import org.springframework.beans.factory.annotation.Autowired;
import sdek.supplier.exceptions.OrderNullPointerException;
import sdek.supplier.mappers.CanceledOrdersMapper;
import sdek.supplier.mappers.OrderMapper;
import sdek.supplier.models.CanceledOrders;
import sdek.supplier.models.Order;
import sdek.supplier.utils.Actions;
import sdek.supplier.utils.RequestSocket;
import sdek.supplier.utils.ResponseSocket;
import sdek.supplier.utils.ResponseSocketBuilder;

import java.io.UncheckedIOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.mockito.Mockito.*;

public class TestResponseSocketBuilder {

    ResponseSocketBuilder responseSocketBuilder;
    ResponseSocket responseSocketReflect = new ResponseSocket();
    RequestSocket requestSocketReflect = new RequestSocket();
    Order orderReflect = new Order(1l,"test1");
    @Before
    public void setup() throws NoSuchFieldException, IllegalAccessException,
                                InvocationTargetException, InstantiationException {
        Constructor<ResponseSocketBuilder> constructor =
                (Constructor<ResponseSocketBuilder>) ResponseSocketBuilder.class.getDeclaredConstructors()[0];
        constructor.setAccessible(true);
        responseSocketBuilder = constructor.newInstance();

        Order order = new Order(1l,"test1");
        CanceledOrders canceledOrder = new CanceledOrders(1l,order);
        CanceledOrdersMapper canceledOrdersMapperMock = mock(CanceledOrdersMapper.class);
        when(canceledOrdersMapperMock.getCanceledOrderByOrderNum("test1")).thenReturn(canceledOrder);
        when(canceledOrdersMapperMock.getCanceledOrderByOrderNum("test2")).thenReturn(null);

        OrderMapper orderMapperMock = mock(OrderMapper.class);
        when(orderMapperMock.getOrderByOrderNum("test1")).thenReturn(order);

        Field orderMapper = ResponseSocketBuilder.class.getDeclaredField("orderMapper");
        orderMapper.setAccessible(true);
        orderMapper.set(responseSocketBuilder,orderMapperMock);

        Field canceldOrderMapper = ResponseSocketBuilder.class.getDeclaredField("canceledOrdersMapper");
        canceldOrderMapper.setAccessible(true);
        canceldOrderMapper.set(responseSocketBuilder,canceledOrdersMapperMock);

        Field responseSocket = ResponseSocketBuilder.class.getDeclaredField("responseSocket");
        responseSocket.setAccessible(true);
        responseSocket.set(responseSocketBuilder,responseSocketReflect);

        requestSocketReflect.setNumUser("123");
        requestSocketReflect.setOrderNum("test2");
    }
    @Test
    public void testResponseSocketBuilder() throws NoSuchMethodException, IllegalAccessException,InvocationTargetException {
        assertGetExistOrderError();
        assertGetExistOrder();
        assertIsExistCanceledOrderError();
        assertIsExistCanceledOrder();
        assertInsertCanceledOrder();
        assertIsErrorInsertError();
        assertIsErrorInsert();
    }
    private void assertGetExistOrderError() throws NoSuchMethodException, IllegalAccessException {
        requestSocketReflect.setOrderNum("test2");
        try {
            Method method = ResponseSocketBuilder.class.getDeclaredMethod("getExistOrder", RequestSocket.class);
            method.setAccessible(true);
            method.invoke(responseSocketBuilder, requestSocketReflect);
        }catch (InvocationTargetException ex){
            Assert.assertEquals(ex.getTargetException().getMessage(), "Order is empty");
            Assert.assertEquals(responseSocketReflect.getAction(), Actions.ERROR);
            Assert.assertEquals(responseSocketReflect.getMessage(), "Не существует заказа");
        }
    }
    private void assertGetExistOrder() throws NoSuchMethodException, IllegalAccessException,InvocationTargetException  {
        requestSocketReflect.setOrderNum("test1");
            Method method = ResponseSocketBuilder.class.getDeclaredMethod("getExistOrder", RequestSocket.class);
            method.setAccessible(true);
            Order order = (Order) method.invoke(responseSocketBuilder, requestSocketReflect);
            Assert.assertEquals(orderReflect.getId(),order.getId());
            Assert.assertEquals(orderReflect.getOrderNum(),order.getOrderNum());
    }
    private void assertIsExistCanceledOrderError() throws NoSuchMethodException, IllegalAccessException {
        requestSocketReflect.setOrderNum("test1");
        responseSocketReflect.setAction(null);
        try {
            Method method = ResponseSocketBuilder.class.getDeclaredMethod("isExistCanceledOrder", Order.class);
            method.setAccessible(true);
            method.invoke(responseSocketBuilder, orderReflect);
        }catch (InvocationTargetException ex){
            Assert.assertEquals(ex.getTargetException().getMessage(), "Canceled order exist inside queue");
            Assert.assertEquals(responseSocketReflect.getAction(), Actions.EXIST);
        }
    }
    private void assertIsExistCanceledOrder() throws NoSuchMethodException, IllegalAccessException {
        requestSocketReflect.setOrderNum("test1");
        responseSocketReflect.setAction(null);
        try {
            Method method = ResponseSocketBuilder.class.getDeclaredMethod("isExistCanceledOrder", Order.class);
            method.setAccessible(true);
            method.invoke(responseSocketBuilder, orderReflect);
            Assert.assertNull(responseSocketReflect.getAction());
        }catch (InvocationTargetException ex){

        }
    }
    private void assertInsertCanceledOrder() throws NoSuchMethodException, IllegalAccessException {
        requestSocketReflect.setOrderNum("test1");
        responseSocketReflect.setAction(null);
        try {
            Method method = ResponseSocketBuilder.class.getDeclaredMethod("insertCanceledOrder", Order.class);
            method.setAccessible(true);
            method.invoke(responseSocketBuilder, orderReflect);
            Assert.assertEquals(responseSocketReflect.getAction(),Actions.SAVE);
        }catch (InvocationTargetException ex){

        }
    }
    private void assertIsErrorInsertError() throws NoSuchMethodException, IllegalAccessException {
        requestSocketReflect.setOrderNum("test2");
        responseSocketReflect.setAction(null);
        try {
            Method method = ResponseSocketBuilder.class.getDeclaredMethod("isErrorInsert", Order.class);
            method.setAccessible(true);
            method.invoke(responseSocketBuilder, orderReflect);
        }catch (InvocationTargetException ex){
            Assert.assertEquals(ex.getTargetException().getMessage(), "error working DB");
            Assert.assertEquals(responseSocketReflect.getAction(), Actions.ERROR);
            Assert.assertEquals(responseSocketReflect.getDictionary().getId(), orderReflect.getId());
        }
    }
    private void assertIsErrorInsert() throws NoSuchMethodException, IllegalAccessException {
        requestSocketReflect.setOrderNum("test1");
        responseSocketReflect.setAction(null);
        try {
            Method method = ResponseSocketBuilder.class.getDeclaredMethod("isErrorInsert", Order.class);
            method.setAccessible(true);
            method.invoke(responseSocketBuilder, orderReflect);
            Assert.assertNull(responseSocketReflect.getAction());
        }catch (InvocationTargetException ex){

        }
    }
}
