package io.giskard.scheduler.jpa.dto;

public class MailMessageDTO {

	private String recipient;
	private String subject;
	private String mailBody;
	private String attachment;
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMailBody() {
		return mailBody;
	}
	public void setMailBody(String mailBody) {
		this.mailBody = mailBody;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	
//	MailMessageDTO={
//		"recipient":"",
//		"subject":"",
//		"mailBody":"",
//		"attachment":""
//		
//	}
//	
}
