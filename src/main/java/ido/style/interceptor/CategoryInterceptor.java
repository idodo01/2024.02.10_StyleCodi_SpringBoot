package ido.style.interceptor;


import ido.style.dto.CategoryDTO;
import ido.style.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Component
public class CategoryInterceptor implements HandlerInterceptor {
    @Autowired private ProductService productService;
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        List<CategoryDTO> categories = productService.get_categories();
        modelAndView.addObject("categories", categories);
    }
}











