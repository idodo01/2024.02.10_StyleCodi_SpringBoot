package ido.style.service;

import ido.style.dto.*;
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

    public List<StyleCategoryDTO> get_categories(){
        return styleProductMapper.selectStyleCategories();
    }

    // 해당 유저의 찜 스타일 목록을 가져오기
    public List<LovesStyleDTO> get_lovesStyle_by_user(UserDTO user){
        return styleProductMapper.selectLovesStyleByUser(user);
    }

    public void add_lovesStyle(StylesProductDTO style, UserDTO user){
        LovesStyleDTO loves = new LovesStyleDTO();
        loves.setStyle(style);
        loves.setUser(user);

        styleProductMapper.insertLovesStyle(loves);
    }

    public void remove_lovesStyle(StylesProductDTO style, UserDTO user){
        LovesStyleDTO loves = new LovesStyleDTO();
        loves.setStyle(style);
        loves.setUser(user);

        styleProductMapper.deleteLovesStyle(loves);
    }


}
