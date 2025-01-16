package ido.style.service;

import ido.style.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserRestService {
    @Autowired
    private UserMapper userMapper;
    // id를 통해 유저를 찾기
    public boolean find_user_by_id(String userId){
        // 찾았으면 false(사용 불가), 못찾았으면 true(사용 가능)
        return Objects.isNull(userMapper.selectUserById(userId));
    }
}







