package test.sendgrid.java;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import java.io.IOException;

/**
 * 22/07/2020 23:29:27
 *
 * @author murilotuvani
 */
public class SendIt {

    public static void main(String[] args) throws IOException {
        Email from = new Email("rfq@cartola.net");
        String subject = "Sending with SendGrid is Fun";
        Email to = new Email("murilo.tuvani@gmail.com");
        Content content = new Content("text/plain", "and easy to do anywhere, even with Java");
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid("chave gerada no site do send-grid");
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }
    }
}
