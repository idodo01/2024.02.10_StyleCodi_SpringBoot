package ido.style.service;

import ido.style.dto.SnsInfoDTO;
import ido.style.dto.UserDTO;
import ido.style.mapper.UserMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Log4j2
@Service
// OAuth2 Client를 통해 로그인을 시도하게 되었을 시, 로그인 성공하면 여기가 자동 실행된다
public class SecurityOAuth2UserService extends DefaultOAuth2UserService {
    @Autowired private UserMapper userMapper;
    // 원래는 소셜 로그인 시 ci를 가져와야하지만, 테스트용에서는 ci를 가져올 수 없으므로, 임의로 만들어줌
    // 운영 단계에서는 사용하지 않아야 함
    private final String CI = "TEST_CI2";

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info(userRequest);
        ClientRegistration registration = userRequest.getClientRegistration();
        String clientName = registration.getClientName().toUpperCase();
        OAuth2User oAuth2User = super.loadUser(userRequest); // 기존 로그인 절차를 실행시킨다!
        var attributes = oAuth2User.getAttributes();
        log.info(oAuth2User); // 로그인한 유저의 정보를 출력
        SnsInfoDTO snsInfoDTO = switch (clientName){
            case "KAKAO" -> get_kakao_sns_info(attributes);
            case "NAVER" -> get_naver_sns_info(attributes);
            case "GOOGLE" -> get_google_sns_info(attributes);
            default -> null;
        };
        // KAKAO, NAVER, GOOGLE 로그인이 아니라면 null. 로그인 에러
        if(Objects.isNull(snsInfoDTO)){
            throw new OAuth2AuthenticationException("INVALID SNS");
        }

        // 같은 ci를 가지는 유저를 DB에서 찾는다
        UserDTO existsUser = userMapper.selectUserByCi(snsInfoDTO.getCi());
        log.info(existsUser);
        // 기존에 해당 ci를 가지는 유저가 없으면
        if(Objects.isNull(existsUser)){
            throw new OAuth2AuthenticationException("INVALID USER");
        }
        // 이 유저가 현재 로그인하려고 하는 SNS 정보를 가지고 있는지 확인
        boolean isSnsExists = existsUser.getSnsInfo().stream()
                .anyMatch(snsInfo -> snsInfo.getName().equals(clientName));
        // 기존에 이 SNS가 연동된 적이 없다면
        if(!isSnsExists) {
            // 유저에게 연동된 SNS 정보를 추가해준다
            existsUser.getSnsInfo().add(snsInfoDTO);
            snsInfoDTO.setUserId(existsUser.getId());
            log.info("SNS INFO DB 저장:" + snsInfoDTO);
            // DB에 해당 유저의 SNS 연결 정보를 추가로 저장한다
            userMapper.insertSnsInfo(snsInfoDTO);
        }
        // UserDTO를 반환한다. 인증된 유저가 된다 (스프링 시큐리티가 관리)
        return existsUser;
    }
    // 구글로 로그인 했을 시 구글 로그인 정보를 담는 User를 만들어 반환
    SnsInfoDTO get_google_sns_info(Map<String, Object> attributes) {
        String id = attributes.get("sub").toString(); // 구글 고유 ID
        return SnsInfoDTO.builder()
                .id(id)
                .ci(CI)
                .name("GOOGLE")
                .build();
    }
    // 카카오로 로그인 했을 시 구글 로그인 정보를 담는 User를 만들어 반환
    SnsInfoDTO get_kakao_sns_info(Map<String, Object> attributes) {
        String id = attributes.get("id").toString(); // 구글 고유 ID
        return SnsInfoDTO.builder()
                .id(id)
                .ci(CI)
                .name("KAKAO")
                .build();
    }
    // 네이버로 로그인 했을 시 구글 로그인 정보를 담는 User를 만들어 반환
    SnsInfoDTO get_naver_sns_info(Map<String, Object> attributes) {
        Map<String, String> response = (Map<String, String>) attributes.get("response");
        String id = response.get("id"); // 구글 고유 ID
        return SnsInfoDTO.builder()
                .id(id)
                .ci(CI)
                .name("NAVER")
                .build();
    }






}
