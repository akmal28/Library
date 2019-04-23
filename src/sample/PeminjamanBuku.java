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

public class PeminjamanBuku {

    static TextField txIdInput, memberIdInput;
    static TableView<PeminjamanTable> tableP1;
    static Scene scene;

    public static Connection getConnection() throws SQLException {
        return DbConnection.getConnection();
    }

    public static ObservableList<PeminjamanTable> pinjamBuku() throws SQLException {
        Statement stmt = DbConnection.getStatement();
        ObservableList<PeminjamanTable> pinjam1 = FXCollections.observableArrayList();
        ResultSet rs1 = stmt.executeQuery("select * from detail");
        while (rs1.next()){
            pinjam1.add(new PeminjamanTable(rs1.getInt("tx_id"), rs1.getInt("book_id")));
        }
        return pinjam1;
    }

    public static TableView<PeminjamanTable> tablePinjamBuku() throws SQLException{
        TableColumn<PeminjamanTable, Integer > txIdColumn = new TableColumn("ID");
        txIdColumn.setMinWidth(100);
        txIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<PeminjamanTable, Integer > bookIdColumn = new TableColumn("Book ID");
        bookIdColumn.setMinWidth(100);
        bookIdColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));

        tableP1 = new TableView();
        tableP1.setItems(pinjamBuku());
        tableP1.getColumns().addAll(txIdColumn, bookIdColumn);
        return tableP1;
    }

    public static Scene pinjamBukuScene() throws SQLException{
        txIdInput = new TextField();
        txIdInput.setPromptText("Trasaction ID");
        memberIdInput = new TextField();
        memberIdInput.setPromptText("Book ID");

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
        hBox.getChildren().addAll(txIdInput, memberIdInput, btnAdd);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(hBox, tablePinjamBuku(),foot);
        scene = new Scene(vBox, 800, 600);
        return scene;
    }

    public static void addPinjam() throws SQLException{
        PeminjamanTable add = new PeminjamanTable();

        try {
            PreparedStatement posted = getConnection().prepareStatement("INSERT INTO detail VALUES (?,?)");
            posted.setInt(1, Integer.parseInt(txIdInput.getText()));
            posted.setInt(2, Integer.parseInt(memberIdInput.getText()));
            posted.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        txIdInput.clear();
        memberIdInput.clear();
    }

}
