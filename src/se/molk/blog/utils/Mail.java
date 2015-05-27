package se.molk.blog.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;




public class Mail {
    public void BlogMail(String contactName, String contactEmail, String contactMessage){
        //Start of email code:
        final String username = "wehbechristelle@gmail.com";
        final String password = "";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com"); //This is the smtp server address
        props.put("mail.smtp.port", "587"); //This is the port for the smtp server

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {

                        protected PasswordAuthentication getPasswordAuthentication(){
                            return new PasswordAuthentication(username, password);
                        }
                 });

        try {
            Message message= new MimeMessage(session);
            message.setFrom(new InternetAddress(contactEmail));//FROM EMAIL
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("wehbechristelle@gmail.com"));//TO EMAIL
            message.setFileName(contactName); //SETS SUBJECT OF EMAIL
            message.setContent(contactMessage, "text/html; charset=utf-8");//SETS THE CONTENT BODY OF THE EMAIL
            Transport.send(message);//SENDS THE ENTIRE MESSAGE

            System.out.println("Was the email sent: Done!");//Verifies that the code fired
        }catch (MessagingException e){
            throw new RuntimeException(e);//If the email address is bad or doesn't exist
        }
    }   //end of email code
}