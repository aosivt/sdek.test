package sdek.supplier.utils;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import sdek.supplier.config.MyBatisConfig;
import sdek.supplier.mappers.Mapper;
import sdek.supplier.mappers.OrderMapper;
import sdek.supplier.models.Dictionary;

import java.util.Set;

public class MyBatisUtils {

    public static Dictionary getDataMappersById(Long l, Class type){
        SqlSessionFactory sessionFactory = MyBatisConfig.getSessionFactory();
        SqlSession session = sessionFactory.openSession();
        Mapper m = (Mapper) session.getMapper(type);
        Dictionary dictionary = m.getById(l);
        session.close();
        return dictionary;
    }
    public static Set getDataMappers(Class type){
        SqlSessionFactory sessionFactory = MyBatisConfig.getSessionFactory();
        SqlSession session = sessionFactory.openSession();
        Mapper m = (Mapper) session.getMapper(type);
        Set collectionDictionary = m.getCollectionData();
        session.close();
        return collectionDictionary;
    }


}
