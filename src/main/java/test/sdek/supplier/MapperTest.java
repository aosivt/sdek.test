package test.sdek.supplier;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;


import org.junit.Test;
import sdek.supplier.config.MyBatisConfig;
import sdek.supplier.mappers.FileFormatMapper;
import sdek.supplier.models.FileFormat;

import java.util.Set;

public class MapperTest {
    @Test
    public void test() {
        SqlSessionFactory sessionFactory = MyBatisConfig.getSessionFactory();
        SqlSession session = sessionFactory.openSession();

        FileFormatMapper fileFormatMapper = session.getMapper(FileFormatMapper.class);
        Set<FileFormat> fileFormats = fileFormatMapper.getFileFormats();
        fileFormats.parallelStream().map(FileFormat::getTitle).forEach(System.out::println);

        FileFormat fileFormat = new FileFormat();
        fileFormat.setShortName("shortName");
        fileFormat.setTitle("title");
        fileFormatMapper.insertFileFormat(fileFormat);
        session.commit();


        fileFormatMapper = session.getMapper(FileFormatMapper.class);
        FileFormat fileFormatById = fileFormatMapper.getFileFormatByMaxId();
        System.out.println(fileFormatById.getTitle().equals(fileFormat.getTitle()));


        fileFormatMapper = session.getMapper(FileFormatMapper.class);
        fileFormatById.setTitle("345");
        fileFormatMapper.updateVillage(fileFormatById);
        session.commit();


        fileFormatMapper = session.getMapper(FileFormatMapper.class);
        fileFormatMapper.deleteVillage(fileFormatById.getId());
        session.commit();
        session.close();

    }
}
