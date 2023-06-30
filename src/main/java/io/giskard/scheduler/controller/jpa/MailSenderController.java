package io.giskard.scheduler.controller.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import io.giskard.scheduler.jpa.dto.MailMessageDTO;
import io.giskard.scheduler.jpa.service.MailService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class MailSenderController {

	@Autowired
	MailService mailService;

	@PostMapping(value = "sendEmail/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void sendEmail(@RequestBody MailMessageDTO sms) {
		
		if(sms!=null)
		{
//		ResponseEntity<Object> resp = new ResponseEntity<Object>(
//				"Failed to send email to  " + mailMessageDTO.getRecipient(), HttpStatus.BAD_REQUEST);

			MailMessageDTO mms=new MailMessageDTO();
					mms.setRecipient(sms.getRecipient());
					mms.setSubject(sms.getSubject());
					mms.setMailBody(sms.getMailBody());
					mms.setAttachment(sms.getAttachment());
					
		int responseCode = this.mailService.sendSimpleMail(mms);
//		if (responseCode == 1) {
//			return ResponseEntity.ok().body("Email Sent To" + sms.getRecipient()+" . Did you check your Inbox and also Spam forlders ? ");
//		}}
//		return null;

	}
	}
}
