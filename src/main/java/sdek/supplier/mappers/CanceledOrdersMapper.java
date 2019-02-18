package sdek.supplier.mappers;

import org.apache.ibatis.annotations.*;
import sdek.supplier.models.CanceledOrders;
import sdek.supplier.models.Dictionary;
import sdek.supplier.models.Order;

import java.util.Set;

public interface CanceledOrdersMapper extends Mapper {

    @Select("select * from canceled_orders")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "orderId", column = "id_order"),
            @Result(property = "order", column = "id_order",  javaType= Order.class, one=@One(select="getOrder")),
            @Result(property = "cancelDate", column = "cancel_date")
    })

    Set<Dictionary> getCollectionData();

    @Select("select * from canceled_orders WHERE id = #{id}")
    @Results(value={
            @Result(property = "id", column = "id"),
            @Result(property = "orderId", column = "id_order"),
            @Result(property = "order", column = "id_order",  javaType= Order.class, one=@One(select="getOrder")),
            @Result(property = "cancelDate", column = "cancel_date")
    })
    CanceledOrders getById(Long id);

    @Select("select * from orders WHERE id = #{id}")
    @Results(value={
            @Result(property="id", column ="id" ),
            @Result(property="orderNum", column = "order_num")
    })
    Order getOrder(Long id);

    @Select("select * from canceled_orders where id_order = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "orderId", column = "id_order"),
            @Result(property = "order", column = "id_order",  javaType= Order.class, one=@One(select="getOrder")),
            @Result(property = "cancelDate", column = "cancel_date")
    })

    CanceledOrders getCanceledOrderByOrderId(Long id);


    @Select("select * from canceled_orders co inner join orders o on co.id_order = o.id and o.order_num = #{orderNum}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "orderId", column = "id_order"),
            @Result(property = "order", column = "id_order",  javaType= Order.class, one=@One(select="getOrder")),
            @Result(property = "cancelDate", column = "cancel_date")
    })

    CanceledOrders getCanceledOrderByOrderNum(String orderNum);


    @Insert("insert into canceled_orders (id_order, cancel_date) VALUES(#{orderId}, #{cancelDate})")
    void insertCanceledOrders(CanceledOrders canceledOrders);

    @Delete("delete from canceled_orders where id_order = #{orderId}")
    void deleteCanceledOrder(CanceledOrders canceledOrders);
}
