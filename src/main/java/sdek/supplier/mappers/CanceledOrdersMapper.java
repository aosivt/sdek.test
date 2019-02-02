package sdek.supplier.mappers;

import org.apache.ibatis.annotations.*;
import sdek.supplier.models.CanceledOrders;
import sdek.supplier.models.Order;

import java.util.Set;

public interface CanceledOrdersMapper {

    @Select("select * from canceled_orders")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "order", column = "order_id",  javaType= Order.class, one=@One(select="getOrder")),
            @Result(property = "cancelDate", column = "cancel_date")
    })

    Set<CanceledOrders> getCanceledOrders();

    @Select("select * from order WHERE id = #{id}")
    @Results(value={
            @Result(property="id", column ="id" ),
            @Result(property="orderNum", column = "order_num")
    })
    Order getOrder(Long id);


    @Insert("insert into canceled_orders (order_id, cancel_date) VALUES(#{orderId}, #{cancelDate})")
    void insertCanceledOrders(CanceledOrders canceledOrders);
}
