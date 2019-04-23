package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.*;

public class Book{

    public static ObservableList<BookTable> getBuku() throws SQLException{
        Statement stmt = DbConnection.getStatement();
        ObservableList<BookTable> book = FXCollections.observableArrayList();

        ResultSet rs = stmt.executeQuery("SELECT * from book");

        while (rs.next()){
            book.add(new BookTable(rs.getInt("book_id"), rs.getString("title"), rs.getInt("year"), rs.getInt("publisher_id"), rs.getString("status")));
        }

        return book;
    }

    public static TableView<BookTable> tableBuku() throws SQLException {
        TableView<BookTable> tableB;
        TableColumn<BookTable, Integer> bookIdColumn = new TableColumn("Book ID");
        bookIdColumn.setMinWidth(100);
        bookIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<BookTable, String > bookTitleColumn = new TableColumn("Title");
        bookTitleColumn.setMinWidth(200);
        bookTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<BookTable, Integer> bookYearColumn = new TableColumn("Year");
        bookYearColumn.setMinWidth(100);
        bookYearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));

        TableColumn<BookTable, Integer> bookPubIdColumn = new TableColumn("Publisher");
        bookPubIdColumn.setMinWidth(100);
        bookPubIdColumn.setCellValueFactory(new PropertyValueFactory<>("publisherId"));

        TableColumn<BookTable, String > bookStatusColumn = new TableColumn("Status");
        bookStatusColumn.setMinWidth(100);
        bookStatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        tableB = new TableView();
        tableB.setItems(getBuku());
        tableB.getColumns().addAll(bookIdColumn, bookTitleColumn, bookYearColumn, bookPubIdColumn, bookStatusColumn);

        return tableB;
    }

    public static Scene bookScene(){
        Scene scene1;

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.setSpacing(10);
        hBox.getChildren().add(Main.backBtn());
        hBox.setAlignment(Pos.BOTTOM_RIGHT);

        VBox layout2 = new VBox();
        try {
            layout2.getChildren().addAll(Book.tableBuku(),hBox);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        layout2.setPadding(new Insets(10,10,10,10));
        scene1 = new Scene(layout2, 800, 600);
        return scene1;
    }

}
