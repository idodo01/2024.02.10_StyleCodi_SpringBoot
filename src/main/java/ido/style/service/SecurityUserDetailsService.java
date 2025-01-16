package ido.style.service;

import ido.style.dto.UserDTO;
import ido.style.mapper.UserMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class SecurityUserDetailsService implements UserDetailsService{
    @Autowired UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("username: " + username);
        // 해당 아이디를 가지는 유저를 찾기! (DB에서)
        UserDTO user = userMapper.selectUserById(username);
        log.info("찾은 유저: " + user);
        // DB에 해당 아이디의 유저가 없다? 로그인 실패!
        if(user == null) {
            throw new UsernameNotFoundException(username);
        }
        
        var pe = new BCryptPasswordEncoder();
        log.info("이 사람 패스워드는 1234인가?:" + pe.matches("1234", user.getPassword()) );
        log.info("1234의 인코딩 결과:" + pe.encode("1234"));
        // DB에 해당 아이디의 유저가 있다? 그 유저가 가지는 패스워드와 사용자가 입력한 패스워드 비교해줘!
        return user;
    }
}
