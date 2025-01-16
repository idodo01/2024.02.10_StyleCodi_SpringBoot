package ido.style.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Random;

@Log4j2
@Service
public class EmailService {
    private final String FROM = "fnvit@naver.com";
    private final String EMAIL_CERT_TEMPLATE = "/mail/email-auth-template.html";
    @Autowired private JavaMailSender mailSender;
    @Autowired private TemplateEngine templateEngine;

    // 인증번호 생성 후, TO 에게 메일을 보낸 후 생성된 인증번호를 RETURN하는 인증 메일
    public String send_cert_mail(String TO) throws MessagingException {
        log.info("메일 템플릿 생성 중...");
        Context context = new Context();
        // 4자리의 랜덤 번호 구성
        StringBuilder certNumber = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < 4; i++) {
            certNumber.append(random.nextInt(10)); // 0 - 9 까지의 랜덤 숫자 하나 생성
        }
        context.setVariable("certNumber", certNumber.toString());
        // 메일을 전송한다
        send_mail(EMAIL_CERT_TEMPLATE, context, "[KOREA STORE] 회원가입 인증 메일입니다", TO);
        // 생성된 인증번호를 RETURN
        return certNumber.toString();
    }

    // 실제로 메일을 구성해서 발송하기!
    private void send_mail(String template, Context context, String SUBJECT, String TO) throws MessagingException {
        log.info("메일 템플릿 생성 중...");
        // template의 html의 내용을 context를 사용해서 String형태로 변경했다!
        String templateMailContext = templateEngine.process(template, context);
        // 해당 String을 메세지 내용으로 해서 메일을 전송한다
        log.info("메일 전송 중...");
        MimeMessage message = mailSender.createMimeMessage();
        // helper를 통해 간단히 메일을 구성
        // MimeMessageHelper에 true를 설정시, 파일 첨부 가능! + 인코딩 설정도 가능!
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom(FROM); // 보내는 사람 (application.properties에 작성한 유저밖에 안됩니다..!)
        helper.setTo(TO); // 받는 사람. 아무나.
        helper.setSubject(SUBJECT); // 메일 제목
        helper.setText(templateMailContext, true); // 메일 내용, true 설정 시 HTML형식이라고 설정함!
        // 생성한 MimeMessage를 SEND
        mailSender.send(message);
        log.info("메일 전송 완료!");
    }
}
