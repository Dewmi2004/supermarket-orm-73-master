package lk.ijse.supermarketfx.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lombok.Setter;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;



public class SendMailPageController {

//    @FXML
//    private TextField txtRecipient;

    @FXML
    private TextField txtSubject;

    @FXML
    private TextArea txtDescription;

    @Setter
    private String customerEmail;

    @FXML
    void btnSendOnAction(ActionEvent event) {
        if (customerEmail == null || txtSubject == null || txtDescription == null) {
            new Alert(Alert.AlertType.ERROR, "All field are required..!").show();
            return;
        }

        String from = "shamodha7@gmail.com";
        String password = "zfws dodb icqd aqsh";
        String to = customerEmail;

        // Hold configuration setting for the SMTP (Simple mail transfer protocol)
        Properties properties = new Properties();
        // key value

        properties.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        properties.put("mail.smtp.port", "587"); //TLS Port
        properties.put("mail.smtp.auth", "true"); //enable authentication
        properties.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(txtSubject.getText());
            message.setText(txtDescription.getText());

            Transport.send(message);
            new Alert(Alert.AlertType.INFORMATION, "Email send successfully").show();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to send email..!").show();
        }
    }

}
