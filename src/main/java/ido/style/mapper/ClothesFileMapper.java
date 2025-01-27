package ido.style.mapper;

import ido.style.dto.ClothesFileDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ClothesFileMapper {
    @Select("SELECT * FROM clothes_image WHERE no = #{imageNo}")
    ClothesFileDTO getClothesImageFileByNo(Integer imageNo);
}







