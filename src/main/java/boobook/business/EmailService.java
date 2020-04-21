package boobook.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailService implements IEmailService {

	@Autowired
	public JavaMailSender sender;
	
	@Override
	public void sendResetEmail(String token, String emailTo) {
		String recoveryLink = "http://localhost:8081/log/resetpassword?token="+token;
		
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(emailTo);
		mail.setFrom("Boobook");
		mail.setSubject("Récupération du mot de passe");
		mail.setText("Cliquez sur le lien suivant pour réinitialiser votre mot de passe : \n \n"+recoveryLink);
		
		sender.send(mail);
	}

}
