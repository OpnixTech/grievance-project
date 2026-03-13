package com.grievance.resolve.service;

import org.springframework.stereotype.Service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import io.jsonwebtoken.io.IOException;


@Service
public class EmailService {

//	public void sendOtpEmail(String to, String otp) {
//		String from="dewangbackup1@gmail.com";
//		String host="smtp.gmail.com";
//		Properties props=new Properties();
//		props.put("mail.smtp.host", host);
//		props.put("mail.smtp.port", "587");
//		props.put("mail.smtp.starttls.enable", "true");
//		props.put("mail.smtp.auth", "true");
//		
//		Session session=Session.getInstance(props, new Authenticator() {
//		protected PasswordAuthentication getPasswordAuthentication() {
//			return new PasswordAuthentication("dewangbackup1@gmail.com", "zsjqpnhxzwpxrujm");
//		}
//		});
//		
//		try {
//			MimeMessage message=new MimeMessage(session);
//			message.setFrom(from);
//			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//			message.setSubject("Your OTP Code from Grievance Portal");
//			message.setText("Your OTP is: "+otp);
//			Transport.send(message);
//		}catch (MessagingException e) {
//			e.printStackTrace();
//		}
//	}
	
	 private final String SENDGRID_API_KEY = System.getenv("SENDGRID_API_KEY");

	 public void sendOtpEmail(String to, String otp) throws java.io.IOException {
		    try {
		        Email from = new Email("no-reply@grievanceportal.com");
		        String subject = "Your OTP Code from Grievance Portal";
		        Email recipient = new Email(to);
		        Content content = new Content("text/plain", "Your OTP is: " + otp);
		        Mail mail = new Mail(from, subject, recipient, content);

		        SendGrid sg = new SendGrid(SENDGRID_API_KEY);
		        Request request = new Request();
		        request.setMethod(Method.POST);
		        request.setEndpoint("mail/send");
		        request.setBody(mail.build());
		        sg.api(request);

		    } catch (IOException e) {
		        System.err.println("Failed to send OTP email: " + e.getMessage());
		    }
		}
	
}
