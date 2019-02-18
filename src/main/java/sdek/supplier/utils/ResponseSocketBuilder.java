package sdek.supplier.utils;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import sdek.supplier.config.MyBatisConfig;
import sdek.supplier.exceptions.CanceledOrderExistException;
import sdek.supplier.exceptions.InsertCanceledOrderException;
import sdek.supplier.exceptions.OrderNullPointerException;
import sdek.supplier.mappers.CanceledOrdersMapper;
import sdek.supplier.mappers.OrderMapper;
import sdek.supplier.models.CanceledOrders;
import sdek.supplier.models.Dictionary;
import sdek.supplier.models.Order;

import java.io.Serializable;
import java.util.Objects;

public class ResponseSocketBuilder {

    private SqlSession session;
    private ResponseSocket responseSocket;
    private OrderMapper orderMapper;
    private CanceledOrdersMapper canceledOrdersMapper;

    private static Logger log = LogManager.getLogger(ResponseSocketBuilder.class);

    private ResponseSocketBuilder(){
        SqlSessionFactory sessionFactory = MyBatisConfig.getSessionFactory();
        session = sessionFactory.openSession();
        responseSocket = new ResponseSocket();
        orderMapper = session.getMapper(OrderMapper.class);
        canceledOrdersMapper = session.getMapper(CanceledOrdersMapper.class);
    }

    public static ResponseSocket build(RequestSocket requestSocket){
        ResponseSocketBuilder builder = new ResponseSocketBuilder();
        builder.responseSocket.setNumUser(requestSocket.getNumUser());

        try{
            Order order = builder.getExistOrder(requestSocket);
            builder.isExistCanceledOrder(order);
            builder.insertCanceledOrder(order);
            builder.isErrorInsert(order);
        }catch (OrderNullPointerException | CanceledOrderExistException ex){
            log.info(ex);
        } catch (InsertCanceledOrderException | PersistenceException ex){
            log.error(ex);
        }

        builder.closeSession();
        return builder.responseSocket;
    }

    private Order getExistOrder(RequestSocket requestSocket) throws OrderNullPointerException {
        Order order = orderMapper.getOrderByOrderNum(requestSocket.getOrderNum());
        if (Objects.isNull(order)){
            responseSocket.setAction(Actions.ERROR);
            responseSocket.setMessage("Не существует заказа");
            throw new OrderNullPointerException();
        }
        return order;
    }

    private void isExistCanceledOrder(Order order) throws CanceledOrderExistException {
        Dictionary canceledOrderExist = canceledOrdersMapper.getCanceledOrderByOrderNum(order.getOrderNum());
        if (Objects.isNull(responseSocket.getAction()) && !Objects.isNull(canceledOrderExist)){
            responseSocket.setAction(Actions.EXIST);
            responseSocket.setMessage("Данный заказ уже в очереди на отмену");
            responseSocket.setDictionary(canceledOrderExist);
            throw new CanceledOrderExistException();
        }
    }

    private void insertCanceledOrder(Order order) throws PersistenceException{
        CanceledOrders canceledOrder = new CanceledOrders(order);
        canceledOrdersMapper.insertCanceledOrders(canceledOrder);
        session.commit();
        responseSocket.setAction(Actions.SAVE);
        responseSocket.setMessage("Заказ в очереди на отмену");
        responseSocket.setDictionary(canceledOrder);
    }

    private void isErrorInsert(Order order) throws InsertCanceledOrderException{
        CanceledOrders canceledOrderExist = canceledOrdersMapper.getCanceledOrderByOrderNum(order.getOrderNum());
        if (Objects.isNull(canceledOrderExist)){
            responseSocket.setAction(Actions.ERROR);
            responseSocket.setMessage("Ошибка записи в БД");
            responseSocket.setDictionary(order);
            throw new InsertCanceledOrderException();
        }
    }

    private void closeSession(){
        session.close();
    }
}
