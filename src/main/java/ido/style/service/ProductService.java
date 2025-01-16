package ido.style.service;

import ido.style.dto.CategoryDTO;
import ido.style.dto.ProductDTO;
import ido.style.dto.StyleStoreCategoryDTO;
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
}
