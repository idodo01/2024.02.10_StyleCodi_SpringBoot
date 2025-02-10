package ido.style.mapper;


import ido.style.dto.*;
import ido.style.searchApi.ProductNaverShopDTO;
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

    // 네이버 쇼핑 상품 불러오기
    List<ProductNaverShopDTO> selectNaverShopProducts(
            Integer categoryNo, // 상품의 카테고리
            String sort // 상품의 정렬 방식
    );



    // 스타일 메이크 전용 카테고리

    @Select("SELECT no, name FROM style_store_category")
    List<StyleStoreCategoryDTO> selectStyleStoreCategories();

    // 찜 상품 불러오기
    List<LovesDTO> selectLovesByUser(
            Integer categoryNo,
            UserDTO user,
            String sort
    );

    void insertLoves(LovesDTO loves); // 찜 목록에 상품 정보 추가

    void deleteLoves(LovesDTO loves); // 찜 목록에 상품 정보 삭제



    ///////////// 스타일 리스트 - 마이 페이지
    void insertStyles(StylesDTO styles); // 스타일 리스트 추가
    // 스타일 불러오기
    List<StylesDTO> selectStylesByUser(
            UserDTO user
    );
    StylesDTO selectStyleByUser(UserDTO user, Integer styleNo);

    ////////////////스타일 리스트 - 스타일 코디
    void insertStylesStyleCodi(StylesProductDTO styles); // 스타일 리스트 추가
    // 스타일 불러오기
    List<StylesProductDTO> selectStylesStyleCodi();
    StylesProductDTO selectStyleStyleCodi(Integer styleNo);

    ////////////  스타일 리스트 - 마이페이지 > 내 업로드 스타일
    List<StylesProductDTO> selectStylesStyleCodiByUser(UserDTO user);
}



    
    
