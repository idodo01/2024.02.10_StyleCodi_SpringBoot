package ido.style.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
public class UserDTO implements UserDetails, OAuth2User {
    @NotBlank
    @Length(min = 4, max = 15)
    @Pattern(regexp = "^[a-z][0-9a-zA-Z]*$")
    private String id;
    private String ci;
    @NotBlank
    @Pattern(regexp = "^[0-9a-zA-Z~!@#$%^&*()_=+.-]{4,10}")
    private String password;
    @NotBlank
    @Pattern(regexp = "^(010|011|017|019|018)-[0-9]{3,4}-[0-9]{4}$")
    private String tel;
    @NotBlank 
    @Email
    private String email;
    private String name;
    private List<SnsInfoDTO> snsInfo; // 이 유저가 로그인 할 때 사용한 SNS 데이터
    private String profileImage;




    @Override
    public String getName() {
        return this.id;
    }




    @Override
    public Map<String, Object> getAttributes() {
        return Map.of();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("TEMP"));
    }

    @Override
    public String getUsername() {
        return this.id;
    }
}
