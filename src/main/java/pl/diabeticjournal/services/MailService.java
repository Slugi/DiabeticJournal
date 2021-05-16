package pl.diabeticjournal.services;

import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import pl.diabeticjournal.entity.User;

import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


@Service
@AllArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;
    private final FileService fileService;
    private final MeasurementService measurementService;


    public void sendMail(String to, String subject, String text, boolean isHtmlContent)
            throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(text, isHtmlContent);
        javaMailSender.send(mimeMessage);
    }


    public void sendMailWithAttachment(String to, String subject, String text, boolean isHtmlContent, User user) throws MessagingException, IOException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText(text);
        MimeBodyPart attachmentPart = new MimeBodyPart();
        ByteArrayOutputStream out = fileService.writeExcelMeasurements(measurementService.getMeasurementsByUser(user));
        attachmentPart.setDataHandler(new DataHandler(new ByteArrayDataSource(out.toByteArray(), "application/vnd.ms-excel")));
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        multipart.addBodyPart(attachmentPart);
        mimeMessage.setContent(multipart);
        javaMailSender.send(mimeMessage);
    }

}
