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
                                product.setPrice(Integer.valueOf(item.path("lprice").asText("0")));  // 가격 기본값 설정


                                // 카테고리 값들을 모두 가져오기
                                String category1 = item.path("category1").asText("Unknown");
                                String category2 = item.path("category2").asText("Unknown");
                                String category3 = item.path("category3").asText("Unknown");
                                String category4 = item.path("category4").asText("Unknown");

                                // 카테고리 값들을 출력하여 확인 (로깅하거나 콘솔에 출력)
                                System.out.println("Category 1: " + category1);
                                System.out.println("Category 2: " + category2);
                                System.out.println("Category 3: " + category3);
                                System.out.println("Category 4: " + category4);

                                // 카테고리 조건에 맞는 경우만 처리
                                if(category3.equals("카디건")
                                        ||category3.equals("점퍼")
                                        ||category3.equals("재킷")) {
                                    product.setCategory(6);
                                } else if (category3.equals("니트/스웨터")
                                        ||category3.equals("블라우스/셔츠")
                                        ||category3.equals("티셔츠")) {
                                    product.setCategory(5);
                                } else if(category3.equals("바지")
                                        ||category3.equals("스커트")){
                                    product.setCategory(4);
                                } else if(category2.equals("여성신발")
                                || category3.equals("여성신발")){
                                    product.setCategory(3);
                                } else if(category2.equals("여성가방")){
                                    product.setCategory(2);
                                } else {
                                    product.setCategory(1);
                                }

//                                2,BAG
//                                3,SHOSE
//                                4,BOTTOM
//                                5,TOP
//                                6,OUTER

                                return product; // 해당 아이템을 반환
//                                // 조건에 맞지 않으면 null 반환하여 필터링
//                                return null;

                            })
//                            // null 값을 제외하고 리스트로 변환
//                            .filter(product -> product != null)
                            .collect(Collectors.toList());


            productNaverShopMapper.insertProducts(products); // MyBatis를 사용하여 DB 저장
            return products;

        } catch (Exception e) {
            throw new RuntimeException("JSON 파싱 중 오류 발생", e);
        }
    }
}