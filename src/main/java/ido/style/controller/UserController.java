package ido.style.controller;

import ido.style.dto.*;
import ido.style.searchApi.ProductNaverShopDTO;
import ido.style.service.*;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger log = LogManager.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private StyleProductService styleProductService;


    @GetMapping("/login")
    public String user_login(
            Authentication authentication,
            Model model
    ) {
        // 이미 로그인이 되어 있는 상태이다
        if(Objects.nonNull(authentication)){
            return "redirect:/";
        }

        List<StyleCategoryDTO> styleCategories = styleProductService.get_categories();
        List<CategoryDTO> categories = productService.get_categories();
        model.addAttribute("styleCategories", styleCategories);
        model.addAttribute("categories", categories);
        return "user/login";
    }
    /************************************************/
    @GetMapping("/join")
    public String user_join(
            @ModelAttribute UserDTO userDTO,
            Authentication authentication,
            Model model
    ) {
        // 이미 로그인이 되어 있는 상태이다
        if(Objects.nonNull(authentication)){
            return "redirect:/";
        }
        List<StyleCategoryDTO> styleCategories = styleProductService.get_categories();
        List<CategoryDTO> categories = productService.get_categories();
        model.addAttribute("styleCategories", styleCategories);
        model.addAttribute("categories", categories);
        return "user/join";
    }

    @PostMapping("/join")
    public String user_join(
            @ModelAttribute UserDTO userDTO,
            BindingResult bindingResult,
            HttpSession session
    ) {
        if(bindingResult.hasErrors()){
            log.error("에러 발생!");
            log.error(bindingResult.getAllErrors());
            return "user/join";
        }

        boolean joinResult = userService.join_user(userDTO);
        // 가입 성공이면 login 화면으로, 실패라면 회원가입 화면으로.
        return joinResult ? "redirect:/user/login" : "user/join";
    }


    /************************ 유저 마이페이지 ***************************/

    /////////////////////// 내 옷장

    @GetMapping("/myClothes")
    public String user_myClothes(
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

        // 상위 header에 사용되는 카테고리
        List<StyleCategoryDTO> styleCategories = styleProductService.get_categories();
        List<CategoryDTO> categories = productService.get_categories();
        model.addAttribute("styleCategories", styleCategories);
        model.addAttribute("categories", categories);

        return "user/myClothes";
    }

    @GetMapping("/clothes-upload")
    public String clothes_upload(
//            @AuthenticationPrincipal UserDTO user,
            Authentication authentication,
            Model model
    ){

        if(!(Objects.nonNull(authentication))){
            return "redirect:/user/login";
        }

        List<StyleCategoryDTO> styleCategories = styleProductService.get_categories();
        List<CategoryDTO> categories = productService.get_categories();
        model.addAttribute("styleCategories", styleCategories);
        model.addAttribute("categories", categories);
        return "user/clothes-upload";
    }

    @PostMapping("/clothes-upload")
    public String clothes_upload(
            @AuthenticationPrincipal UserDTO user,
            ClothesDTO clothes
    )
    {
        userService.add_clothes(clothes, user);
        return "redirect:/user/myClothes";
    }
    /////////////////////// 내 찜
    @GetMapping("/myLove")
    public String user_myLove(
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

        // 상위 header에 사용되는 카테고리
        List<StyleCategoryDTO> styleCategories = styleProductService.get_categories();
        List<CategoryDTO> categories = productService.get_categories();
        model.addAttribute("styleCategories", styleCategories);
        model.addAttribute("categories", categories);

        // 상품 및 찜
        List<ProductNaverShopDTO> products = productService.get_naver_shop_products(categoryNo, sort);
        List<LovesDTO> loves = productService.get_loves_by_user(categoryNo, user, sort);

        Map<Integer, Boolean> lovesMap = products.stream()
                .collect(Collectors.toMap(
                        ProductNaverShopDTO::getNo,
                        product -> loves.stream().anyMatch(love -> love.getProduct().getNo().equals(product.getNo()))
                ));

        model.addAttribute("products", products);
        model.addAttribute("loves", loves);

        model.addAttribute("lovesMap", lovesMap);

        return "user/myLove";
    }
    @GetMapping("/myLoveStyle")
    public String user_myLoveStyle(
            Authentication authentication,
            @AuthenticationPrincipal UserDTO user,

            Model model
    ){
        if(!(Objects.nonNull(authentication))){
            return "redirect:/user/login";
        }


        // 상위 header에 사용되는 카테고리
        List<StyleCategoryDTO> styleCategories = styleProductService.get_categories();
        List<CategoryDTO> categories = productService.get_categories();
        model.addAttribute("styleCategories", styleCategories);
        model.addAttribute("categories", categories);

        List<StylesProductDTO> styles = productService.get_styles_style_codi();
        List<LovesStyleDTO> loves = styleProductService.get_lovesStyle_by_user(user);

        Map<Integer, Boolean> lovesMap = styles.stream()
                .collect(Collectors.toMap(
                        StylesProductDTO::getNo,
                        style -> loves.stream().anyMatch(love -> love.getStyle().getNo().equals(style.getNo()))
                ));

        model.addAttribute("styles", styles);
        model.addAttribute("loves", loves);

        model.addAttribute("lovesMap", lovesMap);




        return "user/myLoveStyle";
    }

    /////////////////////// 내 스타일
    @GetMapping("/myStyle")
    public String user_myStyle(
            Authentication authentication,
            @AuthenticationPrincipal UserDTO user,

            Model model
    ){
        if(!(Objects.nonNull(authentication))){
            return "redirect:/user/login";
        }

        List<StylesDTO> styles = productService.get_styles_by_user(user); // 스타일 전부
        model.addAttribute("styles", styles);

        // 상위 header에 사용되는 카테고리
        List<StyleCategoryDTO> styleCategories = styleProductService.get_categories();
        List<CategoryDTO> categories = productService.get_categories();
        model.addAttribute("styleCategories", styleCategories);
        model.addAttribute("categories", categories);

        return "user/myStyle";
    }

    @GetMapping("/myStyle-detail/{styleNo}")
    public String user_myStyle_detail(
            @PathVariable Integer styleNo,

            Authentication authentication,
            @AuthenticationPrincipal UserDTO user,

            Model model
    ){
        if(!(Objects.nonNull(authentication))){
            return "redirect:/user/login";
        }

        StylesDTO style = productService.get_style_by_user(user, styleNo); // 하나의 스타일
        model.addAttribute("style", style);

        // 상위 header에 사용되는 카테고리
        List<StyleCategoryDTO> styleCategories = styleProductService.get_categories();
        List<CategoryDTO> categories = productService.get_categories();
        model.addAttribute("styleCategories", styleCategories);
        model.addAttribute("categories", categories);

        return "user/myStyle-detail";
    }

    // 내 업로드 스타일
    @GetMapping("/myUpload")
    public String user_myUpload(
            Authentication authentication,
            @AuthenticationPrincipal UserDTO user,

            Model model
    ){
        if(!(Objects.nonNull(authentication))){
            return "redirect:/user/login";
        }

        List<StylesProductDTO> styles = productService.get_styles_style_codi_by_user(user);
        model.addAttribute("styles", styles);

        // 상위 header에 사용되는 카테고리
        List<StyleCategoryDTO> styleCategories = styleProductService.get_categories();
        List<CategoryDTO> categories = productService.get_categories();
        model.addAttribute("styleCategories", styleCategories);
        model.addAttribute("categories", categories);

        return "user/myUpload";
    }



}
