package sdek.supplier.mappers;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import sdek.supplier.models.Order;

import java.util.Set;

public interface OrderMapper {


    @Select("select * from order")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "orderNum", column = "order_num")
    })

    Set<Order> getOrders();

    @Select("select * from order where id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "orderNum", column = "order_num")
    })

    Order getOrderById(Long id);

    @Select("select * from order where orderNum = #{order_num}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "orderNum", column = "order_num")
    })

    Order getOrderByOrderNum(String orderNum);
}
