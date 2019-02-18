package supplier;

import org.junit.Before;
import org.junit.Test;
import org.springframework.aop.TargetClassAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import sdek.supplier.mappers.CanceledOrdersMapper;
import sdek.supplier.models.CanceledOrders;
import sdek.supplier.utils.ResponseSocketBuilder;

import java.lang.reflect.Field;

import static org.mockito.Mockito.*;

public class TestResponceSocketBuilder {

    ResponseSocketBuilder responseSocketBuilder;
    @Before
    public void setup() throws NoSuchFieldException{
        Field orderMapper = ResponseSocketBuilder.class.getDeclaredField("orderMapper");
    }
}

//    Method method = TargetClass.getDeclaredMethod(methodName, argClasses);
//    method.setAccessible(true);
//    return method.invoke(targetObject, argObjects);

//    Field field = TargetClass.getDeclaredField(fieldName);
//    field.setAccessible(true);
//    field.set(object, value);