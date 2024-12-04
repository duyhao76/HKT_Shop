package vn.HKT.services.Impl;

import java.util.Properties;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class EmailService {

	public void sendPasswordResetEmail(String email, String token) {
		final String username = "trinhuutho@gmail.com"; // Địa chỉ email
		final String password = "wrwqwpdmtyyinknh"; // Mật khẩu ứng dụng gmail

		// Thiết lập các thuộc tính
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.protocols", "TLSv1.2");
		props.put("mail.smtp.socketFactory.port", "587");

		// Tạo một phiên
		Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			// Tạo một đối tượng Message
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			message.setSubject("TomeKeeper-Khôi phục mật khẩu");
			message.setText("Để khôi phục mật khẩu của bạn, hãy nhấp vào liên kết sau: "
					+ "https://localhost:8443/Project_Sach/authentication/reset-password?action=reset&token=" + token);

			// Gửi email
			Transport.send(message);
			System.out.println("Email đã được gửi thành công");

		} catch (MessagingException e) {
			System.err.println("Lỗi khi gửi email: " + e.getMessage());
			e.printStackTrace(); // In ra thông tin lỗi chi tiết
		}
	}

}
