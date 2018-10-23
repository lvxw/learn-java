package com.funshion.artemis.mail;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.event.TransportEvent;
import javax.mail.event.TransportListener;
import javax.mail.internet.*;
import java.util.Properties;

public class SendMail {

    private enum MailSentState {
        INITIAL, SUCCESS, FAILURE, PARTIALLY_SUCCESS,
    }


    private static class MailSentStateFuture {
        private MailSentState state = MailSentState.INITIAL;
        synchronized void waitForReady() throws InterruptedException {
            if (state == MailSentState.INITIAL) {
                wait();
            }
        }
        synchronized MailSentState getState() {
            return state;
        }
        synchronized void setState(MailSentState newState) {
            state = newState;
            notifyAll();
        }
    }

    private static Address[] getAddressObjArr(String addresses) throws Exception{
        String[] addressArr = addresses.split(",");
        Address[] addressObjArr = new InternetAddress[addressArr.length];

        for (int i=0; i<addressArr.length;i++) {
            addressObjArr[i] = new InternetAddress(addressArr[i]);
        }

        return addressObjArr;
    }
    private static void addAttachment(String attachmentPaths,Multipart multipart) throws Exception{
        String[] attachmentPathArr = attachmentPaths.split(",");

        for(int i=0;i<attachmentPathArr.length;i++){
            BodyPart messageBodyPart_attachment = new MimeBodyPart();
            DataSource source = new FileDataSource(attachmentPathArr[i].split("\\|")[0]);
            messageBodyPart_attachment.setDataHandler(new DataHandler(source));
            messageBodyPart_attachment.setFileName(MimeUtility.encodeText(attachmentPathArr[i].split("\\|")[1]));
            multipart.addBodyPart(messageBodyPart_attachment);
        }


    }

    public static String sendMail(String subject,String content, String attachmentPaths, String from,String addresses,String host,String auth,String username,String password){
        Properties props = System.getProperties();
        props.setProperty("mail.smtp.host", host);
        props.put("mail.smtp.auth", auth);
        props.setProperty("mail.user", username);
        props.setProperty("mail.password", password);

        String errMsg = "";

        Session session = Session.getDefaultInstance(props);
        MailSentStateFuture future = new MailSentStateFuture();

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipients(Message.RecipientType.TO, getAddressObjArr(addresses));
            message.setSubject(subject);
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(content, "text/html;charset=utf-8");

            Multipart multipart = new MimeMultipart();

            multipart.addBodyPart(messageBodyPart);
            addAttachment(attachmentPaths,multipart);

            message.setContent(multipart);

            Transport transport = session.getTransport();
            transport.addTransportListener(new TransportListener() {
                public void messageDelivered(TransportEvent arg0) {
                    future.setState(MailSentState.SUCCESS);
                }
                public void messageNotDelivered(TransportEvent arg0) {
                    future.setState(MailSentState.FAILURE);
                }
                public void messagePartiallyDelivered(TransportEvent arg0) {
                    future.setState(MailSentState.PARTIALLY_SUCCESS);
                }
            });
            transport.connect();
            transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            future.waitForReady();
        } catch (Exception e) {
            future.setState(MailSentState.FAILURE);
            errMsg = e.toString();
        }

        return "{state:'"+future.getState()+"',err:'"+errMsg+"'}";
    }


    public static void main(String[] args) throws  Exception{

        String subject = "test_title_EEE";
        String content = "<h1>这是一份测试邮件_EEE</h1>";
        String attachmentPaths = "E:/project_sync_repository/artemis-sendmail-service/lib/1.txt|1.txt,E:/project_sync_repository/artemis-sendmail-service/lib/2.txt|2.txt";
        String from = "microlens_admin@funshion.com";
        String to = "lvxw@fun.tv";
        String host = "mail.funshion.com";
        String auth = "hadoop";
        String username = "microlens_admin@funshion.com";
        String password = "Funshion&*90";


        String re = SendMail.sendMail(subject, content,attachmentPaths, from, to, host, auth, username, password);
        System.out.println(re);
    }
}
