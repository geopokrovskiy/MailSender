package com.example.program;

import com.example.model.User;
import com.example.repository.UserRepository;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MainController {
    @FXML
    private TableView<User> tableUser;
    private UserRepository userRepository;

    private File file;


    private void initializeTable(){
        this.tableUser.setItems(null);
        TableColumn<User, Integer> idCol = new TableColumn<User, Integer>("User Id");
        TableColumn<User, String> dateCol = new TableColumn<User, String>("Registration Date");
        TableColumn<User, String> emailCol = new TableColumn<User, String>("Email Address");
        TableColumn<User, String> countryCol = new TableColumn<User, String>("Country");
        TableColumn<User, Boolean> isSentCol = new TableColumn<User, Boolean>("Is Sent");
        TableColumn sendColumn = new TableColumn<>("Send");

        idCol.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
        dateCol.setCellValueFactory(new PropertyValueFactory<User, String>("registrationDate"));
        emailCol.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        countryCol.setCellValueFactory(new PropertyValueFactory<User, String>("country"));

        tableUser.setItems(FXCollections.observableList(this.userRepository.getUserList()));

        Callback<TableColumn<User, Boolean>, TableCell<User, Boolean>> cellFactory =
                new Callback<TableColumn<User, Boolean>, TableCell<User, Boolean>>() {
                    @Override
                    public TableCell call(final TableColumn<User, Boolean> param) {
                        final TableCell<User, Boolean> cell = new TableCell<User, Boolean>() {
                            CheckBox btn = new CheckBox();

                            {
                                btn.setDisable(true);
                                btn.setStyle("-fx-opacity: 1;");
                            }

                            @Override
                            public void updateItem(Boolean item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setSelected(tableUser.getItems().get(getIndex()).isSent());

                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };

        Callback<TableColumn<User, String>, TableCell<User, String>> buttonFactory =
                new Callback<TableColumn<User, String>, TableCell<User, String>>() {
                    @Override
                    public TableCell call(final TableColumn<User, String> param) {
                        return new TableCell<User, String>() {
                            final Button btn = new Button("Send an email");

                            {
                                btn.getStyleClass().add("primary");
                            }

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        User user = getTableView().getItems().get(getIndex());
                                        try {
                                            App.openWindowAndWait("MailSenderController.fxml", user);
                                            if(user.isSent() == true){
                                                List<Integer> users = userRepository.getSendUserIdRepository().getUserIdList();
                                                users.add(user.getId());
                                                userRepository.getSendUserIdRepository().setUserIdList(users);
                                                userRepository.getSendUserIdRepository().saveToFile(users);
                                            }
                                        } catch (IOException e) {
                                            throw new RuntimeException(e);
                                        }
                                        try {
                                            userRepository = new UserRepository(file);
                                        } catch (IOException e) {
                                            throw new RuntimeException(e);
                                        }
                                        initializeTable();
                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                    }
                };

        isSentCol.setCellFactory(cellFactory);
        sendColumn.setCellFactory(buttonFactory);
        isSentCol.setEditable(true);
        tableUser.getColumns().addAll(idCol, dateCol, emailCol, countryCol, isSentCol, sendColumn);
    }

    @FXML
    public void openFile(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJson = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(extFilterJson);
        File file = fileChooser.showOpenDialog(null);
        this.file = file;
        if(file != null) {
            this.userRepository = new UserRepository(file);
            this.initializeTable();
        }
    }


}
