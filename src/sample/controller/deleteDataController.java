package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import sample.helper.DBHelper;
import sample.jadwal.Jadwal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class deleteDataController {

    private ObservableList<Jadwal> listOfJadwal = FXCollections.observableArrayList();
    Connection connection;

    private Alert alertInformation = new Alert(Alert.AlertType.INFORMATION);

    @FXML
    private VBox vboxRightLayout;

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
    private JFXButton buttonDelete;

    @FXML
    void onButtonDeleteClicked(ActionEvent event) throws SQLException {
        ObservableList<Jadwal> allJadwal, selectedJadwal;

        allJadwal = tblShowJadwal.getItems();
        selectedJadwal = tblShowJadwal.getSelectionModel().getSelectedItems();
        Integer selectedId = getIdJadwal(selectedJadwal);
        String selectedMatkul = getMatkulJadwal(selectedJadwal);

        selectedJadwal.forEach(allJadwal::remove);

        alertInformation.setTitle("Delete Success!!");
        alertInformation.setHeaderText(null);
        alertInformation.setContentText(selectedMatkul + " berhasil dihapus!");

        alertInformation.showAndWait();

        tblShowJadwal.getSelectionModel().select(null);

        String query = "DELETE FROM jadwal WHERE id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, selectedId);
        preparedStatement.execute();
    }

    @FXML
    void onTblShowJadwalClicked(MouseEvent event) {
        buttonDelete.setDisable(false);
    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        buttonDelete.setDisable(true);

        DBHelper dbHelper = new DBHelper();
        connection = dbHelper.getConnection();

        tableDataController tblData = new tableDataController();
        listOfJadwal = tblData.readDB(connection);


        matkulCol.setCellValueFactory(new PropertyValueFactory<>("Matkul"));
        waktuCol.setCellValueFactory(new PropertyValueFactory<>("Waktu"));
        dosenCol.setCellValueFactory(new PropertyValueFactory<>("Dosen"));
        gkbCol.setCellValueFactory(new PropertyValueFactory<>("Gkb"));
        ruangCol.setCellValueFactory(new PropertyValueFactory<>("Ruang"));


        tblShowJadwal.setItems(listOfJadwal);
    }

    private Integer getIdJadwal(ObservableList<Jadwal> observableList) {
        Integer selectedId = null;
        for (Jadwal jadwal : observableList) {
            selectedId = jadwal.getId();
        }
        return selectedId;
    }

    private String getMatkulJadwal(ObservableList<Jadwal> observableList) {
        String matkul = "";
        for (Jadwal jadwal : observableList){
            matkul = jadwal.getMatkul();
        }
        return matkul;
    }
}
