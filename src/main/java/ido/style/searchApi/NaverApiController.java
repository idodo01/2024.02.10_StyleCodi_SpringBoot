package ido.style.searchApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class NaverApiController {

    private final NaverApiService naverApiService;

    @Autowired
    public NaverApiController(NaverApiService naverApiService) {
        this.naverApiService = naverApiService;
    }

    @GetMapping("/search") // http://localhost:8080/api/products/search?query=dog 입력해서, 해당 값 10개 db에 넣기
    public List<ProductNaverShopDTO> searchAndSaveProducts(@RequestParam String query) {
        return naverApiService.fetchAndSaveNaverProducts(query);
    }
}