package ido.style.mapper;

import ido.style.dto.ClothesDTO;
import ido.style.dto.SnsInfoDTO;
import ido.style.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    UserDTO selectUserById(String id);
    UserDTO selectUserByCi(String ci);
    void insertUser(UserDTO user);
    void insertSnsInfo(SnsInfoDTO snsInfo);

    void insertClothes(ClothesDTO clothes, UserDTO user);
    void insertClothesImages(ClothesDTO clothes);

    List<ClothesDTO> selectClothes(
            Integer categoryNo, // 상품의 카테고리
            UserDTO user,
            String sort // 상품의 정렬 방식
    );

}
