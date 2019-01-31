package sdek.supplier.mappers;

import org.apache.ibatis.annotations.*;
import sdek.supplier.models.FileFormat;
import sdek.supplier.models.Order;

import java.util.Set;

public interface FileFormatMapper {
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "shortName", column = "short_name")
    })
    @Select("SELECT * from file_format")
    Set<FileFormat> getFileFormats();

    @Select("SELECT * from file_format WHERE id = #{id}")
    FileFormat getFileFormatById(Long id);

    @Select("SELECT * from file_format order by id desc limit 1")
    FileFormat getFileFormatByMaxId();

    @Insert("INSERT into file_format(title,short_name) VALUES(#{title}, #{shortName})")
    void insertFileFormat(FileFormat fileFormat);

    @Update("UPDATE file_format SET title=#{title}, short_name =#{shortName} WHERE id =#{id}")
    void updateVillage(FileFormat fileFormat);

    @Delete("DELETE FROM file_format WHERE id =#{id}")
    void deleteVillage(Long id);
}
