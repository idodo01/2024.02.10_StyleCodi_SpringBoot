package ido.style.mapper;


import ido.style.dto.CategoryDTO;
import ido.style.dto.ProductDTO;
import ido.style.dto.StyleStoreCategoryDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<ProductDTO> selectProducts(
            Integer categoryNo, // 상품의 카테고리
            String sort // 상품의 정렬 방식
    );

    ProductDTO selectProductByNo(Integer productNo);

    List<CategoryDTO> selectCategories();


//    스타일 스토어 상품, 카테고리

    @Select("SELECT * FROM style_store_category")
    List<StyleStoreCategoryDTO> selectStyleStoreCategories();
}

    
    
