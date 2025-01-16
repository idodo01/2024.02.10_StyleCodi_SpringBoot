package ido.style.service;

import ido.style.dto.CartDTO;
import ido.style.dto.ProductDTO;
import ido.style.dto.UserDTO;
import ido.style.mapper.OrderMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class OrderService {
    @Autowired private OrderMapper orderMapper;


    // 해당 유저의 장바구니 item들을 가져오기
    public List<CartDTO> get_carts_by_user(UserDTO user){
        return orderMapper.selectCartsByUser(user);
    }

    // product: 유저가 추가하려는 상품 정보
    // user: 로그인된 유저
    public void add_cart(ProductDTO product, UserDTO user){
        CartDTO cart = new CartDTO();
        cart.setProduct(product);
        cart.setUser(user);

        orderMapper.insertCart(cart);

    }
    
    // 장바구니 수량 변경
    // cartNo: 변경하려는 장바구니 no
    // amount: 변경하려는 수량
    // user: 변경하는 사람이 맞는지 체크
    public void change_cart_amount(Integer cartNo, Integer amount, UserDTO user){
        CartDTO cartDTO = new CartDTO();
        cartDTO.setUser(user);
        cartDTO.setNo(cartNo);
        cartDTO.setAmount(amount);
        orderMapper.updateCartAmount(cartDTO);
    }


    // 장바구니 상품 제거
    // cartNo: 변경하려는 장바구니 no
    // user: 변경하는 사람이 맞는지 체크
    public void remove_cart(Integer cartNo, UserDTO user){
        CartDTO cartDTO = new CartDTO();
        cartDTO.setUser(user);
        cartDTO.setNo(cartNo);
        orderMapper.deleteCartByNoAndUser(cartDTO);
    }

    public void remove_carts(List<CartDTO> carts, UserDTO user){
        orderMapper.deleteCartsByNoAndUser(carts, user);
    }


    // 카트에 있는 총 금액을 구하기
    public Integer calculate_total_price(List<CartDTO> carts){
        return carts.parallelStream().mapToInt(cart -> {
            ProductDTO product = cart.getProduct();
            System.out.println(cart);
            Integer productPrice = product.getPrice();
            Integer amount = cart.getAmount();
            System.out.println(amount);
            return (productPrice) * amount;
        }).sum();
    }











}








