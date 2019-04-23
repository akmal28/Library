package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Denda {

    static TableView<DendaTable> tableD;
    static Scene scene;

    public static ObservableList<DendaTable> getDenda() throws SQLException {
        Statement stmt = DbConnection.getStatement();
        ObservableList<DendaTable> pen = FXCollections.observableArrayList();

        ResultSet rs = stmt.executeQuery("SELECT * from penalty");

        while (rs.next()){
            pen.add(new DendaTable(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getString(5)));
        }
        return pen;
    }

    public static TableView<DendaTable> tableDenda() throws SQLException{
        TableColumn<DendaTable, Integer > penIdColumn = new TableColumn("ID");
        penIdColumn.setMinWidth(100);
        penIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<DendaTable, Integer > memberIdColumn = new TableColumn("Member ID");
        memberIdColumn.setMinWidth(100);
        memberIdColumn.setCellValueFactory(new PropertyValueFactory<>("memberId"));

        TableColumn<DendaTable, Integer > txIdColumn = new TableColumn("Transaction ID");
        txIdColumn.setMinWidth(100);
        txIdColumn.setCellValueFactory(new PropertyValueFactory<>("txId"));

        TableColumn<DendaTable, Integer > totalColumn = new TableColumn("Total");
        totalColumn.setMinWidth(100);
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));

        TableColumn<DendaTable, String> statusColumn = new TableColumn("Status");
        statusColumn.setMinWidth(100);
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        tableD = new TableView();
        tableD.setItems(getDenda());
        tableD.getColumns().addAll(penIdColumn, memberIdColumn, txIdColumn, totalColumn, statusColumn);
        return tableD;
    }

    public static Scene dendaScene() throws SQLException {
        VBox vBox = new VBox();
        vBox.getChildren().addAll(tableDenda(),Main.backBtn());
        scene = new Scene(vBox, 800, 600);
        return scene;
    }

}
