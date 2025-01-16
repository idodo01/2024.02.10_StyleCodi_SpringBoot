package ido.style.mapper;

import ido.style.dto.CartDTO;
import ido.style.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    /****************** 주문 ********************/
    List<CartDTO> selectCartsByNumberAndUser( // 주문하려고 하는 장바구니 상품들 가져오기
            List<Integer> cartNumbers, 
            UserDTO user
    );
    /****************** 장바구니 *************************/
    List<CartDTO> selectCartsByUser(UserDTO user);
    void insertCart(CartDTO cart); // 장바구니에 상품 정보 추가
    void updateCartAmount(CartDTO cart); // 장바구니의 해당 장바구니 수량 변경
    void deleteCartByNoAndUser(CartDTO cart); // 장바구니의 해당 상품 제거
    void deleteCartsByNoAndUser(List<CartDTO> carts, UserDTO user); // 해당하는 모든 장바구니 상품들 제거
}
