package boobook.business;

public interface IEmailService {
	void sendResetEmail(String token, String emailTo);
}
