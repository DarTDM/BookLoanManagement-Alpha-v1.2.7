package sample.controller;

import com.jfoenix.controls.JFXButton;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import sample.helper.DBHelper;
import sample.jadwal.Jadwal;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class updateDataController {

    private final Alert alertWarning = new Alert(Alert.AlertType.WARNING);
    private final Alert alertInformation = new Alert(Alert.AlertType.INFORMATION);

    private tableDataController tblController;
    private Connection connection;

    private ObservableList<Jadwal> listOfJadwal;
    private PreparedStatement preparedStatement;
    private Jadwal jadwal;

    @FXML
    private TableView<Jadwal> tblShowJadwal;

    @FXML
    private TableColumn<Jadwal, Integer> idCol;

    @FXML
    private TableColumn<Jadwal, String> matkulCol;

    @FXML
    private TableColumn<Jadwal, Integer> gkbCol;

    @FXML
    private TableColumn<Jadwal, Integer> ruangCol;

    @FXML
    private TableColumn<Jadwal, String> dosenCol;

    @FXML
    private TableColumn<Jadwal, String> waktuCol;

    @FXML
    private TextField matkulTxt;

    @FXML
    private TextField gkbTxt;

    @FXML
    private TextField ruangTxt;

    @FXML
    private TextField dosenTxt;

    @FXML
    private TextField waktuTxt;

    @FXML
    private JFXButton buttonUpdate;

    @FXML
    void onButtonUpdateClicked(ActionEvent event) throws SQLException {

        boolean resultTrue = isTextFieldCorrect(matkulTxt, gkbTxt, ruangTxt,
                dosenTxt, waktuTxt);

        if (resultTrue) {
            alertWarning.setTitle("Warning!!!");
            alertWarning.setHeaderText(null);
            alertWarning.setContentText("Textfield tidak boleh kosong!!");

            alertWarning.showAndWait();
        } else {


            String query = "UPDATE jadwal set matkul = ?, waktu = ?, dosen = ?, gkb = ?, ruang = ?  where id = ?";


            int idJadwal = jadwal.getId();
            String matkul = matkulTxt.getText();
            String waktu = waktuTxt.getText();
            String dosen = dosenTxt.getText();
            int gkb = Integer.valueOf(gkbTxt.getText());
            int ruang = Integer.valueOf(ruangTxt.getText());


            alertInformation.setTitle("Update Sukses!!!");
            alertInformation.setHeaderText(null);
            alertInformation.setContentText("Data berhasil diubah!!");

            alertInformation.showAndWait();

            tblShowJadwal.refresh();
            tblShowJadwal.getSelectionModel().select(null);

            matkulTxt.clear();
            waktuTxt.clear();
            dosenTxt.clear();
            gkbTxt.clear();
            ruangTxt.clear();


            matkulTxt.setDisable(true);
            waktuTxt.setDisable(true);
            dosenTxt.setDisable(true);
            gkbTxt.setDisable(true);
            ruangTxt.setDisable(true);
            buttonUpdate.setDisable(true);

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, matkul);
            preparedStatement.setString(2, waktu);
            preparedStatement.setString(3, dosen);
            preparedStatement.setInt(4, gkb);
            preparedStatement.setInt(5, ruang);
            preparedStatement.setInt(6, idJadwal);
            preparedStatement.executeUpdate();
            preparedStatement.close();

            listOfJadwal = tblController.readDB(connection);
            tblShowJadwal.setItems(listOfJadwal);
        }
    }

    @FXML
    void onTableClicked(MouseEvent event) {
        jadwal = tblShowJadwal.getSelectionModel().getSelectedItem();

        matkulTxt.setDisable(false);
        gkbTxt.setDisable(false);
        ruangTxt.setDisable(false);
        dosenTxt.setDisable(false);
        waktuTxt.setDisable(false);
        buttonUpdate.setDisable(false);

        matkulTxt.setText(String.valueOf(jadwal.getMatkul()));
        dosenTxt.setText(String.valueOf(jadwal.getDosen()));
        gkbTxt.setText(String.valueOf(jadwal.getGkb()));
        ruangTxt.setText(String.valueOf(jadwal.getRuang()));
        waktuTxt.setText(String.valueOf(jadwal.getWaktu()));

    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {

        DBHelper dbHelper = new DBHelper();
        connection = dbHelper.getConnection();

        matkulTxt.setDisable(true);
        gkbTxt.setDisable(true);
        ruangTxt.setDisable(true);
        dosenTxt.setDisable(true);
        ruangTxt.setDisable(true);
        buttonUpdate.setDisable(true);

        tblController = new tableDataController();
        listOfJadwal = tblController.readDB(connection);

        matkulCol.setCellValueFactory(new PropertyValueFactory<>("Matkul"));
        gkbCol.setCellValueFactory(new PropertyValueFactory<>("Gkb"));
        ruangCol.setCellValueFactory(new PropertyValueFactory<>("Ruang"));
        dosenCol.setCellValueFactory(new PropertyValueFactory<>("Dosen"));
        waktuCol.setCellValueFactory(new PropertyValueFactory<>("Waktu"));

        tblShowJadwal.setItems(listOfJadwal);
    }

    private boolean isTextFieldEmpty(TextField textField) {
        return textField.getText().equals("");
    }

    private boolean isTextFieldCorrect(TextField matkulTxt, TextField gkbTxt, TextField ruangTxt, TextField dosenTxt, TextField waktuTxt) {
        return isTextFieldEmpty(matkulTxt) || isTextFieldEmpty(gkbTxt) || isTextFieldEmpty(ruangTxt)
                || isTextFieldEmpty(waktuTxt) || isTextFieldEmpty(dosenTxt);
    }
}