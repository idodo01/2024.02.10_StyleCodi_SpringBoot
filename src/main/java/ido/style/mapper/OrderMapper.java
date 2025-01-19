package ido.style.mapper;

import ido.style.dto.DibsDTO;
import ido.style.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    /****************** 주문 ********************/
    List<DibsDTO> selectCartsByNumberAndUser( // 주문하려고 하는 장바구니 상품들 가져오기
                                              List<Integer> cartNumbers,
                                              UserDTO user
    );
    /****************** 장바구니 *************************/
    List<DibsDTO> selectCartsByUser(UserDTO user);
    void insertCart(DibsDTO cart); // 장바구니에 상품 정보 추가
    void updateCartAmount(DibsDTO cart); // 장바구니의 해당 장바구니 수량 변경
    void deleteCartByNoAndUser(DibsDTO cart); // 장바구니의 해당 상품 제거
    void deleteCartsByNoAndUser(List<DibsDTO> carts, UserDTO user); // 해당하는 모든 장바구니 상품들 제거
}
