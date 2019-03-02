package sdek.supplier.utils;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import sdek.supplier.config.MyBatisConfig;
import sdek.supplier.mappers.CanceledOrdersMapper;
import sdek.supplier.mappers.Mapper;
import sdek.supplier.mappers.OrderMapper;
import sdek.supplier.models.Dictionary;

import java.util.Set;
import java.util.stream.Collectors;

public class MyBatisUtils {

    public static Dictionary getDataMappersById(Long l, Class type) {
        SqlSessionFactory sessionFactory = MyBatisConfig.getSessionFactory();
        SqlSession session = sessionFactory.openSession();
        Mapper m = (Mapper) session.getMapper(type);
        Dictionary dictionary = m.getById(l);
        session.close();
        return dictionary;
    }

    public static Set getCollectionCanceldOrders() {
        SqlSessionFactory sessionFactory = MyBatisConfig.getSessionFactory();
        SqlSession session = sessionFactory.openSession();
        Set collectionDictionary = session.getMapper(CanceledOrdersMapper.class).getCollectionData();
        session.close();
        return collectionDictionary;
    }

}








