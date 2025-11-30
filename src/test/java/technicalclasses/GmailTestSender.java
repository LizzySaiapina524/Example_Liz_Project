package technicalclasses;

import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.util.Properties;

public class GmailTestSender {

    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "465"; // SSL
    private static final String USERNAME = "elizaveta.saiapina.qa@gmail.com";
    private static final String RECEIVER = "elizaveta.saiapina.qa@gmail.com";
    private static final String APP_PASSWORD = System.getenv("ALLURE_MAIL_PASSWORD");

    private static final String LOCAL_REPORT_URL = "http://localhost:8080/";

    // Метод для отправки ссылки на локальный сервер с отчетом
    public static void sendAllureReportLink() {
        if (APP_PASSWORD == null || APP_PASSWORD.trim().isEmpty()) {
            System.err.println("❌ Переменная окружения ALLURE_MAIL_PASSWORD не установлена!");
            return;
        }

        try {
            sendEmailWithLink(
                    SMTP_HOST,
                    SMTP_PORT,
                    USERNAME,
                    APP_PASSWORD,
                    RECEIVER,
                    "Ссылка на Allure Report тестов",
                    "Последние результаты тестов можно посмотреть по ссылке: " + LOCAL_REPORT_URL
            );

            System.out.println("✅ Ссылка на Allure Report отправлена на: " + RECEIVER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void sendEmailWithLink(String host, String port,
                                          final String userName, final String password,
                                          String toAddress,
                                          String subject, String text) throws Exception {

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");

        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };

        Session session = Session.getInstance(props, auth);

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(userName));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
        msg.setSubject(subject);

        MimeBodyPart textPart = new MimeBodyPart();
        textPart.setText(text, "UTF-8");

        MimeMultipart multipart = new MimeMultipart();
        multipart.addBodyPart(textPart);

        msg.setContent(multipart);

        Transport.send(msg);
    }
}

