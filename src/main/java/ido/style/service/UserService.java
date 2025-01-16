package ido.style.service;

import ido.style.dto.StyleProductDTO;
import ido.style.dto.UserDTO;
import ido.style.mapper.UserMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Log4j2
@Service
public class UserService {
    @Autowired private UserMapper userMapper;
    @Autowired private PasswordEncoder passwordEncoder;

    public boolean join_user(UserDTO userDTO) {
        // 유저가 중복인지 찾는다
        UserDTO findUser = userMapper.selectUserById(userDTO.getId());
        if(Objects.nonNull(findUser)){ // findUser != null
            log.error("가입 유저가 중복되었다!");
            return false; // 가입 실패 판단
        }
        // 유저 DB에 등록하기 직전에 패스워드를 인코딩해서 넣어준다 
        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
        userDTO.setPassword(encodedPassword);
        // 실제로 회원가입을 시킨다 (DB에 데이터를 저장한다)
        userMapper.insertUser(userDTO);
        log.info("가입 유저가 등록되었다!");
        return true; // 가입 성공 판단
    }

    // 상품과 해당 상품의 옵션들을 DB에 INSERT
    public void add_style_product(StyleProductDTO styleProduct){
        // 상품을 전달해서 DB에 상품의 정보를 추가하기!
        userMapper.insertStyleProduct(styleProduct);

        userMapper.insertStyleProductImages(styleProduct);

    }
}
