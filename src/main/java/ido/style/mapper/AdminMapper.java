package ido.style.mapper;

import ido.style.dto.ProductDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {
    void insertProduct(ProductDTO product);
    void insertProductImages(ProductDTO product);


}
