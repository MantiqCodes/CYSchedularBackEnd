package io.giskard.scheduler.jpa.service;

import io.giskard.scheduler.jpa.dto.MailMessageDTO;

public interface  MailService {

	public int sendSimpleMail(MailMessageDTO	 mailMessageDTO);
}
