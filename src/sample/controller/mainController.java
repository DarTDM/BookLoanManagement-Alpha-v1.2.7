package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class mainController {

    @FXML
    private ImageView imgUmm;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private BorderPane borderPaneMainLayout;

    @FXML
    private HBox hBoxMenus;

    @FXML
    private Label labelSceneTitle;

    @FXML
    private JFXButton buttonShowData;

    @FXML
    private JFXButton buttonAddData;

    @FXML
    private JFXButton buttonUpdateData;

    @FXML
    private JFXButton buttonDeleteData;

    @FXML
    void onButtonAddClicked(ActionEvent event) throws IOException {
        loadUI("/sample/view/add_data");
        labelSceneTitle.setText("Add Data Peminjaman");
    }
    @FXML
    void onButtonDeleteData(ActionEvent event) throws IOException{
        loadUI("/sample/view/delete_data");
        labelSceneTitle.setText("Delete Data Peminjaman");

    }

    @FXML
    void onButtonShowData(ActionEvent event) throws IOException {
        loadUI("/sample/view/table_data");
        labelSceneTitle.setText("Table Data Peminjaman");

    }

    @FXML
    void onButtonUpdateData(ActionEvent event) throws IOException {
        loadUI("/sample/view/update_data");
        labelSceneTitle.setText("Update Data Peminjaman");
    }

    @FXML
    void initialize() throws IOException, SQLException, ClassNotFoundException {
        loadUI("/sample/view/table_data");

    }

    private void loadUI(String layout) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(layout + ".fxml"));
        borderPaneMainLayout.setCenter(root);
    }

}