package sdek.supplier.mappers;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import sdek.supplier.models.CanceledOrders;
import sdek.supplier.models.FileFormat;
import sdek.supplier.models.Order;

import java.util.Date;
import java.util.Set;

public interface CanceledOrdersMapper extends Dictionary {

    @Select("select * from canceled_orders")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "order", column = "order"),
            @Result(property = "cancelDate", column = "cancel_date",  javaType= Set.class, one=@One(select="getOrder"))
    })

    Set<CanceledOrders> getCanceledOrders();

    @Select("select * from order WHERE id = #{id}")
    @Results(value={
            @Result(property="id", column ="id" ),
            @Result(property="orderNum", column = "order_num")
    })
    Order getOrder(Long id);
}
