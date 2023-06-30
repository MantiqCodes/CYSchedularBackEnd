package io.giskard.scheduler.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import io.giskard.scheduler.jpa.dto.MailMessageDTO;

@Service

public class MailServiceImpl implements MailService {

	
	@Autowired
	JavaMailSender javaMailSender ;
	@Value("${spring.mail.username}")
	private String sender;
	
	@Override
	public int  sendSimpleMail(MailMessageDTO d) {
		try
		{
			SimpleMailMessage sm=new SimpleMailMessage();
			sm.setFrom(sender);
			sm.setTo(d.getRecipient());
			sm.setSubject(d.getSubject());
			sm.setText(d.getMailBody());
			javaMailSender.send(sm);
			return 1;
		}
		catch(Exception e )
		{
			e.printStackTrace();
		}
		
		
		return -1;
	}

}
