package ido.style.service;

import ido.style.dto.StyleCategoryDTO;
import ido.style.dto.StyleProductDTO;
import ido.style.mapper.ProductMapper;
import ido.style.mapper.StyleProductMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class StyleProductService {
    @Autowired
    private StyleProductMapper styleProductMapper;

    public List<StyleProductDTO> get_style_products(Integer categoryNo, String sort){
        return styleProductMapper.selectStyleProducts(categoryNo, sort);
    }

    public StyleProductDTO get_style_product(Integer productNo){
        return styleProductMapper.selectStyleProductByNo(productNo);
    }

    public List<StyleCategoryDTO> get_categories(){
        return styleProductMapper.selectStyleCategories();
    }

}
