package ido.style.service;

import ido.style.dto.*;
import ido.style.mapper.ProductMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProductService {
    @Autowired
    private ProductMapper productMapper;

    public List<ProductDTO> get_products(Integer categoryNo, String sort){
        return productMapper.selectProducts(categoryNo, sort);
    }

    public ProductDTO get_product(Integer productNo){
        return productMapper.selectProductByNo(productNo);
    }

    public List<CategoryDTO> get_categories(){
        return productMapper.selectCategories();
    }

    //    스타일 스토어 상품, 카테고리

    public List<StyleStoreCategoryDTO> get_style_store_categories(){
        return productMapper.selectStyleStoreCategories();
    }

    // 해당 유저의 장바구니 item들을 가져오기
    public List<DibsDTO> get_dibs_by_user(Integer categoryNo, UserDTO user, String sort){
        return productMapper.selectDibsByUser(categoryNo, user, sort);
    }
}
