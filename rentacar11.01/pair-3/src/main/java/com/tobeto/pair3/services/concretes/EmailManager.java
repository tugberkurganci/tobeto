package com.tobeto.pair3.services.concretes;

import com.tobeto.pair3.services.abstracts.EmailService;
import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
@RequiredArgsConstructor
public class EmailManager implements EmailService {

    private JavaMailSenderImpl mailSender;

    private final MessageSource messageSource;

    @PostConstruct
    public  void initialize(){

        this.mailSender=new JavaMailSenderImpl();
        mailSender.setHost("smtp.ethereal.email");
        mailSender.setPort(587);
        mailSender.setUsername("kameron.cassin@ethereal.email");
        mailSender.setPassword("gtyRSz2CHD2GnNVXaq");

        Properties properties=mailSender.getJavaMailProperties();
        properties.put("mail.smtp.starttls.enable","true");


    }

    String activationEmail = """
            <html>
                <body>
                    <h1>${title}</h1>
                    <a href="${url}">${clickHere}</a>
                </body>
            </html>
            """;
    public void sendActivationEmail(String email,String activationToken)  {
        var activationUrl = "smtp.ethereal.email" + "/activation/" + activationToken;
        var title = "activation";
        var clickHere = "activate it";


        var mailBody = activationEmail
                .replace("${url}", activationUrl)
                .replace("${title}", title)
                .replace("${clickHere}", clickHere);

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");

        try {
            message.setFrom("noreply@rentacar.com");
            message.setTo(email);
            message.setSubject(title);
            message.setText(mailBody,true);
        }catch (MessagingException e){
            e.printStackTrace();
        }

        this.mailSender.send(mimeMessage);

    }
    public void sendReCreatePassword(String email, String passToken)  {
        var activationUrl = "smtp.ethereal.email" + "/create-password/" + passToken;
        var title = "change your password";
        var clickHere = "change your password";


        var mailBody = activationEmail
                .replace("${url}", activationUrl)
                .replace("${title}", title)
                .replace("${clickHere}", clickHere);

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");

        try {
            message.setFrom("noreply@rentacar.com");
            message.setTo(email);
            message.setSubject(title);
            message.setText(mailBody,true);
        }catch (MessagingException e){
            e.printStackTrace();
        }

        this.mailSender.send(mimeMessage);

    }
}