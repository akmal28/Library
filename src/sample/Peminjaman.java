package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.*;

public class Peminjaman {

    static Scene scene;
    static TextField idInput;
    static Statement stmt;

    static {
        try {
            stmt = DbConnection.getStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static TableView<PeminjamanTable> tableP, tableP1;

    public static Connection getConnection() throws SQLException {
        return DbConnection.getConnection();
    }

    public static ObservableList<PeminjamanTable> getPinjam() throws SQLException {
        //stmt = DbConnection.getStatement();
        ObservableList<PeminjamanTable> pinjam = FXCollections.observableArrayList();
        ResultSet rs = stmt.executeQuery("select * from transaction");
        while (rs.next()){
            pinjam.add(new PeminjamanTable(rs.getInt("tx_id"), rs.getInt("member_id"), rs.getTimestamp("date_taken").toString(),rs.getTimestamp("date_return").toString(),rs.getString("status")));
        }
        return pinjam;
    }

    public static TableView<PeminjamanTable> tablePinjam() throws SQLException {
        TableColumn<PeminjamanTable, Integer > txIdColumn = new TableColumn("ID");
        txIdColumn.setMinWidth(100);
        txIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<PeminjamanTable, Integer > memberIdColumn = new TableColumn("Member ID");
        memberIdColumn.setMinWidth(100);
        memberIdColumn.setCellValueFactory(new PropertyValueFactory<>("memberId"));

        TableColumn<PeminjamanTable, String > tglPinjamColumn = new TableColumn("Tanggal Pinjam");
        tglPinjamColumn.setMinWidth(200);
        tglPinjamColumn.setCellValueFactory(new PropertyValueFactory<>("tanggalPinjam"));

        TableColumn<PeminjamanTable, String > tglKembaliColumn = new TableColumn("Tanggal Kembali");
        tglKembaliColumn.setMinWidth(200);
        tglKembaliColumn.setCellValueFactory(new PropertyValueFactory<>("tanggalKembali"));

        TableColumn<PeminjamanTable, String > statusColumn = new TableColumn("Status");
        statusColumn.setMinWidth(200);
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        tableP = new TableView();
        tableP.setItems(getPinjam());
        tableP.getColumns().addAll(txIdColumn, memberIdColumn, tglPinjamColumn, tglKembaliColumn, statusColumn);
        return tableP;
    }

    public static Scene peminjamanScene() throws SQLException {
        idInput = new TextField();
        idInput.setPromptText("ID");

        Button btnAdd = new Button("Add");
        btnAdd.setOnAction(e -> {
            try {
                addPinjam();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        HBox foot = new HBox();
        foot.setPadding(new Insets(10,10,10,10));
        foot.setSpacing(10);
        foot.getChildren().addAll(Main.backBtn());
        foot.setAlignment(Pos.BOTTOM_RIGHT);

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(idInput, btnAdd);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(hBox, tablePinjam(),foot);
        scene = new Scene(vBox, 800, 600);
        return scene;
    }

    public static void addPinjam() throws SQLException{
        PeminjamanTable add = new PeminjamanTable();

        try {
            PreparedStatement posted = getConnection().prepareStatement("INSERT INTO transaction (member_id) VALUES (?)");
            posted.setInt(1, Integer.parseInt(idInput.getText()));
            posted.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        idInput.clear();
    }

}
