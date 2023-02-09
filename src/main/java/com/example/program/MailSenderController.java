package com.example.program;

import com.example.model.User;
import com.example.util.Constants;
import com.example.util.ControllerData;
import com.example.util.MailSender;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MailSenderController implements ControllerData<User> {
    public TextField mailObject;
    public TextArea mailText;
    public Button sendMailButton;

    private User user;

    public void sendMail(ActionEvent actionEvent) {
        MailSender mailSender = new MailSender(Constants.email, Constants.password, this.user.getEmail());
        String object = this.mailObject.getText();
        String text = this.mailText.getText();
        mailSender.send(object, text);
        App.showAlert("Success", "The message has been successfully sent!", Alert.AlertType.CONFIRMATION);
        this.user.setSent(true);

        Stage oldStageMail = (Stage) this.sendMailButton.getScene().getWindow();
        oldStageMail.close();
    }

    @Override
    public void initData(User value) {
        this.user = value;
    }
}
