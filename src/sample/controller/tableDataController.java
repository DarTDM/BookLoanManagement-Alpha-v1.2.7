package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import sample.helper.DBHelper;
import sample.jadwal.Jadwal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class tableDataController {


    private ObservableList<Jadwal> listOfJadwal = FXCollections.observableArrayList();

    @FXML
    private VBox vboxRightLayout;

    @FXML
    private TableView<Jadwal> tblShowJadwal;

    @FXML
    private TableColumn<Jadwal, Integer> Id;

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
    void initialize() throws SQLException, ClassNotFoundException {
        DBHelper dbHelper = new DBHelper();
        Connection connection = dbHelper.getConnection();

        listOfJadwal = readDB(connection);


        matkulCol.setCellValueFactory(new PropertyValueFactory<>("Matkul"));
        waktuCol.setCellValueFactory(new PropertyValueFactory<>("Waktu"));
        gkbCol.setCellValueFactory(new PropertyValueFactory<>("Gkb"));
        ruangCol.setCellValueFactory(new PropertyValueFactory<>("ruang"));
        dosenCol.setCellValueFactory(new PropertyValueFactory<>("Dosen"));


        tblShowJadwal.setItems(listOfJadwal);
    }
    public ObservableList<Jadwal> readDB(Connection connection) throws SQLException {

        String query = "SELECT * FROM jadwal";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        ObservableList<Jadwal> allJadwal = FXCollections.observableArrayList();

        while (resultSet.next()) {
            Jadwal jadwal = new Jadwal(
                    resultSet.getInt("id"),
                    resultSet.getString("matkul"),
                    resultSet.getInt("gkb"),
                    resultSet.getInt("ruang"),
                    resultSet.getString("dosen"),
                    resultSet.getString("waktu")
            );
            allJadwal.add(jadwal);
        }

        return allJadwal;
    }

}
