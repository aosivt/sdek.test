package sdek.supplier.utils;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import sdek.supplier.config.MyBatisConfig;
import sdek.supplier.mappers.CanceledOrdersMapper;
import sdek.supplier.mappers.OrderMapper;
import sdek.supplier.models.CanceledOrders;
import sdek.supplier.models.Dictionary;
import sdek.supplier.models.Order;

import java.io.Serializable;
import java.util.Objects;

public class ResponseSocketBuilder implements Serializable {

    private SqlSession session;
    private ResponseSocket responceSocket;
    private OrderMapper orderMapper;
    private CanceledOrdersMapper canceledOrdersMapper;
    private Order order;

    private ResponseSocketBuilder(){
        SqlSessionFactory sessionFactory = MyBatisConfig.getSessionFactory();
        session = sessionFactory.openSession();
        responceSocket = new ResponseSocket();
        orderMapper = session.getMapper(OrderMapper.class);
        canceledOrdersMapper = session.getMapper(CanceledOrdersMapper.class);
    }

    public static ResponseSocket build(RequestSocket requestSocket){
        ResponseSocketBuilder builder = new ResponseSocketBuilder();

        builder.isExistOrder(requestSocket);
        builder.isExistCanceldOrder(requestSocket);
        builder.insertCanceldOrder(requestSocket);
        builder.closeSession();
        return builder.responceSocket;
    }

    private boolean isExistOrder(RequestSocket requestSocket){
        order = orderMapper.getOrderByOrderNum(requestSocket.getOrderNum());
        if (Objects.isNull(order)){
            responceSocket.setAction(Actions.ERROR);
            responceSocket.setMessage("Не существует заказа");
            responceSocket.setDictionary(order);
            responceSocket.setNumUser(requestSocket.getNumUser());
            return true;
        }
        return false;
    }

    private boolean isExistCanceldOrder(RequestSocket requestSocket){
        CanceledOrders canceledOrderExist = canceledOrdersMapper.getCanceledOrderByOrderNum(requestSocket.getOrderNum());
        if (Objects.isNull(responceSocket.getAction()) && !Objects.isNull(canceledOrderExist)){
            responceSocket.setAction(Actions.EXIST);
            responceSocket.setMessage("Данный заказ уже в очереди на отменену");
            responceSocket.setDictionary(canceledOrderExist);
            responceSocket.setNumUser(requestSocket.getNumUser());
            return true;
        }
        return false;
    }

    private boolean isErrorInsert(RequestSocket requestSocket){
        order = orderMapper.getOrderByOrderNum(requestSocket.getOrderNum());
        if (Objects.isNull(order)){
            responceSocket.setAction(Actions.ERROR);
            responceSocket.setMessage("Ошибка записи в БД");
            responceSocket.setDictionary(order);
            responceSocket.setNumUser(requestSocket.getNumUser());
            return true;
        }
        return false;
    }

    private boolean insertCanceldOrder(RequestSocket requestSocket){
        if (Objects.isNull(responceSocket.getAction())) {
            CanceledOrders canceledOrder = new CanceledOrders(order);
            canceledOrdersMapper.insertCanceledOrders(canceledOrder);
            session.commit();
            responceSocket.setAction(Actions.SAVE);
            responceSocket.setMessage("Заказ в очереди на отмену");
            responceSocket.setDictionary(canceledOrder);
            responceSocket.setNumUser(requestSocket.getNumUser());
            return true;
        }
        return false;
    }

    private void closeSession(){
        session.close();
    }
}
