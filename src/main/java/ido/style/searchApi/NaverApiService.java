package ido.style.searchApi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class NaverApiService {

    @Value("${naver.client.id}")
    private String clientId;

    @Value("${naver.client.secret}")
    private String clientSecret;

    private final String NAVER_API_URL = "https://openapi.naver.com/v1/search/shop.json?query=";
    private final ProductNaverShopMapper productNaverShopMapper;
    private final ObjectMapper objectMapper;

    public NaverApiService(ProductNaverShopMapper productNaverShopMapper, ObjectMapper objectMapper) {
        this.productNaverShopMapper = productNaverShopMapper;
        this.objectMapper = objectMapper;
    }

    public List<ProductNaverShopDTO> fetchAndSaveNaverProducts(String query) {
        RestTemplate restTemplate = new RestTemplate();

        // 검색어 인코딩
        String encodedQuery;
        try {
            encodedQuery = URLEncoder.encode(query, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패", e);
        }

        String url = NAVER_API_URL + encodedQuery;

        // API 요청 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", clientId);
        headers.set("X-Naver-Client-Secret", clientSecret);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        // JSON 파싱 및 저장
        return saveProductsFromJson(response.getBody());
    }

    private List<ProductNaverShopDTO> saveProductsFromJson(String jsonResponse) {
        try {
            JsonNode rootNode = objectMapper.readTree(jsonResponse); // JSON 파싱
            JsonNode itemsArray = rootNode.path("items"); // "items" 배열을 가져옴

            // JSON 데이터를 DTO로 변환하고 DB에 저장
            List<ProductNaverShopDTO> products =
                    StreamSupport.stream(itemsArray.spliterator(), false)
                            .map(item -> {
                                ProductNaverShopDTO product = new ProductNaverShopDTO();

                                // <b> 태그 제거
                                String title = item.path("title").asText();
                                title = title.replaceAll("<b>", "").replaceAll("</b>", "");  // <b> 태그 제거

                                product.setTitle(title);  // 수정된 title 값 설정
                                product.setLink(item.path("link").asText());
                                product.setImage(item.path("image").asText());
                                product.setPrice(item.path("lprice").asText("0"));  // 가격 기본값 설정
                                product.setCategory(item.path("category4").asText("Unknown")); // 기본값 설정

                                return product;
                            })
                            .collect(Collectors.toList());

            productNaverShopMapper.insertProducts(products); // MyBatis를 사용하여 DB 저장
            return products;

        } catch (Exception e) {
            throw new RuntimeException("JSON 파싱 중 오류 발생", e);
        }
    }
}