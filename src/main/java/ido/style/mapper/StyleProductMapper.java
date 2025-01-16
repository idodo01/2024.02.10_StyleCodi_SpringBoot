package ido.style.mapper;


import ido.style.dto.StyleCategoryDTO;
import ido.style.dto.StyleProductDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StyleProductMapper {
    List<StyleProductDTO> selectStyleProducts(
            Integer categoryNo, // 상품의 카테고리
            String sort // 상품의 정렬 방식
    );

    StyleProductDTO selectStyleProductByNo(Integer productNo);

    List<StyleCategoryDTO> selectStyleCategories();





}

    
    
