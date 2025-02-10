package ido.style.controller;

import ido.style.dto.*;
//import ido.style.searchApi.JsonParser;
//import ido.style.searchApi.ProductNaverShopDTO;
import ido.style.searchApi.ProductNaverShopDTO;
import ido.style.service.ProductService;
import ido.style.service.StyleProductService;
import ido.style.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
public class MainController {
    @Autowired
    private ProductService productService;
    @Autowired
    private StyleProductService styleProductService;
    @Autowired
    private UserService userService;

    @GetMapping("/") // localhost:8080
    public String get_home(Model model){
        List<StyleCategoryDTO> styleCategories = styleProductService.get_categories();
        List<CategoryDTO> categories = productService.get_categories();

        model.addAttribute("styleCategories", styleCategories);
        model.addAttribute("categories", categories);
        return "main/home"; // 홈페이지로 가라
    }

//    // 로그인 필요 페이지
//    @GetMapping("/loginPlz")
//    public String loginPlz() {
//       return "redirect:/user/login";
//    }


 // 스타일 리스트 화면
    @GetMapping("/styleCategory")
    public String get_styleCategory(
//            @RequestParam(defaultValue = "1") Integer categoryNo,
//            String sort,
            Model model

    ){

        List<StylesProductDTO> styles = productService.get_styles_style_codi();
        model.addAttribute("styles", styles);

        // 상위 header에 사용되는 카테고리
        List<StyleCategoryDTO> styleCategories = styleProductService.get_categories();
        List<CategoryDTO> categories = productService.get_categories();
        model.addAttribute("styleCategories", styleCategories);
        model.addAttribute("categories", categories);

        return "main/styleCategory";
    }

    // 스타일 하나의 화면
    @GetMapping("/styleProduct/{styleNo}")
    public String get_styleProduct_detail(
            @PathVariable Integer styleNo,
            Model model
    ){

        StylesProductDTO style = productService.get_style_style_codi(styleNo);
        model.addAttribute("style", style);

        // 상위 header에 사용되는 카테고리
        List<StyleCategoryDTO> styleCategories = styleProductService.get_categories();
        List<CategoryDTO> categories = productService.get_categories();
        model.addAttribute("styleCategories", styleCategories);
        model.addAttribute("categories", categories);

        return "main/styleProduct";
    }

    // 모든 상품 리스트 화면 - 네이버 쇼핑 상품
    @GetMapping("/category")
    public String get_category(
            @RequestParam(defaultValue = "1") Integer categoryNo,
            String sort,

            @AuthenticationPrincipal UserDTO user,

            Model model
    ){
        model.addAttribute("categoryNo", categoryNo); // 정렬 a태그에 사용

        List<ProductNaverShopDTO> products = productService.get_naver_shop_products(categoryNo, sort);

        List<StyleCategoryDTO> styleCategories = styleProductService.get_categories();
        List<CategoryDTO> categories = productService.get_categories();

        List<LovesDTO> loves = productService.get_loves_by_user(categoryNo, user, sort);
        Map<Integer, Boolean> lovesMap = products.stream()
                .collect(Collectors.toMap(
                        ProductNaverShopDTO::getNo,
                        product -> loves.stream().anyMatch(love -> love.getProduct().getNo().equals(product.getNo()))
                ));

        System.out.println(products);
        model.addAttribute("products", products);

        model.addAttribute("styleCategories", styleCategories);
        model.addAttribute("categories", categories);

        model.addAttribute("lovesMap", lovesMap);

        return "main/category";
    }

    /****************************************************************************/
    // 스타일
    @GetMapping("/style-make")
    public String get_style_make(
            Model model
    ){

        List<CategoryDTO> categories = productService.get_categories();
        List<StyleCategoryDTO> styleCategories = styleProductService.get_categories();


        model.addAttribute("categories", categories);
        model.addAttribute("styleCategories", styleCategories);

//        String iframe_url ="http://211.188.55.158:8080/style-store";
        String iframe_url ="http://localhost:8080/style-store";
        model.addAttribute("iframe_url", iframe_url);

        return "main/style-make";
    }

    // 스타일 리스트 - 마이페이지 저장
    @PostMapping("/style-list")
    public ResponseEntity<Void> style_list(
//            @RequestBody Map<String, Object> requestBody,
            @RequestBody List<ProductNaverShopDTO> products,
            @AuthenticationPrincipal UserDTO userDTO
    ){

//        System.out.println(products);
//        System.out.println(products.get(0));
//
        ProductNaverShopDTO product1 = products.get(0);
        ProductNaverShopDTO product2 = products.get(1);
        ProductNaverShopDTO product3 = products.get(2);
        ProductNaverShopDTO product4 = products.get(3);
        ProductNaverShopDTO product5 = products.get(4);


        if(Objects.isNull(userDTO)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        productService.add_styles(product1, product2, product3, product4, product5, userDTO);
        return ResponseEntity.ok().build();
    }

    // 스타일 리스트 - 스타일 코디 업로드
    @PostMapping("/style-list-stylecodi")
    public ResponseEntity<Void> style_list_stylecodi(

            @RequestBody List<ProductNaverShopDTO> products,
            @AuthenticationPrincipal UserDTO userDTO
    ){

        ProductNaverShopDTO product1 = products.get(0);
        ProductNaverShopDTO product2 = products.get(1);
        ProductNaverShopDTO product3 = products.get(2);
        ProductNaverShopDTO product4 = products.get(3);
        ProductNaverShopDTO product5 = products.get(4);

        if(Objects.isNull(userDTO)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        productService.add_styles_stylecodi(product1, product2, product3, product4, product5, userDTO);
        return ResponseEntity.ok().build();
    }


    // 스타일 - style-make 안에 사용되는 IFRAME 1 (스토어 페이지)
    @GetMapping("/style-store")
    public String get_style_store(
            @RequestParam(defaultValue = "1") Integer categoryNo,
            String sort,
            Model model
    ){

        model.addAttribute("categoryNo", categoryNo); // 정렬 a태그에 사용

        List<ProductNaverShopDTO> products = productService.get_naver_shop_products(categoryNo, sort);

//        List<ProductDTO> products = productService.get_products(categoryNo, sort);
        List<StyleStoreCategoryDTO> styleStoreCategories = productService.get_style_store_categories();

        System.out.println(products);
        model.addAttribute("products", products);
        model.addAttribute("styleStoreCategories", styleStoreCategories);
        return "main/style-store";
    }

    // 스타일 - style-make 안에 사용되는 IFRAME 2 (찜 페이지)
    @GetMapping("/style-loves")
    public String get_user_love(
            @RequestParam(defaultValue = "1") Integer categoryNo,
            String sort,

            Authentication authentication,
            @AuthenticationPrincipal UserDTO user,

            Model model
    ){
        if(!(Objects.nonNull(authentication))){
            return "redirect:/user/login";
        }

        model.addAttribute("categoryNo", categoryNo); // 정렬 a태그에 사용

        List<LovesDTO> loves = productService.get_loves_by_user(categoryNo, user, sort); // 찜 목록
        List<StyleStoreCategoryDTO> styleStoreCategories = productService.get_style_store_categories(); // 편의 카테고리


        System.out.println(loves);
        model.addAttribute("loves", loves);
        model.addAttribute("styleStoreCategories", styleStoreCategories);


        return "main/style-loves";
    }


    // 찜목록에 상품을 추가하기
    @PostMapping("/loves-post")
    public ResponseEntity<Void> loves_post(
            @RequestBody ProductNaverShopDTO product,
            @AuthenticationPrincipal UserDTO userDTO
    ){
        if(Objects.isNull(userDTO)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        // 해당 상품과 유저를 전달해서 찜목록에 추가하기
        productService.add_loves(product, userDTO);
        return ResponseEntity.ok().build();
    }

    // 찜목록에 상품을 삭제하기
    @DeleteMapping("/loves-delete")
    public ResponseEntity<Void> loves_delete(
            @RequestBody ProductNaverShopDTO product,
            @AuthenticationPrincipal UserDTO userDTO
    ){

        productService.remove_loves(product, userDTO);
        return ResponseEntity.ok().build(); // 200

    }
    // 내 옷장
    @GetMapping("/style-clothes")
    public String get_user_clothes(
            @RequestParam(defaultValue = "1") Integer categoryNo,
            String sort,

            Authentication authentication,
            @AuthenticationPrincipal UserDTO user,

            Model model
    ){
        if(!(Objects.nonNull(authentication))){
            return "redirect:/user/login";
        }

        model.addAttribute("categoryNo", categoryNo); // 정렬 a태그에 사용

        List<StyleStoreCategoryDTO> styleStoreCategories = productService.get_style_store_categories(); // 편의 카테고리
        model.addAttribute("styleStoreCategories", styleStoreCategories);

        List<ClothesDTO> clothes =  userService.get_clothes_products(categoryNo, user, sort);
        model.addAttribute("clothes", clothes);


        return "main/style-clothes";
    }
}
