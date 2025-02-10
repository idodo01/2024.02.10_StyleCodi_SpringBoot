package ido.style.mapper;


import ido.style.dto.LovesStyleDTO;
import ido.style.dto.StyleCategoryDTO;
import ido.style.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StyleProductMapper {

    List<StyleCategoryDTO> selectStyleCategories();

    // 찜 스타일 상품 불러오기
    List<LovesStyleDTO> selectLovesStyleByUser(
            UserDTO user
    );
    void insertLovesStyle(LovesStyleDTO loves); // 찜 목록에 상품 정보 추가

    void deleteLovesStyle(LovesStyleDTO loves); // 찜 목록에 상품 정보 삭제




}

    
    
