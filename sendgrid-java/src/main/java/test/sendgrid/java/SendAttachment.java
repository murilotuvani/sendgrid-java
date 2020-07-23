package test.sendgrid.java;

import com.sendgrid.Attachments;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

/**
 * 23/07/2020 11:48:45
 *
 * @author murilotuvani
 */
public class SendAttachment {

    public static void main(String[] args) throws IOException {
        File file = new File("../sendGridPrincing.pdf");
        if (file.exists()) {
            Email from = new Email("rfq@cartola.net");
            String subject = "Sending with SendGrid is Fun";
            Email to = new Email("murilo.tuvani@gmail.com");
            Content content = new Content("text/html", "and easy to do anywhere, even with Java");

            Mail mail = new Mail(from, subject, to, content);

            Attachments attachment = new Attachments();
            byte [] conteudo = Files.readAllBytes(file.toPath());
            String conteudo64 = Base64.getEncoder().encodeToString(conteudo);
            attachment.setContent(conteudo64);
            attachment.setFilename(file.getName());
            attachment.setType("application/pdf");
            mail.addAttachments(attachment);

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

        } else {
            System.out.println("Nao encontrou o arquivo : " + file.getAbsolutePath());
        }
    }

}
