package net.letapp.documenter.services;

public interface MailingService {
	public void sendEmail(String body,String title,String to,String from) throws Exception;
}
