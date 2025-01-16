package ido.style.mapper;

import ido.style.dto.StyleFileDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StyleFileMapper {
    @Select("SELECT * FROM style_product_image WHERE no = #{imageNo}")
    StyleFileDTO getStyleImageFileByNo(Integer imageNo);
}







