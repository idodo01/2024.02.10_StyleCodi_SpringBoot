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

    // 해당 유저의 찜 목록을 가져오기
    public List<DibsDTO> get_dibs_by_user(Integer categoryNo, UserDTO user, String sort){
        return productMapper.selectDibsByUser(categoryNo, user, sort);
    }

    // product: 유저가 추가하려는 상품 정보
    // user: 로그인된 유저
    public void add_dibs(ProductDTO product, UserDTO user){
        DibsDTO dibs = new DibsDTO();
        dibs.setProduct(product);
        dibs.setUser(user);

        productMapper.insertDibs(dibs);
    }

    public void remove_dibs(ProductDTO product, UserDTO user){
        DibsDTO dibs = new DibsDTO();
        dibs.setProduct(product);
        dibs.setUser(user);

        productMapper.deleteDibs(dibs);
    }
}
