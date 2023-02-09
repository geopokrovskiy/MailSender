module com.example.mailsender {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    requires javax.mail.api;


    opens com.example.program to javafx.fxml;
    opens com.example.model to javafx.base;
    exports com.example.program;
    exports com.example.model to com.fasterxml.jackson.databind;
}