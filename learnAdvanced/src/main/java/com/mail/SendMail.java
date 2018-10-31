package com.mail;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.event.TransportEvent;
import javax.mail.event.TransportListener;
import javax.mail.internet.*;
import java.util.Properties;

/**
 * @author lvxw
 */
public class SendMail {

    private enum MailSentState {
        INITIAL, SUCCESS, FAILURE, PARTIALLY_SUCCESS
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


    private static void addAttachment(String attachmentPaths, Multipart multipart) throws Exception{
        if("".equals(attachmentPaths.trim())){
            return;
        }

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
        props.setProperty("mail.smtp.port", "25");
        props.put("mail.smtp.auth", true);
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
                @Override
                public void messageDelivered(TransportEvent env) {
                    future.setState(MailSentState.SUCCESS);
                }
                @Override
                public void messageNotDelivered(TransportEvent env) {
                    future.setState(MailSentState.FAILURE);
                }
                @Override
                public void messagePartiallyDelivered(TransportEvent env) {
                    future.setState(MailSentState.PARTIALLY_SUCCESS);
                }
            });
            transport.connect(host,username,password);
            transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            future.waitForReady();
            transport.close();
        } catch (Exception e) {
            errMsg = e.toString()
                    .replaceAll("\n|\t"," ")
                    .replaceAll("\\s{2,}"," ");
        }

        return "{\"subject\":\""+subject+"\", \"state\":\""+future.getState()+"\", \"err\":\""+errMsg+"\"}";
    }



    public static void main(String[] args) throws  Exception{

        String subject = "";
        String content = "<h1>这是一份测试邮件_EEE</h1>";
        String attachmentPaths = "";
        String from = "";
        String to = "";
        String host = "";
        String auth = "";
        String username = "";
        String password = "";


        String re = SendMail.sendMail(subject, content,attachmentPaths, from, to, host, auth, username, password);
        System.out.println(re);
    }
}
