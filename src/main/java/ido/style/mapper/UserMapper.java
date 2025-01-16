package ido.style.mapper;

import ido.style.dto.SnsInfoDTO;
import ido.style.dto.StyleProductDTO;
import ido.style.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    UserDTO selectUserById(String id);
    UserDTO selectUserByCi(String ci);
    void insertUser(UserDTO user);
    void insertSnsInfo(SnsInfoDTO snsInfo);

    void insertStyleProduct(StyleProductDTO styleProduct);
    void insertStyleProductImages(StyleProductDTO styleProduct);

}
