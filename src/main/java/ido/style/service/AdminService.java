package ido.style.service;

import ido.style.dto.ProductDTO;
import ido.style.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminMapper adminMapper;
    // 상품과 해당 상품의 옵션들을 DB에 INSERT
    public void add_product(ProductDTO product){
        // 상품을 전달해서 DB에 상품의 정보를 추가하기!
        adminMapper.insertProduct(product);

        adminMapper.insertProductImages(product);

    }

}








