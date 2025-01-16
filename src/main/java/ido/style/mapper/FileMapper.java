package ido.style.mapper;

import ido.style.dto.FileDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FileMapper {
    @Select("SELECT * FROM product_image WHERE no = #{imageNo}")
    FileDTO getImageFileByNo(Integer imageNo);
}







