package ido.style.controller;

import ido.style.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@RestController
public class MainRestController {
    @Autowired private EmailService emailService;
    // 어떤 이메일(key)에 대해 생성된 인증번호(value)를 저장하는 저장소(repsitory)
    private final Map<String, String> emailCertRepository = new HashMap<>();
    
    // 사용자가 회원가입 창에서 휴대폰 인증을 시도하고 성공했을 경우 portone api의 imp_uid값을 받아온다
    @PostMapping("/tel/auth")
    public ResponseEntity<Void> post_tel_auth(
            @RequestBody String impUid,
            HttpSession session
    ){
        log.info("휴대폰 본인인증 값: "+ impUid);
        session.setAttribute("impUid", impUid);
        return ResponseEntity.ok().build();
    }
    
    
    // 사용자가 회원가입 창에서 이메일 인증확인 버튼을 클릭했을 시 동작
    @GetMapping("/email/auth")
    public ResponseEntity<Void> get_email_auth(
            @RequestParam String email, // 인증을 시도한 이메일
            @RequestParam String certNumber, // 발급된 인증번호
            HttpSession session // 회원가입 시 인증 완료 여부를 설정하기 위한 세션 
    ) {
        // 해당 이메일에 대해서 서버가 알고 있는 인증번호를 가져온다
        String createdCertNumber = emailCertRepository.get(email);
        // 해당 이메일으로 인증받은 적이 없다면, 혹은 사용자가 입력한 인증번호와 저장된 인증번호가 다르다면
        if (createdCertNumber == null || !createdCertNumber.equals(certNumber)) {
            return ResponseEntity.notFound().build(); // 인증 실패!
        }
        session.setAttribute("emailAuth", email); // 이 세션에서 인증이 성공했다고 판단한다
        emailCertRepository.remove(email); // 인증번호 일치 여부는 삭제해도 된다
        return ResponseEntity.ok().build(); // 인증 성공!
    }

    // 사용자가 회원가입 창에서 이메일 인증 버튼을 클릭했을 시 동작
    @PostMapping("/email/auth")
    public ResponseEntity<Void> post_email_auth(
            // 요청으로 받은 이 이메일로 인증번호를 전송할것이다.
            @RequestBody String email_to
    ){
        // 인증번호를 전송하는 이메일 서비스 실행!
        try {
            String certNumber = emailService.send_cert_mail(email_to);
            // 해당 이메일에 대해 생성된 인증번호를 저장하기
            emailCertRepository.put(email_to, certNumber);
            // 정상 완료. 200 반환.
            return ResponseEntity.ok().build();
        }catch (MessagingException e){
            log.error("이메일 전송 중 오류 발생..!: " + e.getMessage());
        }
        // 에러라면 우리 서비스에서 에러니까 500 에러가 맞다.
        return ResponseEntity.internalServerError().build();
    }
    
    
    
    
}
