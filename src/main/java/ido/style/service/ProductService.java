package ido.style.service;

import ido.style.dto.*;
import ido.style.mapper.ProductMapper;
import ido.style.searchApi.ProductNaverShopDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProductService {
    @Autowired
    private ProductMapper productMapper;

    // 상품 불러오기
    public List<ProductDTO> get_products(Integer categoryNo, String sort){
        return productMapper.selectProducts(categoryNo, sort);
    }

    public ProductDTO get_product(Integer productNo){
        return productMapper.selectProductByNo(productNo);
    }

    public List<CategoryDTO> get_categories(){
        return productMapper.selectCategories();
    }

    // 상품 불러오기 - 네이버 상품
    public List<ProductNaverShopDTO> get_naver_shop_products(){
        return productMapper.selectNaverShopProducts();
    }



    // 스타일 스토어 상품, 카테고리
    public List<StyleStoreCategoryDTO> get_style_store_categories(){
        return productMapper.selectStyleStoreCategories();
    }

    // 해당 유저의 찜 목록을 가져오기
    public List<LovesDTO> get_loves_by_user(Integer categoryNo, UserDTO user, String sort){
        return productMapper.selectLovesByUser(categoryNo, user, sort);
    }

    // product: 유저가 추가하려는 상품 정보
    // user: 로그인된 유저
    public void add_loves(ProductDTO product, UserDTO user){
        LovesDTO loves = new LovesDTO();
        loves.setProduct(product);
        loves.setUser(user);

        productMapper.insertLoves(loves);
    }

    public void remove_loves(ProductDTO product, UserDTO user){
        LovesDTO loves = new LovesDTO();
        loves.setProduct(product);
        loves.setUser(user);

        productMapper.deleteLoves(loves);
    }

    // 스타일 저장
    public void add_styles(
            ProductDTO product_top,
            ProductDTO product_outer,
            ProductDTO product_bottom,
            ProductDTO product_shoes,
            ProductDTO product_bag,
            UserDTO user
    ){
        StylesDTO styles = new StylesDTO();
        styles.setProduct_top(product_top);
        styles.setProduct_outer(product_outer);
        styles.setProduct_bottom(product_bottom);
        styles.setProduct_shoes(product_shoes);
        styles.setProduct_bag(product_bag);

        styles.setUser(user);

        productMapper.insertStyles(styles);
    }

    // 해당 유저의 스타일 목록을 가져오기
    public List<StylesDTO> get_styles_by_user(UserDTO user){
        return productMapper.selectStylesByUser(user);
    }

    public StylesDTO get_style_by_user(UserDTO user, Integer styleNo){
        return productMapper.selectStyleByUser(user, styleNo);
    }

}
