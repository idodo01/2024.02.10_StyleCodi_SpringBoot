package ido.style.controller;

import ido.style.dto.ProductDTO;
import ido.style.dto.UserDTO;
import ido.style.service.OrderService;
import ido.style.service.PortOneService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Log4j2
@RestController
public class OrderRestController {
    @Autowired private OrderService orderService;
    @Autowired private PortOneService portOneService;
    
    // 장바구니에 상품을 추가하기
    @PostMapping("/cart")
    public ResponseEntity<Void> post_cart(
            @RequestBody ProductDTO product,
            @AuthenticationPrincipal UserDTO userDTO
    ){
        if(Objects.isNull(userDTO)){
            log.warn("로그인 되지 않은 유저가 장바구니 삽입을 시도");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        // 해당 상품과 유저를 전달해서 카트에 추가하기
        orderService.add_cart(product, userDTO);
        return ResponseEntity.ok().build();
    }
    
    // 카트의 상품 수량을 변경하기
    @PatchMapping("/cart/{cartNo}")
    public ResponseEntity<Void> patch_cart(
            @PathVariable Integer cartNo,
            @RequestBody Integer amount,
            @AuthenticationPrincipal UserDTO user
    ){
        orderService.change_cart_amount(cartNo, amount, user);
        return ResponseEntity.ok().build();
    }

    // 카트의 상품을 제거하기
    @DeleteMapping("/cart/{cartNo}")
    public ResponseEntity<Void> delete_cart(
            @PathVariable Integer cartNo,
            @AuthenticationPrincipal UserDTO user
    ){
        orderService.remove_cart(cartNo, user);
        return ResponseEntity.ok().build();
    }
    
    /*********************************************/

    
}
