package com.ultimatetek.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailConfig {

	/*@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setUsername("info@ultimatetek.in");
		mailSender.setPassword("infoU#2020");

		Properties props = mailSender.getJavaMailProperties();
		props.setProperty("mail.smtp.host", "smtp.zoho.com");
		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.startssl.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");
		props.put("mail.store.protocol", "pop3");
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.debug.auth", "true");
		props.setProperty("mail.pop3.socketFactory.fallback", "false");
		return mailSender;
	}*/

	@Bean
	public JavaMailSender primarySender() {
		final String username = "";
        final String password = "";
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setUsername(username);
		mailSender.setPassword(password);

		Properties props = mailSender.getJavaMailProperties();
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.port", "587");
//		props.setProperty("mail.smtp.host", "smtp.siddagangatravels.in");
//		props.setProperty("mail.smtp.port", "587");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		//props.put("mail.debug", "true");
		return mailSender;
	}
	
	@Bean
	public JavaMailSender billingEmailSender() {
		final String username = "billing@siddagangatravels.in";
        final String password = "SiddaBook1969";
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setUsername(username);
		mailSender.setPassword(password);

		Properties props = mailSender.getJavaMailProperties();
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.port", "587");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		//props.put("mail.debug", "true");
		return mailSender;
	}

	@Bean
	public JavaMailSender accountEmailSender() {
		final String username = "accounts@siddagangatravels.in";
        final String password = "SiddaBook1969";
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setUsername(username);
		mailSender.setPassword(password);

		Properties props = mailSender.getJavaMailProperties();
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.port", "587");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		//props.put("mail.debug", "true");
		return mailSender;
	}

}
