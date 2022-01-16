package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import sample.helper.DBHelper;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class addDataController {

    private Connection connection;

    private Alert alertInformation = new Alert(Alert.AlertType.INFORMATION);
    private Alert alertWarning = new Alert(Alert.AlertType.WARNING);

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private VBox addDataLayout;

    @FXML
    private TextField textFieldMatkul;

    @FXML
    private TextField textfieldGKB;

    @FXML
    private TextField textfieldRUANG;

    @FXML
    private TextField textfieldDOSEN;

    @FXML
    private TextField textfieldWAKTU;

    @FXML
    private JFXButton buttonAdd;

    @FXML
    void OnButtonAddClicked(ActionEvent event) throws SQLException, ClassNotFoundException {

        boolean textfieldEmptyResult = isInputEmpty(
                textFieldMatkul, textfieldGKB, textfieldRUANG,
                textfieldDOSEN, textfieldWAKTU
        );

        if (textfieldEmptyResult) {
            alertWarning.setTitle("Warning!");
            alertWarning.setHeaderText(null);
            alertWarning.setContentText("Textfield tidak boleh kosong!!");

            alertWarning.showAndWait();
        } else {
            String matkul = textFieldMatkul.getText();
            String waktu = textfieldWAKTU.getText();
            String dosen = textfieldDOSEN.getText();
            int gkb = Integer.valueOf(textfieldGKB.getText());
            int ruang = Integer.valueOf(textfieldRUANG.getText());
            //  #005E7A  #CCFFE4

            boolean validValue = isValueValid(gkb, ruang);

            if (validValue) {
                writeToDB(matkul, waktu, dosen, gkb, ruang);

                alertInformation.setTitle("Input Sukses!!");
                alertInformation.setHeaderText(null);
                alertInformation.setContentText("Data berhasil ditambahkan ke database!");

                alertInformation.showAndWait();

                textFieldMatkul.clear();
                textfieldGKB.clear();
                textfieldRUANG.clear();
                textfieldWAKTU.clear();
                textfieldDOSEN.clear();
            } else {
                alertWarning.setTitle("Warning!!");
                alertWarning.setHeaderText(null);
                alertWarning.setContentText("Value salah!");

                alertWarning.showAndWait();

                textfieldGKB.clear();
                textfieldRUANG.clear();
            }
        }
    }

        @FXML
        void onTextFieldClicked (MouseEvent event){
            buttonAdd.setDisable(false);
        }

        @FXML
        void initialize () throws SQLException, ClassNotFoundException {
            DBHelper dbHelper = new DBHelper();
            connection = dbHelper.getConnection();
        }

        private void writeToDB (String matkul, String waktu, String dosen, int gkb, int ruang) throws
        SQLException, ClassNotFoundException {
            String query = "INSERT INTO jadwal(matkul, waktu, dosen, gkb, ruang) VALUES(?,?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, matkul);
            preparedStatement.setString(2, waktu);
            preparedStatement.setString(3, dosen);
            preparedStatement.setInt(4, gkb);
            preparedStatement.setInt(5, ruang);

            preparedStatement.executeUpdate();
        }

        private boolean isTextFieldEmpty (TextField textField){
            return textField.getText().equals("");
        }

        private boolean isValueValid ( int gkb, int ruang){
            return validValue(gkb) || validValue(ruang);
        }

        private boolean validValue ( int value){
            return value >= 0 && value <= 1351;
        }


        private boolean isInputEmpty (TextField matkul, TextField ruang, TextField dosen,
                TextField gkb, TextField waktu){
            return isTextFieldEmpty(matkul) || isTextFieldEmpty(ruang) || isTextFieldEmpty(dosen)
                    || isTextFieldEmpty(gkb) || isTextFieldEmpty(waktu);
        }

    }




