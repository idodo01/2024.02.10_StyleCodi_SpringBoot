package ido.style.interceptor;


import ido.style.dto.CategoryDTO;
import ido.style.dto.StyleCategoryDTO;
import ido.style.service.ProductService;
import ido.style.service.StyleProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Component
public class StyleCategoryInterceptor implements HandlerInterceptor {
    @Autowired private StyleProductService styleProductService;
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        List<StyleCategoryDTO> styleCategories = styleProductService.get_categories();
        modelAndView.addObject("styleCategories", styleCategories);
    }
}











