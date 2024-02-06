package com.hukahuka.mail.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.hukahuka.mail.domain.UserMail;
import com.hukahuka.user.bo.UserBO;

@Service
public class MailBO {
	
	@Autowired
	private JavaMailSender mailSender;

	// 임시 비밀번호 랜덤 숫자 10개 생성
    public String getTempPassword(){
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String str = "";

        // 문자 배열 길이의 값을 랜덤으로 10개를 뽑아 구문을 작성함
        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        
        return str;
    }
    
    // 메일 내용을 생성하고 임시 비밀번호로 회원 비밀번호를 변경
    public UserMail createMailAndChangePassword(String email, String str) {
        UserMail mail = new UserMail();
        mail.setAddress(email);
        mail.setTitle("후카후카(스티커 샵) 임시비밀번호 안내 이메일 입니다.");
        mail.setMessage("안녕하세요. 후카후카(스티커 샵) 임시비밀번호 안내 관련 이메일 입니다. \n" + "회원님의 임시 비밀번호는"
                + str + " 입니다. \n" + "로그인 후에 비밀번호를 변경해주시길 바랍니다.");
        return mail;
    }
    
    // UserMail을 바탕으로 실제 이메일 전송
    public void mailSend(UserMail mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mail.getAddress());
        message.setSubject(mail.getTitle());
        message.setText(mail.getMessage());
        message.setFrom("suhyun9378@naver.com");
        message.setReplyTo("suhyun9378@naver.com");
        mailSender.send(message);
    }
    
    
}
